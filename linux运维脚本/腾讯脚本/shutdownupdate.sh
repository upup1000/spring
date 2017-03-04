#!/bin/bash
#是否升级gamedata库
gamedata=$1
#是否升级character判断
character=$2
#是否升级account判断
account=$3
#是否升级gamelog库
gamelog=$4
#版本号
version=$5
echo "开始 /usr/local/jhserver 目录 赋予读写执行权限"
chmod -R 700 /usr/local/jhserver*
cd /usr/local/
echo "开始下载服务器程序升级包（webapps.zip）"
rm -rf /usr/local/webapps.zip*
rm -rf /usr/local/gamedata*
lftp -u app100625638,zuijian123 cvmftp.tencentyun.com:53000 -e 'get '${version}'/webapps.zip; quit'
if [ $gamedata -eq 1 ]
then
     echo "开始下载数据库升级包（gamedata_update。zip）。。"
	 lftp -u app100625638,zuijian123 cvmftp.tencentyun.com:53000 -e 'get '${version}'/gamedata.zip; quit'
	 unzip gamedata.zip
elif [ $gamedata -eq 0 ]
then
     echo "不下载数据库升级包（gamedata_update。zip）。"
fi

if [ $character -eq 1 ]
then
     echo "开始下载数据库升级包（character_update.sql）。。"
	  lftp -u app100625638,zuijian123 cvmftp.tencentyun.com:53000 -e 'get '${version}'/character.sql; quit'
elif [ $character -eq 0 ]
then
     echo "不下载数据库升级包（character_update.sql）。。"
fi

if [ $account -eq 1 ]
then
     echo "开始下载数据库升级包（account_update.sql）。。"
	  lftp -u app100625638,zuijian123 cvmftp.tencentyun.com:53000 -e 'get '${version}'/account.sql; quit'
elif [ $account -eq 0 ]
then
     echo "不下载数据库升级包（account_update.sql）."
fi

if [ $gamelog -eq 1 ]
then
     echo "开始下载数据库升级包 (gamelog_update.sql)."
	 lftp -u app100625638,zuijian123 cvmftp.tencentyun.com:53000 -e 'get '${version}'/gamelog.sql; quit'
elif [ $gamelog -eq 0 ]
then
     echo "不下载数据库升级包 (gamelog_update.sql)"
fi
echo "升级内容下载完毕。。"
#######停止游戏服务器######
 echo "开始关闭游戏服务"
 curl http://127.0.0.1/gameserverweb/gm/ip_shutdown.jsp&
 echo "开始等待服务器关闭300秒延迟"
 sleep 300
##########强制关闭服务器################################
echo "开始强制关闭游戏服务器"
kill -9 `ps aux |grep -i gameserver |grep -v grep |awk '{print $2}'`
#############数据库备份#######################
rm -rf /usr/local/dbback/
mkdir -p /usr/local/dbback/${version}back
cd /usr/local/dbback/${version}back
echo "=======================================account start=======================================";
mysqldump account > account.sql
echo "=======================================character start=======================================";
mysqldump character > character.sql  
echo "=======================================gamelog start=======================================";
mysqldump gamelog > gamelog.sql
echo "=======================================gamedata start=======================================";
mysqldump gamedata > gamedata.sql
mysqldump gamedata server_list > server_list.sql
mysqldump gamedata lineserver > lineserver.sql
mysqldump gamedata serverid_transition > serverid_transition.sql
tar -zcvf dbback.tar.gz *.sql
echo "======================================db over=======================================";
tar -zcvf  gameserver.tar.gz /usr/local/jhserver/gameserver/
echo "======================================程序打包完毕=======================================";

cd /usr/local
echo "执行数据库升级操作"
if [ $gamedata -eq 1 ]
then
     echo "开始升级gamedata。。。"
     mysql gamedata <gamedata.sql
	 mysql gamedata < /usr/local/dbback/${version}back/server_list.sql;
	 mysql gamedata < /usr/local/dbback/${version}back/lineserver.sql;
	 mysql gamedata < /usr/local/dbback/${version}back/serverid_transition.sql;
	 mysql -e " update gamedata.server_list set flashver=flashver+1 ";
     elif [ $gamedata -eq 0 ]
then
     echo "不升级gamedata"
fi

if [ $character -eq 1 ]
then
     echo "开始升级character"
     mysql character < character.sql
     elif [ $character -eq 0 ]
then
     echo "不升级character"
fi

if [ $account -eq 1 ]
then
     echo "开始升级account"
     mysql account < account.sql
     elif [ $account -eq 0 ]
then
     echo "不升级account"
fi
if [ $gamelog -eq 1 ]
then
     echo "开始升级account"
     mysql gamelog < gamelog.sql
     elif [ $account -eq 0 ]
then
     echo "不升级gamelog"
fi
sleep 5
cd /usr/local/
echo "开始更新服务器程序。。。"
mv webapps.zip /usr/local/jhserver/gameserver/
cd /usr/local/jhserver/gameserver/
rm -rf /usr/local/jhserver/gameserver/webapps
unzip -o webapps.zip
rm -rf /usr/local/jhserver/gameserver/work/Catalina/localhost/*
rm -fr /usr/local/jhserver/gameserver/logs/*
chmod 777 /usr/local/jhserver/gameserver/bin/*.sh
echo "开始启动游戏服务器。。。"
/usr/local/jhserver/gameserver/bin/startup.sh 