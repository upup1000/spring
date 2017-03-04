#!/bin/bash
dbip=$1
##########强制关闭服务器################################
echo "开始强制关闭游戏服务器"
kill -9 `ps aux |grep -i apache-tomcat-8.0.26 |grep -v grep |awk '{print $2}'`
mysqldump --default-character-set=utf8 --opt -d -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_account >heeking_account.sql
mysqldump --default-character-set=utf8 --opt -d -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_role >heeking_role.sql
mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_role < heeking_role.sql
mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_account < heeking_account.sql
rm -rf heeking_role.sql heeking_account.sql
