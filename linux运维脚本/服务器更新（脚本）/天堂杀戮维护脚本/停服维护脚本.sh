#!/bin/bash
#数据库ip
dbip=$1
#是否更新代码
gameserver=$2
#是否更新heeking_account库
account=$3
#是否更新heeking_gamedata库
gamedata=$4
#是否更新更新heeking_role库
role=$5
opendate=''
back_dir=$(date "+%Y-%m-%d")

mkdir -p /usr/local/updateback/$back_dir
cd /usr/local/updateback/$back_dir
/data/heeking/apache-tomcat-8.0.26/bin/shutdown.sh
echo "sleep 60正式服不可用 要修改"
sleep 60
##########强制关闭服务器################################
echo "开始强制关闭游戏服务器"
kill -9 `ps aux |grep -i apache-tomcat-8.0.26 |grep -v grep |awk '{print $2}'`
##########删除日志####################################
rm -rf /data/heeking/apache-tomcat-8.0.26/logs/catalina.out 
##########开始数据备份#################################
echo “开始备份数据库”
mysqldump --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_account >heeking_account.sql
mysqldump --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_role >heeking_role.sql
mysqldump --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_gamedata >heeking_gamedata.sql
tar -zcvf db_back.tar.gz *.sql
rm -rf *.sql
echo "开始备份程序"
tar czvf webapps.tar.gz -C /data/heeking/apache-tomcat-8.0.26 webapps
########备份完成开始更新#########################################
mkdir -p /usr/local/down_update
cd /usr/local/down_update
rm -rf *.sql
rm -rf db
tar -zxvf db.tar.gz
echo "开始导入数据库"

if [ $account -eq 1 ]
then
  mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_account < heeking_account.sql
elif [ $account -eq 0 ]
then
   echo "不更新 account">1.txt
fi

if [ $gamedata -eq 1 ]
then
  opendate=$(mysql -usaintdbcon -p87ohB07jG7 -h${dbip} -e "select v from heeking_gamedata.game_param where id = 45" | head -n 2 | tail -n 1)
  mysqldump --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_gamedata server_list > server_list.sql
  mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_gamedata < heeking_gamedata.sql
  mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_gamedata < server_list.sql  
  mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} -e "update heeking_gamedata.game_param set v='${opendate}' where id=45 "
elif [ $gamedata -eq 0 ]
then
   echo "不更新 gamedata" >>1.txt
fi

if [ $role -eq 1 ]
then
  mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} heeking_role < heeking_role.sql
elif [ $role -eq 0 ]
then
   echo "不更新 role">>1.txt
fi
echo "导入数据库完成"

if [ $gameserver -eq 1 ]
then
   tar -zxvf gameserver.tar.gz
   rm -rf /data/heeking/apache-tomcat-8.0.26/webapps/gameserver
   echo "开始部署程序"
   mv gameserver /data/heeking/apache-tomcat-8.0.26/webapps/
   echo "部署完成"
elif [ $gameserver -eq 0 ]
then
   echo "不更新 gameserver">>1.txt
fi

echo "部署完成"
echo "开始启动服务器"
/data/heeking/apache-tomcat-8.0.26/bin/startup.sh