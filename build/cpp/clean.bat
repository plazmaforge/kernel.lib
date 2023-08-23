
set "KERNEL_LIB=..\..\src\main\cpp\plazma\kernel\lib"

del /s %KERNEL_LIB%\*.o

del run.exe
del run_test.exe

del lib-*.dll