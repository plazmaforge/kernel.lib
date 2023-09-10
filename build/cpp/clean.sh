#!/bin/sh

KERNEL_LIB="../../src/main/cpp/plazma/kernel/lib"

rm -f $KERNEL_LIB/**/*.o
rm -f $KERNEL_LIB/**/**/*.o

rm -f run
rm -f run_test

rm -f lib-*.so
rm -f lib-*.dylib