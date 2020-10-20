#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "array.h"
#include "hash_table.h"

struct table {
    // The (simple) array used to index the table
    struct node **array;
    // The function used for computing the hash values in this table
    unsigned long (*hash_func)(unsigned char *);

    // Maximum load factor after which the table array should be resized
    double max_load;
    // Capacity of the array used to index the table
    unsigned long capacity;
    // Current number of elements stored in the table
    unsigned long load;
};

/* Note: This struct should be a *strong* hint to a specific type of hash table
 * You may implement other options, if you can build them in such a way they
 * pass all tests. However, the other options are generally harder to code. */
struct node {
    // The string of characters that is the key for this node
    char *key;
    // A resizing array, containing the all the integer values for this key
    struct array *value;

    // Next pointer
    struct node *next;
};

/* Constructs a table that contains a resizeable array, capacity and a load. */
struct table *table_init(unsigned long capacity,
                         double max_load,
                         unsigned long (*hash_func)(unsigned char *)) {
    struct table *t = malloc(sizeof(struct table));

    if (t) {
        t->array = calloc(capacity, sizeof(struct node*));
        t->capacity = capacity;
        t->hash_func = hash_func;
        t->load = 0;
        t->max_load = max_load;
        return t;
    } else {
        return NULL;
    }
}

/* Constructs a node that contains a key and an array of values. */
struct node *node_init(unsigned char *key, struct array *val) {
    struct node *n = malloc(sizeof(struct node));

    if (n) {
        n->key = malloc((strlen(key) + 1) * sizeof(char));

        if (n->key) {
            strcpy(n->key, key);
            n->value = val;
            n->next = NULL;
            return n;
        }
        free(n->key);
        free(n);
    } else {
        return NULL;
    }
}

/* Resizes the entire hash table by creating a new table of twice the size and rehashing every node. */
void table_resize(struct table *t) {
    struct array **new = calloc(t->capacity*2, sizeof(struct node*));

    for (int i = 0; i < (int)t->capacity; i++) {
        struct node *search = t->array[i];

        if (search) {
            struct node *temp = search->next;

            /* While there are nodes in the same bucket in the original array this function will rehash every
             * node accordingly and store them in the array even if there are already node on that index. */
            if (temp) {
                while (temp) {
                    int indexN = (int)(t->hash_func(search->key) % (t->capacity * 2));

                        if (new[indexN]) {
                            struct node *inTab = new[indexN];

                            while (inTab->next) {
                                inTab = search->next;
                            }
                            inTab->next = search;
                        } else {
                            new[indexN] = search;
                        }
                    temp = temp->next;
                }
                int indexN = t->hash_func(search->key) % (t->capacity * 2);
                new[indexN] = search;
            } else {
                int indexN = t->hash_func(search->key) % (t->capacity * 2);
                new[indexN] = search;
                search->next = NULL;
            }
        }
    }
    t->capacity *= 2;
    free(t->array);
    t->array = new;
}

/* Returns load factor of the table. */
double table_load_factor (struct table *t) {
    return t->load / t->capacity;
}

/* Inserts a key into the hash table if the spot at the index if empty. */
int table_insert(struct table *t, char *key, int val) {
    if (t && val && key) {
        if (table_load_factor(t) > t->max_load) {
            table_resize(t);
        }
        int indexN = t->hash_func(key) % t->capacity;
        struct array *found = table_lookup(t, key);

        if (found) {
            array_append(found, val);
            return 0;
        } else {
            struct array *values = array_init(1);
            array_append(values, val);
            struct node *new = node_init(key, values);

            /* If the array of the table has a node on the same index it will check
             * until the node has no longer a next value and place the new node there.
             * otherwise the node is added on the pointer of the given index. */
            if (t->array[indexN]) {
                struct node *search = t->array[indexN];

                while (search->next) {
                    search = search->next;
                }
                search->next = new;
                t->load += 1;
                return 0;
            } else {
                t->array[indexN] = new;
                t->load += 1;
                return 0;
            }
        }
    } else {
        return 1;
    }
}

/* Returns a list for all the values with the same key in the hash table. */
struct array *table_lookup(struct table *t, char *key) {
    if (t) {
        int indexT = t->hash_func(key) % t->capacity;
        struct node *search = t->array[indexT];

        while (search) {
            if (strcmp(search->key, key) == 0) {
                return search->value;
            }
            search = search->next;
        }
        return NULL;
    } else {
        return NULL;
    }
}

/* Deletes a key from the table. */
int table_delete(struct table *t, char *key) {
    if (t && key) {
        if (table_lookup(t, key)) {
            int indexT = t->hash_func(key) % t->capacity;
            struct node *search = t->array[indexT];
            struct node *prev = search;

            /* If the element is not the only element with this index this function will determain how to
             * adjust the rest of the nodes in order to keep the general in place while still removing the
             * node. If this is not the case the key can just be removed. */
            if (search->next) {
                while (search) {
                    if (strcmp(search->key, key) == 0) {
                        if (search->next) {
                            if (strcmp(search->key, prev->key) == 0) {
                                t->array[indexT] = search->next;
                                array_cleanup(search->value);
                                free(search->key);
                                free(search);
                                return 0;
                            } else {
                                prev->next = search->next;
                                array_cleanup(search->value);
                                free(search->key);
                                free(search);
                                return 0;
                            }
                        } else {
                            prev->next = NULL;
                            array_cleanup(search->value);
                            free(search->key);
                            free(search);
                            return 0;
                        }
                    }
                    prev = search;
                    search = search->next;
                }
            } else {
                array_cleanup(search->value);
                free(search->key);
                free(search);
                return 0;
            }
        } else {
            return 1;
        }
    }
}

/* Cleans up every node and array in the entire table. */
void table_cleanup(struct table *t) {
    if (t) {
        for (int i = 0; i < t->capacity; i ++) {
            struct node *search = t->array[i];

            /* Cleans up all the nodes in the table for each index layer of the table. */
            if (search && search->next) {
                while (search) {
                    struct node *temp = search;
                    search = search->next;

                    array_cleanup(temp->value);
                    free(temp->key);
                    free(temp);
                }
            }

            if (search == NULL) {
                continue;
            }
            free(search->key);
            array_cleanup(search->value);
            free(search);
        }
        free(t->array);
        free(t);
    }
}
