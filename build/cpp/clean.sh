#!/bin/sh

PLAZMA_LIB="../../src/main/cpp/plazma/lib"
PLAZMA_TEST_LIB="../../src/test/cpp/plazma/lib"

rm -f $PLAZMA_LIB/**/*.o $PLAZMA_LIB/**/**/*.o
rm -f $PLAZMA_TEST_LIB/*.o

rm -f run
rm -f run_test

rm -f lib-*.so
rm -f lib-*.dylib
