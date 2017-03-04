#!/bin/bash
#ver
jdk_ver="1.8.73"
echo "================开始安装jdk=================================";
tar zxvf jdk-8u73-linux-x64.gz  -C /usr/local/
ln -s /usr/local/jdk1.8.0_73 /usr/local/jdk
#set jdk
cat >>/etc/profile<<EOF
export JAVA_HOME=/usr/local/jdk
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin
EOF
#
rm -rf /usr/bin/java
ln -s /usr/local/jdk1.8.0_73/bin/java /usr/bin/
source /etc/profile
