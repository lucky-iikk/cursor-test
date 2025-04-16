#!/bin/bash

# 1. 定义变量
APP_NAME="cursor-test"
JAR_FILE="target/${APP_NAME}.jar"
DEPLOY_DIR="/home/admin/app"
LOG_FILE="${DEPLOY_DIR}/app.log"

# 2. 停止正在运行的应用
echo "Stopping existing application..."
PID=$(ps -ef | grep ${APP_NAME} | grep -v grep | awk '{print $2}')
if [ -n "$PID" ]; then
    kill -9 $PID
fi

# 3. 构建项目
echo "Building the project..."
mvn clean package -DskipTests

# 4. 检查构建结果
if [ ! -f "${JAR_FILE}" ]; then
    echo "Build failed. JAR file not found: ${JAR_FILE}"
    exit 1
fi

# 5. 部署新版本
echo "Deploying new version..."
mkdir -p ${DEPLOY_DIR}
cp ${JAR_FILE} ${DEPLOY_DIR}

# 6. 启动应用
echo "Starting the application..."
nohup java -jar ${DEPLOY_DIR}/${APP_NAME}.jar > ${LOG_FILE} 2>&1 &

# 7. 检查启动状态
sleep 5
PID=$(ps -ef | grep ${APP_NAME} | grep -v grep | awk '{print $2}')
if [ -n "$PID" ]; then
    echo "Application started successfully. PID: $PID"
else
    echo "Failed to start the application."
    exit 1
fi