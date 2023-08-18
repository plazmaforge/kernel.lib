#!/bin/sh

COUNT=100
if [ "$1" ]; then
  COUNT=$1
fi

./run.sh -task read-float-array -dir ../test.data.gen -file-prefix test-float-matrix- -file-count $COUNT
