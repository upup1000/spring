#!/bin/bash
qudao=$1
gamedata=$2
account=$3
role=$4

filepath=/usr/local/buildserver/gameserver/WEB-INF/lib/gameserver/dbsqlmap

back_dir=back/$(date "+%Y-%m-%d")back

opendate=''

mkdir -p $back_dir/db_${qudao}

cd $back_dir/db_${qudao}

/data/heeking/tomcat-${qudao}/bin/shutdown.sh
echo "sleep 10正式服不可用 要修改"
sleep 10
kill -9 `ps aux |grep -i tomcat-"${qudao}" |grep -v grep |awk '{print $2}'`

echo “开始备份数据库”

mysqldump --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_account_${qudao} >heeking_account_${qudao}.sql
mysqldump --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_role_${qudao}>heeking_role_${qudao}.sql
mysqldump --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_gamedata_${qudao} >heeking_gamedata_${qudao}.sql

opendate=$(mysql -usaintdbcon -p87ohB07jG7 -h10.10.130.132 -e "select v from heeking_gamedata_${qudao}.game_param where id = 45" | head -n 2 | tail -n 1)

tar -zcvf db.tar.gz *.sql

echo "开始备份程序"

tar czvf webapps.tar.gz -C /data/heeking/tomcat-${qudao} webapps
rm -rf  /data/heeking/tomcat-${qudao}/webapps/gameserver

echo "备份完成"



cd /usr/local/buildserver/

rm -rf *.sql
rm -rf db

tar -zxvf db.tar.gz 

if [ $account -eq 1 ]
then
  mysql -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_account_${qudao} < heeking_account.sql 
elif [ $account -eq 0 ]
then
   echo "不更新 account">1.txt
fi

if [ $gamedata -eq 1 ]
then
  mysql -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_gamedata_${qudao} < heeking_gamedata.sql
elif [ $gamedata -eq 0 ]
then
   echo "不更新 gamedata" >>1.txt
fi


if [ $role -eq 1 ]
then
  mysql -usaintdbcon -p87ohB07jG7 -h10.10.130.132 heeking_role_${qudao} < heeking_role.sql
elif [ $role -eq 0 ]
then
   echo "不更新 role">>1.txt
fi

echo ${opendate}
mysql -usaintdbcon -p87ohB07jG7 -h10.10.130.132 -e "update heeking_gamedata_${qudao}.game_param set v='${opendate}' where id=45 "


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

for file in `ls $filepath`
do
               if [ -d $filepath"/"$file ] #如果 file存在且是一个目录则为真
               then
                      echo
               else
                     path=$filepath"/"$file #得到文件的完整的目录
                     echo $path
                     sed -i "s/heeking_account/heeking_account_"${qudao}"/g" $path
              fi
done

rm -rf gameserver.jar

cd gameserver

/usr/local/jdk/bin/jar cvf gameserver.jar *

#zip -r gameserver.jar gameserver/* 
mv gameserver.jar ../

rm -rf gameserver
cd /usr/local/buildserver/
mv gameserver /data/heeking/tomcat-${qudao}/webapps

rm -rf /data/heeking/tomcat-${qudao}/logs/catalina.out

/data/heeking/tomcat-${qudao}/bin/startup.sh 
#tail -f /data/heeking/tomcat-${qudao}/logs/catalina.out
echo "备份完成！"
