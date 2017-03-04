#!/bin/bash
ip=$1
rm -rf *.sql
rm -rf db.tar.gz
rm -rf gameserver.tar.gz

mysqldump --default-character-set=utf8 heeking_gamedata >/usr/local/zss/sql/heeking_gamedata.sql
tar -zcvf db.tar.gz *.sql

cd /usr/local/heeking/tomcat/webapps/

tar -zcvf gameserver.tar.gz gameserver/ --exclude=gameserver/WEB-INF/runtimejar/*

mv gameserver.tar.gz /usr/local/zss/sql/

cd /usr/local/zss/sql/

scp -p22 -r /usr/local/zss/sql/gameserver.tar.gz root@${ip}:/tmp/
scp -p22 -r /usr/local/zss/sql/db.tar.gz root@${ip}:/tmp/
scp -p22 -r /usr/local/zss/sql/showdown_game_no_del.sh root@${ip}:/tmp/
ssh -p22 ${ip} "/tmp/showdown_game_no_del.sh"                    

echo "打包完成"
