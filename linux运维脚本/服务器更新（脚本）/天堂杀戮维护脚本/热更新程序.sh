#!/bin/bash
#author zss
##数据库ip
dbip=$1
#是否更新gameserver_processor.jar
process=$2
#是否更新gamelistener.jar
gamelistener=$3
#是否更新gameserver.jar
gameserver=$4

mkdir -p /usr/local/run_update
cd /usr/local/run_update
#####更新process.jar
if [ $process -eq 1 ]
then
    rm -rf /data/heeking/apache-tomcat-8.0.26/webapps/gameserver/WEB-INF/process/gameserver_processor.jar
    cp /usr/local/run_update/gameserver_processor.jar /data/heeking/apache-tomcat-8.0.26/webapps/gameserver/WEB-INF/process/
    mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} -e "update heeking_gamedata.dbcachelist set version=version+1 where cachename='processloader'"
elif [ $process -eq 0 ]
then
   echo "不更新 process">1.txt
fi

#####gamelistener.jar
if [ $gamelistener -eq 1 ]
then
    rm -rf /data/heeking/apache-tomcat-8.0.26/webapps/gameserver/WEB-INF/script/gamelistener.jar
    cp /usr/local/run_update/gamelistener.jar /data/heeking/apache-tomcat-8.0.26/webapps/gameserver/WEB-INF/script/
    mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} -e "update heeking_gamedata.dbcachelist set version=version+1 where cachename='listener'"
elif [ $gamelistener -eq 0 ]
then
   echo "不更新 gamelistener">1.txt
fi

#####更新gameserver.jar
if [ $gameserver -eq 1 ]
then
 ####下载对应版本的gameserver.jar
 rm -rf /usr/local/hotload/reload.txt
 cp /usr/local/run_update/gameserver.jar /usr/local/hotload/
 echo "/data/heeking/apache-tomcat-8.0.26/webapps/gameserver/WEB-INF/lib/gameserver.jar;/usr/local/hotload/gameserver.jar" > /usr/local/hotload/reload.txt
elif [ $gameserver -eq 0 ]
then
   echo "不更新 gameserver">1.txt
fi



