#include <math.h>
#include <stdio.h>
#include <stdlib.h>

#include "array.h"
#include "prioq.h"

void heapify(struct heap *h, int i);
void build_heapify(struct heap *h);

/* Creates the heap by allocating memory to the heap itself and the array inside of it. */
static struct heap *heap_init(int (*compare)(const void *, const void *)) {
    struct heap *h = malloc(sizeof(struct heap));

    if (h) {
        h->compare = *compare;
        h->array = array_init(1);
        return h;
    } else {
        return NULL;
    }
}

/* Initializes the heap struct. */
struct heap *prioq_init(int (*compare)(const void *, const void *)) {
    return heap_init(compare);
}

/* Returns the array size of the priority queue. */
long int prioq_size(struct heap *h) {
    return array_size(h->array);
}

/* Frees all the allocated memory of the heap. */
static int heap_cleanup(struct heap *h, void free_func(void *)) {
    if (h) {
        array_cleanup(h->array, free_func);
        free(h);
        return 0;
    } else {
        return 1;
    }
}

/* Frees all the allocated memory positions of the queue. */
int prioq_cleanup(prioq *h, void free_func(void *)) {
    return heap_cleanup(h, free_func);
}

/* Inserts a element inside of the heap and organizes
 * the heap by sorting the array. */
static int heap_insert(struct heap *h, void *p) {
    if (h && p) {
        array_append(h->array, p);
        build_heapify(h);
        return 0;
    } else {
        return 1;
    }
}

int prioq_insert(prioq *h, void *p) {
    return heap_insert(h, p);
}

/* Pops the first element of the array. */
static void *heap_pop(struct heap *h) {
    if (h) {
        if (array_size(h->array) > 0) {
            if (array_size(h->array) != 1) {
                void *temp = array_get(h->array, 0);
                array_set(h->array, 0, array_get(h->array, array_size(h->array) - 1));
                array_set(h->array, array_size(h->array) - 1, temp);
                array_pop(h->array);
                heapify(h, 0);
                return temp;
            } else {
                return array_pop(h->array);
            }
        } else {
            return NULL;
        }
    } else {
        return NULL;
    }
}

void *prioq_pop(prioq *h) {
    return heap_pop(h);
}

/* Orders the entire heap by sorting its children. */
void heapify(struct heap *h, int i) {
    if (h) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (right < array_size(h->array)) {
            if (h->compare(array_get(h->array, right), array_get(h->array, smallest)) <= 0) {
                smallest = right;
            }
        }

        if (left < array_size(h->array)) {
            if (h->compare(array_get(h->array, left), array_get(h->array, smallest)) <= 0) {
                smallest = left;
            }
        }

        if (smallest != i) {
            void *tempI = array_get(h->array, i);
            array_set(h->array, i, array_get(h->array, smallest));
            array_set(h->array, smallest, tempI);
            heapify(h, smallest);
        }
    }
}

/* Heapifies the heap for every element in the heap that has a child. */
void build_heapify(struct heap *h) {
    int hasChild = (int)((array_size(h->array) - 1) / 2);
    for (int i = hasChild; i >= 0; --i) {
        heapify(h, i);
    }
}

