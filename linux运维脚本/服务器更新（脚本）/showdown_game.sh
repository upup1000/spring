#!/bin/bash
/mydata/heeking/apache-tomcat-8.0.26/bin/shutdown.sh
echo "sleep 10正式服不可用 要修改"
sleep 10
##########强制关闭服务器################################
echo "开始强制关闭游戏服务器"
kill -9 `ps aux |grep -i tomcat |grep -v grep |awk '{print $2}'`
cd /tmp
tar -zxvf db.tar.gz 
echo "开始导入数据库"
mysql heeking_account < heeking_account.sql
mysql heeking_role < heeking_role.sql
mysql heeking_gamedata < heeking_gamedata.sql
echo "导入数据库完成"

tar -zxvf gameserver.tar.gz 

rm -rf /mydata/heeking/apache-tomcat-8.0.26/webapps/gameserver
echo "开始部署程序"
mv gameserver  /mydata/heeking/apache-tomcat-8.0.26/webapps/
echo "部署完成"
rm -rf heeking_account.sql heeking_role.sql heeking_gamedata.sql
rm -rf gameserver.tar.gz
echo "开始启动服务器"
/mydata/heeking/apache-tomcat-8.0.26/bin/startup.sh 

