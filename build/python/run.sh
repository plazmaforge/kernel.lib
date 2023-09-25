
LIB_PATH=$PWD
LIB_PATH=`dirname "$LIB_PATH"`/..
LIB_PATH=$LIB_PATH/src/main/python
PYTHONPATH=`cd "$LIB_PATH" && pwd`

export PYTHONPATH
#echo $PYTHONPATH

python3 -m plazma.lib.task.run "$@"
