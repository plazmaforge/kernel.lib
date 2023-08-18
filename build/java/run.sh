
APP_PATH=$PWD
LIB_PATH=`dirname "$APP_PATH"`/..
LIB_PATH=`cd "$LIB_PATH" && pwd`
LIB_PATH=$LIB_PATH/target
CP=$LIB_PATH/plazma-kernel-lib-1.0.8.jar

#-Xmx64m
java -cp $CP plazma.kernel.lib.task.Run "$@"
