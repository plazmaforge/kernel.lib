
LIB_PATH=$PWD
LIB_PATH=`dirname "$LIB_PATH"`/..

LIB_MAIN_PATH=$LIB_PATH/src/main/python
LIB_TEST_PATH=$LIB_PATH/src/test/python

LIB_MAIN_PATH=`cd "$LIB_MAIN_PATH" && pwd`
LIB_TEST_PATH=`cd "$LIB_TEST_PATH" && pwd`

#echo $LIB_MAIN_PATH
#echo $LIB_TEST_PATH

PYTHONPATH=$LIB_MAIN_PATH:$LIB_TEST_PATH

export PYTHONPATH
#echo $PYTHONPATH

python3 -m unittest discover -v -s $LIB_TEST_PATH/plazma/kernel/lib/str -p "strlib_test.py"