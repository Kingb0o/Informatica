
/* Do not edit this function, as it used in testing too
 * Add you own hash functions with different headers instead. */
unsigned long hash_too_simple(unsigned char *str) {
    return (unsigned long) *str;
}

// unsigned long my_own(unsigned char *str) {
//     int h = 0;
//     for (int i=0; str[i]!='\0'; i++) {
//         h = h*31 + (int)str[i];
//     }
//     return h;
// }

