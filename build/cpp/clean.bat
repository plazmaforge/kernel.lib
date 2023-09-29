
set "PLAZMA_LIB=..\..\src\main\cpp\plazma\lib"
set "PLAZMA_TEST_LIB=..\..\src\test\cpp\plazma\lib"

del /s %PLAZMA_LIB%\*.o %PLAZMA_TEST_LIB%\*.o

del run.exe
del run_test.exe

del lib-*.dll
