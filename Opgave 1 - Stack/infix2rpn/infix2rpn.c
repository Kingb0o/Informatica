#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#include "stack.h"
int checkChar(char*);
int precedence(int);
int assoc(int);
bool isOperator(int);

int main(int argc, char *argv[]) {
    /* Checkt het aantal argumenten dat wordt meegegeven in de command line. */
    if (argc != 2) {
        printf("usage: %s \"infix_expr\"\n", argv[0]);
        return 1;
    }

    int succes = checkChar(argv[1]);

    if (succes == 1) {
        return 1;
    } else {
        return 0;
    }
}

/* This function checks the variables given in the input array */
int checkChar(char *input) {

    if (input != NULL) {
        struct stack *operation = stack_init();
        int length = (int)strlen(input);
        /* Loops through the entire input one by one. */
        for (int i = 0; i < length; i++) {
            char current = input[i];

            /* If the number is a digit it gets printed.
             * If the following character is also a number it will be printed without a space.
             */
            if (isdigit(current) && isdigit(input[i+1])) {
                printf("%c", current);
            } else if (isdigit(current) && !(isdigit(input[i+1]))) {
                printf("%c ", current);
            /* If the character is an operation it will check how to handle the operator by looking at the stack. */
            } else if ( isOperator(current) == true) {
                if (stack_empty(operation) != 1) {
                    int predStack = stack_peek(operation);
                    /* If the character has a higher precedence than the operator on the stack it will be added to the stack.
                     * Otherwise the operator will be directly printed as well.
                     */
                    if (precedence(predStack) < precedence(current)) {
                        stack_push(operation, current);
                    } else {
                        while ( precedence(predStack) > precedence(current) ||
                                ( precedence(predStack) == precedence(current ) &&
                                assoc(current) == 0 )) {
                            printf("%c ", stack_pop(operation));
                            if (stack_empty(operation) != 1) {
                                predStack = stack_peek(operation);
                            } else {
                                break;
                            }
                        }
                        stack_push(operation, current);
                    }
                } else {
                    stack_push(operation, current);
                }
            /* If the character is a left bracket it will be added to the stack or if it's a right one
             * the entire stack will be popped until a left bracket is found.
             */
            } else if (current == '(') {
                stack_push(operation, current);
            } else if (current ==')') {
                while (stack_peek(operation) != '(' && stack_empty(operation) != 1) {
                    printf("%c ", stack_pop(operation));
                }
            } else if (current == ' ') {
                continue;
            } else {
                fprintf( stderr, "There are letters in the input.\n");
                stack_cleanup(operation);
                return 1;
            }
        }

        /* After the entire evalution of the input the remainder of the stack will be printed. */
        while (stack_empty(operation) != 1) {
            if (stack_peek(operation) != '(' && stack_empty(operation) != 1) {
                int temp = stack_pop(operation);
                if (stack_peek(operation) != -1) {
                    printf("%c ", temp);
                } else {
                    printf("%c", temp);
                }
            } else {
                stack_pop(operation);
            }
        }
        printf("\n");
        stack_cleanup(operation);
    }
    return 0;
}

/* Checks the higher precedence of all the operators to compare
 * the operators when popping of pushing to the stack.
 */
int precedence(int charac) {
	if (charac == '~') {
		return 4;
	} else if (charac == '^') {
        return 3;
    } else if (charac == '*' || charac == '/') {
		return 2;
	} else if (charac == '+' || charac == '-') {
		return 1;
	} else {
		return 0;
	}
}

/* Checks the associativity of all the operators to determain when to
 * to push or print the operator.
 */
int assoc(int charac) {
    if (charac == '^' || charac == '~') {
		return 1;
	} else {
		return 0;
	}
}

/* Checks if character is an operator and returns true or false. */
bool isOperator(int current) {
    if (current == '+' || current == '-' ||
        current == '*' || current == '/' ||
        current == '^' || current == '~') {
            return true;
        } else {
            return false;
        }
}