#include <stdlib.h>
#include <stdio.h>

#include "stack.h"

/* Defines attributes of the stack. */
struct stack {
    int top;
    int stackSize;
    int* stackArray;
    int popCount;
    int pushCount;
    int maxSize;
};

/* This function creates a standard stack with a size of 100 and a top value
 * of -1 because the stack is empty. At last also allocating memory for the
 * array in the stack. */
struct stack *stack_init() {
    struct stack *s = malloc(sizeof(struct stack));
    if (s) {
        s->top = -1;
        s->stackSize = 100;
        s->stackArray = (int*)malloc(100 * sizeof(int));
        s->popCount = 0;
        s->pushCount = 0;
        s->maxSize = 0;
        return s;
    } else {
        return NULL;
    }
}

/* This function frees the memory allocated to the stack that was firstly
 * given */
void stack_cleanup(struct stack *s) {

    fprintf( stderr, "stats %d ", s->pushCount);
    fprintf( stderr, "%d ", s->popCount);
    fprintf( stderr, "%d\n", s->maxSize);
    free(s->stackArray);
    free(s);
}

/* This function pushes the given item to the top of the stack
 * where is increases the value of the top variable. */
int stack_push(struct stack *s, int e) {
    if (s != NULL) {
        if (s->top >= 99) {
            return 1;
        } else {
            s->pushCount += 1;
            s->top = s->top + 1;
            s->stackArray[s->top] = e;
            /* Stores the max size of the stack each time something gets pushed. */
            if ((s->top + 1) > s->maxSize) {
                s->maxSize = s->top + 1;
            }
            return 0;
        }
    } else {
        return 1;
    }
}

/* This function pops the top item from the given stack and lowers
 * the top value with 1. */
int stack_pop(struct stack *s) {
    if (s != NULL) {
        if (stack_empty(s) == 0) {
            s->popCount += 1;
            int popValue = s->stackArray[s->top];
            s->top = s->top - 1;
            return popValue;
        } else {
            return -1;
        }
    } else {
        return -1;
    }
}

/* This function returns the top item from the given stack */
int stack_peek(struct stack *s) {
    if (s != NULL) {
        if (stack_empty(s) == 0) {
            return s->stackArray[s->top];
        } else {
            return -1;
        }
    } else {
        return -1;
    }
}

/* This function checks if the stack is empty. */
int stack_empty(struct stack *s) {
    if (s != NULL) {
        if (s->top == -1) {
            return 1;
        } else if (s->top >= 0) {
            return 0;
        } else {
            return -1;
        }
    } else {
        return -1;
    }
}
