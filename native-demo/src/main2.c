#include <stdio.h>
#include "mathlib.h"

int ext_add(int a, int b);

int main(void) {
    printf("add(2, 3)     = %d\n", add(2, 3));
    printf("mul(4, 5)     = %d\n", mul(4, 5));
    printf("ext_add(2, 3) = %d\n", ext_add(2, 3));
    return 0;
}