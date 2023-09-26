
set "APP_PATH=%~dp0..\.."
set "LIB_PATH=%APP_PATH%\target"
set "CP=%LIB_PATH%\plazma-kernel-lib-1.0.8.jar"

rem -Xmx64m
java -cp %CP% plazma.lib.task.Run %*

