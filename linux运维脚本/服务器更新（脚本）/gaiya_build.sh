#!/bin/bash
ip=$1
rm -rf *.sql
rm -rf db.tar.gz
rm -rf gameserver.tar.gz

mysqldump --default-character-set=utf8 --opt -d heeking_account >/usr/local/zss/sql/heeking_account.sql
mysqldump --default-character-set=utf8 --opt -d heeking_role>/usr/local/zss/sql/heeking_role.sql
mysqldump --default-character-set=utf8 heeking_gamedata >/usr/local/zss/sql/heeking_gamedata.sql
tar -zcvf db.tar.gz *.sql
cd /usr/local/heeking/tomcat/webapps/

tar -zcvf gameserver.tar.gz gameserver/ --exclude=gameserver/WEB-INF/runtimejar/*

mv gameserver.tar.gz /usr/local/zss/sql/

cd /usr/local/zss/sql/
scp -i /usr/local/zss/zss -r gameserver.tar.gz  shishan.zhao@${ip}:
scp -i /usr/local/zss/zss -r db.tar.gz  shishan.zhao@${ip}:
echo "打包完成"
