#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <getopt.h>
#include <math.h>
#include <ctype.h>

#include "list.h"

#define BUF_SIZE 1024

static char buf[BUF_SIZE];

struct config {
    // You can ignore these options until you implement the
    // extra command-line arguments.

    // Set to 1 if -u is specified, 0 otherwise.
    int unique_values;

    // Set to 1 if -d is specified, 0 otherwise.
    int descending_order;

    // Set to 1 if -i is specified, 0 otherwise.
    int insert_intermediate;

    // Set to 1 if -z is specified, 0 otherwise.
    int zip_alternating;
};

void printLinkedList(struct list *list);

int parse_options(struct config *cfg, int argc, char *argv[]) {
    memset(cfg, 0, sizeof(struct config));
    int c;
    while ((c = getopt (argc, argv, "udiz")) != -1) {
        switch (c) {
            case 'u': cfg->unique_values = 1; break;
            case 'd': cfg->descending_order = 1; break;
            case 'i': cfg->insert_intermediate = 1; break;
            case 'z': cfg->zip_alternating = 1; break;
            default:
                      fprintf(stderr, "invalid option: -%c\n", optopt);
                      return 1;
        }
    }
    return 0;
}

/* Prints every element of the linked list. */
void printLinkedList(struct list *list) {
    for (size_t i = 0; i < list_length(list); i++) {
        printf("%d\n", list_node_value(list_get_ith(list, i)));
    }
}

/* Thhe function sorts the given input in a sorted linked list. */
int main(int argc, char *argv[]) {
    struct config cfg;
    if (parse_options(&cfg, argc, argv) != 0) {
        return 1;
    }

    struct list *sort = list_init();
    struct list *sndList;
    int dbl = cfg.unique_values;
    int desc = cfg.descending_order;
    int intm = cfg.insert_intermediate;
    int zip = cfg.zip_alternating;


    /* While there are input lines in the files that is given, it will repeat the sorting algorithm. */
    while (fgets(buf, BUF_SIZE, stdin)) {

        char *token = strtok(buf, " \t");

        /* Filters out all the lines that are not numbers. */
        if (isdigit(token[0]) || token[0] == '-') {
            while (token) {
                struct node *newNode = list_new_node((int)strtol(token, NULL, 10));

                if (list_node_present(sort, newNode) && dbl == 1) {
                    free(newNode);
                    break;
                }
                /* If the list is empty the node is added to the front of the list.
                 * If the new node is larger or smaller than the head, depending on if the
                 * list is descending, the new node is added to the front or back of the list.
                 */
                if (list_head(sort)) {
                    if ((list_node_value(newNode) > list_node_value(list_head(sort)) && desc == 0) ||
                        (list_node_value(newNode) < list_node_value(list_head(sort)) && desc == 1)) {
                        if ((list_node_value(newNode) < list_node_value(list_tail(sort)) && desc == 0) ||
                            (list_node_value(newNode) > list_node_value(list_tail(sort)) && desc == 1)) {

                            for (size_t i = 0; i < list_length(sort); i++) {
                                if ((list_node_value(newNode) < list_node_value(list_get_ith(sort, i)) && desc == 0) ||
                                    (list_node_value(newNode) > list_node_value(list_get_ith(sort, i)) && desc == 1)) {
                                    list_insert_before(sort, newNode, list_get_ith(sort, i));
                                    break;
                                }
                            }
                        } else {
                            list_add_back(sort, newNode);
                        }
                    } else {
                        list_add_front(sort, newNode);
                    }
                } else {
                    list_add_front(sort, newNode);
                }
                token = strtok(NULL, " \t");
            }
        }
    }

    /* This function adds intermediate values of the linked values to the linked list. */
    if (intm == 1) {
        struct node *intNode;
        double fst;
        double snd;
        double new;

        for (size_t i = 0; i < list_length(sort)-1; i++) {
            fst = list_node_value(list_get_ith(sort, i));
            snd = list_node_value(list_get_ith(sort, (i+1)));
            new = round((snd + fst)/2.0);

            intNode = list_new_node((int)new);
            list_insert_after(sort, intNode, list_get_ith(sort, i));
            i++;
        }
    }

    /* TODO, werkt bijna. */
    if (zip == 1) {
        int cutAfter = (int)round((double)list_length(sort)/2.0);
        struct node *half = list_get_ith(sort, (size_t)cutAfter);
        sndList = list_cut_after(sort, half);
        struct node *temp = list_head(sndList);

        for (size_t i = 0; i < list_length(sort); i++) {
            if (list_node_value(list_head(sndList))) {
                list_unlink_node(sndList, list_head(sndList));
                list_insert_after(sort, temp, list_get_ith(sort, i));
                temp = list_head(sndList);
                i++;
            }
        }
        list_cleanup(sndList);
    }

    /* Prints the sorted list and cleans it up afterwards. */
    printLinkedList(sort);
    list_cleanup(sort);
    return 0;
}
