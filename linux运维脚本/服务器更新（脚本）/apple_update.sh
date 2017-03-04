#!/bin/bash
qudao=apple

filepath=/usr/local/buildserver/gameserver/WEB-INF/lib/gameserver/dbsqlmap

back_dir=$(date "+%Y-%m-%d")db_${qudao}_back

rm -rf $back_dir

mkdir $back_dir

cd $back_dir

echo “开始备份数据库”

mysqldump --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_account_${qudao} >heeking_account_${qudao}.sql
mysqldump --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_role_${qudao}>heeking_role_${qudao}.sql
mysqldump --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_gamedata_${qudao} >heeking_gamedata_${qudao}.sql

tar -zcvf db.tar.gz *.sql

echo "开始备份程序"

tar czvf webapps.tar.gz -C /data/heeking/tomcat-${qudao} webapps
rm -rf  /data/heeking/tomcat-${qudao}/webapps/gameserver

echo "备份完成"

cd ../

rm -rf *.sql
rm -rf db

tar -zxvf db.tar.gz 

mysql -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_account_${qudao} < heeking_account.sql 

mysql -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_gamedata_${qudao} < heeking_gamedata.sql 

mysql -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_role_${qudao} < heeking_role.sql 

rm -rf gameserver

tar -zxvf gameserver.tar.gz

cd gameserver/WEB-INF/lib/

unzip gameserver.jar -d gameserver

for file in `ls $filepath`
do
               if [ -d $filepath"/"$file ] #如果 file存在且是一个目录则为真
               then
                      echo
               else
                     path=$filepath"/"$file #得到文件的完整的目录
                     echo $path
                     sed -i "s/heeking_role/heeking_role_"${qudao}"/g" $path
              fi
done

rm -rf gameserver.jar
zip -r gameserver.jar gameserver/* 
rm -rf gameserver
cd /usr/local/buildserver/
mv gameserver /data/heeking/tomcat-${qudao}/webapps
echo "备份完成！"
