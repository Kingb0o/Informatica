/* Authors: Wessel van der Linde [12857130]
 * Date: 11/03/2020
 * Study: Bsc Informatica
 * Course: Datastructuren en Algoritmen
 */
#include <fcntl.h>
#include <getopt.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include "prioq.h"

#define BUF_SIZE 1024

static char buf[BUF_SIZE];

struct config {
    // You can ignore these options until/unless you implement the
    // bonus features.

    // Set to 1 if -y is specified, 0 otherwise.
    int year;
};

static int parse_options(struct config *cfg, int argc, char *argv[]);

typedef struct {
    char *name;
    int age;
} patient_t;

static int compare_patient_name(const void *a, const void *b) {
    return strcmp(((const patient_t *)a)->name, ((const patient_t *)b)->name);
}

static int compare_patient_age(const void *a, const void *b) {
    return ((const patient_t *)a)->age - ((const patient_t *)b)->age;;
}

int parse_options(struct config *cfg, int argc, char *argv[]) {
    memset(cfg, 0, sizeof(struct config));
    int c;
    while ((c = getopt(argc, argv, "y")) != -1) {
        switch (c) {
        case 'y':
            cfg->year = 1;
            break;
        default:
            fprintf(stderr, "invalid option: -%c\n", optopt);
            return 1;
        }
    }
    return 0;
}

int main(int argc, char *argv[]) {
    char *token;
    prioq *queue;
    struct config cfg;

    if (parse_options(&cfg, argc, argv) != 0) {
        return EXIT_FAILURE;
    }

    if (cfg.year) {
        queue = prioq_init(&compare_patient_age);
    } else {
        queue = prioq_init(&compare_patient_name);
    }

    for (int iterations = 0;;) {
        while (1) {
            char *s = fgets(buf, BUF_SIZE, stdin);
            if (s == NULL) {
                fprintf(stderr, "Unexpected end of file. exiting\n");
                return EXIT_FAILURE;
            }
            if (strcmp(buf, ".\n") == 0) {
                break;
            } else {
                token = strtok(buf, " ");
                patient_t *patient = malloc(sizeof(patient_t));
                patient->name = malloc((strlen(buf) + 1));
                strcpy(patient->name, token);
                patient->age = (int)strtol(strtok(NULL, " "), 0, 10);
                prioq_insert(queue, patient);
            }
        }

        if (prioq_size(queue) > 0) {
            patient_t *difPatient = prioq_pop(queue);
            printf("%s\n", difPatient->name);
            free(difPatient->name);
            free(difPatient);
        }
        printf(".\n"); // End turn.

        if (++iterations == 10) {

            while(prioq_size(queue) != 0) {
                patient_t *difPatient = prioq_pop(queue);
                printf("%s\n", strtok(difPatient->name, " "));
                free(difPatient->name);
                free(difPatient);
            }
            break;
        }
    }

    prioq_cleanup(queue, NULL);
    return EXIT_SUCCESS;
}
