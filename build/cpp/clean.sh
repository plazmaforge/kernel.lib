#!/bin/sh

KERNEL_LIB="../../src/main/cpp/plazma/kernel/lib"

rm $KERNEL_LIB/**/*.o
rm $KERNEL_LIB/**/**/*.o

rm run
rm run_test