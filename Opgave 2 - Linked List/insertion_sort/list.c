#include "list.h"

/* Definiton of a node. */
struct node {
    int data;
    struct node *next;
    struct node *prev;
};

/* Definition of a list. */
struct list {
    size_t length;
    struct node *head;
    struct node *tail;
};

/* Defines a linked list and allocates a certain amount of memory to it. */
struct list *list_init(void) {
    struct list *l = malloc(sizeof(struct list));

    if (l) {
        l->length = 0;
        l->head = NULL;
        l->tail = NULL;
        return l;
    } else {
        return NULL;
    }
}

/* Allocates memory to a new node with the value of the given integer. */
struct node *list_new_node(int num) {
    struct node *n = malloc(sizeof(struct node));
    if (n) {
        n->data = num;
        n->next = NULL;
        n->prev = NULL;
        return n;
    } else {
        return NULL;
    }
}

/* Returns the head of the list if the list is not empty. */
struct node *list_head(struct list *l) {
    if (l && l->head) {
        return l->head;
    } else {
        return NULL;
    }
}

/* Returns the next node in the list if the node is not the last item in the list. */
struct node *list_next(struct node *n) {
    if (n && n->next) {
        return n->next;
    } else {
        return NULL;
    }
}

/* Adds a node to the front of the linked list. */
int list_add_front(struct list *l, struct node *n) {
    if (l && n) {
        if (l->head) {
            l->head->prev = n;
            n->next = l->head;
            l->head = n;
            l->length += 1;
            return 0;
        } else {
            l->head = n;
            l->tail = n;
            l->length = 1;
            return 0;
        }
    } else {
        return 1;
    }
}

/* Returns the last value of the linked list */
struct node *list_tail(struct list *l) {
    if (l) {
        return l->tail;
    } else {
        return NULL;
    }
}

/* Returns the previous node in the linked list, that points to the given node. */
struct node *list_prev(struct list *l, struct node *n) {
    if (l && n) {
        if (l->head != n && n->prev) {
            return n->prev;
        } else {
            return NULL;
        }
    } else {
        return NULL;
    }
}

/* Adds a node to the end of the linked list. */
int list_add_back(struct list *l, struct node *n) {
    if (l && n) {
        if (l->tail) {
            l->tail->next = n;
            n->prev = l->tail;
            l->tail = n;
            l->length += 1;
            return 0;
        } else {
            l->head = n;
            l->tail = n;
            l->length = 1;
            return 0;
        }
    } else {
        return 1;
    }
}

/* Returns the value of the given node. */
int list_node_value(struct node *n) {
    if (n) {
        return n->data;
    } else {
        return 0;
    }
}

/* Unlinks the give node from the list it is currently in. */
int list_unlink_node(struct list *l, struct node *n) {
    if (l && n) {
        if (list_node_present(l, n)) {
            if (l->head == n && n->next) {
                l->head = n->next;
                n->next = NULL;
                l->length -= 1;
                free(n);
                return 0;
            } else if (l->head == n) {
                l->head = NULL;
                l->tail = NULL;
                l->length -= 1;
                free(n);
                return 0;
            }

            /* If the given node is not the head node of the list it will itterate through the entire list
             * up until the node is found. If the node is found it will be removed and the rest of the
             * links in the list will be transferred to the nodes that came before and after the removed node.
             */
            struct node *nextNode = l->head;
            while (nextNode) {
                if (nextNode->next == n) {
                    nextNode->next = n->next;
                    nextNode->prev = n->prev;
                    n->next = NULL;
                    n->prev = NULL;
                    l->length -= 1;
                    free(n);
                    return 0;
                }
                nextNode = nextNode->next;
            }
            return 1;
        } else {
            return 1;
        }
    } else {
        return 1;
    }
}

/* Frees the given node. */
void list_free_node(struct node *n) {
    free(n);
}

/* Frees all the nodes in the linked list and the list itself. */
int list_cleanup(struct list *l) {
    if (l) {
        if (l->head) {
            struct node *nextNode = l->head;

            while (nextNode) {
                nextNode = l->head->next;
                free(l->head);
                l->head = nextNode;
            }

            /* After freeing all the nodes in the list, the list itself is also freed. */
            if (l->head) {
                return 1;
            } else {
                free(nextNode);
                free(l);
                return 0;
            }
        } else {
            free(l);
            return 0;
        }
    } else {
        return 1;
    }
}

/* Checks if the given node is present in the given list. */
int list_node_present(struct list *l, struct node *n) {
    if (l && n) {
        if (l->head) {
            struct node *temp = l->head;

            for (int i = 0; i < (int)l->length; i++) {
                if (temp->data == n->data) {
                    return 1;
                } else {
                    temp = temp->next;
                }
            }
            return 0;
        } else {
            return 0;
        }
    } else {
        return 0;
    }
}

/* Adds a new node in the given list after the second given node. */
int list_insert_after(struct list *l, struct node *n, struct node *m) {
    if (l && n && m) {
        if (list_node_present(l, n) == 0 || list_node_present(l, m) == 1) {
            n->next = m->next;
            n->prev = m;
            m->next->prev = n;
            m->next = n;
            l->length += 1;
            return 0;
        } else {
            return 1;
        }
    } else {
        return 1;
    }
}

/* Adds a new node in the given list before the second given node. */
int list_insert_before(struct list *l, struct node *n, struct node *m) {
    if (l && n && m) {
        if (list_node_present(l, n) == 0 || list_node_present(l, m) == 1) {
            n->prev = m->prev;
            n->next = m;
            m->prev->next = n;
            m->prev = n;
            l->length += 1;
            return 0;
        } else {
            return 1;
        }
    } else {
        return 1;
    }
}

/* Returns the length of the given list. */
size_t list_length(struct list *l) {
    if (l) {
        return l->length;
    } else {
        return 0;
    }
}

/* Returns the ith node in the given list. */
struct node *list_get_ith(struct list *l, size_t i) {
    if (l) {
        struct node *temp = l->head;
        if (i < l->length) {
            for (int j = 0; j <= (int)i; j++) {
                if (j != (int)i) {
                    temp = temp->next;
                } else {
                    return temp;
                }
            }
            return NULL;
        } else {
            return NULL;
        }
    } else {
        return NULL;
    }
}

/* Cuts the given list in two lists if the */
struct list *list_cut_after(struct list *l, struct node *n) {
    if (l && n) {
        struct list *newList = list_init();

        if (list_node_present(l, n) == 1) {

            if (l->head != n) {
                /* This for loop looks for the given node in the list. If it find it, the list is cut there and a new list is created.
                * The new list starts with the element after the given node.
                */
                for (size_t i = 0; i < l->length; i++) {
                    if (n == list_get_ith(l, i)) {
                        newList->head = list_get_ith(l, (i));
                        newList->tail = l->tail;
                        newList->length = l->length - i;
                        l->tail = n;
                        l->length = i;
                        return newList;
                    }
                }
            } else {
                return l;
            }
            return NULL;
        } else {
            return NULL;
        }
    } else {
        return NULL;
    }
}
