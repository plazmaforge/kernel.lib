@echo off

set COUNT=100
if not "%1"=="" set COUNT=%1

run.bat -task read-float-array -dir ..\test.data.gen -file-prefix test-float-matrix- -file-count %COUNT%
