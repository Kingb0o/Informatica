#include <stdlib.h>

#include "array.h"

struct array {
    unsigned long length;
    unsigned long count;
    int* data;
};

/* Initializes the array by giving it a length and some allocated memory. */
struct array *array_init(unsigned long initial_capacity) {
    struct array *a = malloc(sizeof(struct array));
    if (a) {
        a->data = (int*)malloc(initial_capacity * sizeof(int));
        a->length = initial_capacity;
        a->count = 0;
        return a;
    } else {
        return NULL;
    }
}

/* Cleans up the allocated memory of the array. */
void array_cleanup(struct array *a) {
    if (a) {
        free(a->data);
        free(a);
    }
}

/* Gets the value of the array at the given index. */
int array_get(struct array *a, unsigned long index) {
    if (a && a->count > index) {
        return a->data[index];
    } else {
        return -1;
    }
}

/* Note: Although this operation might require the array to be resized and
 * copied, in order to make room for the added element, it is possible to do
 * this in such a way that the amortized complexity is still O(1).
 * Make sure your code is implemented in such a way to guarantee this. */
int array_append(struct array *a, int elem) {
    if (a && elem) {
        if (a->count != a->length) {
            a->data[a->count] = elem;
            a->count += 1;
            return 0;
        } else {
            a->length += 1;
            a->data = (int*)realloc(a->data, a->length * sizeof(int));
            a->data[a->count] = elem;
            a->count += 1;
            return 0;
        }
    } else {
        return 1;
    }
}

/* Returns the length of the given array. */
unsigned long array_size(struct array *a) {
    if (a) {
        return a->count;
    } else {
        return 0;
    }
}