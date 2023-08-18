
set "LIB_PATH=%~dp0..\.."
set "LIB_PATH=%LIB_PATH%\src\main\python"
set "PYTHONPATH=%LIB_PATH%"

python -m plazma.kernel.lib.task.run %*
