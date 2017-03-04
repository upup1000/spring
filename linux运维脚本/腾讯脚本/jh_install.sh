#!/bin/bash
tencentid=$1
serverid=$2
flashver=$3
deptid=1005

echo "=======================================开始构建安装目录=======================================";
cd /root;
rm -rf installtemp;
mkdir installtemp;
cd installtemp;
echo "=======================================构建安装目录完毕=======================================";

cd /root/installtemp;
rm -rf /tmp/jhinstall*
echo "ok" > /tmp/jhinstall1.txt
echo "=======================================开始下载数据库数据文件=======================================";
lftp -u app100625638,zuijian123 cvmftp.tencentyun.com:53000 -e 'get gameinstall/db.tar.gz; quit'
tar -zxvf db.tar.gz;
echo "=======================================下载数据库数据文件完毕=======================================";
echo "=======================================开始创建数据库=======================================";
mysql -e "create database if not exists gamedata";
mysql -e "create database if not exists \`character\`";
mysql -e "create database if not exists account";
mysql -e "create database if not exists gamelog";
echo "=======================================授权后台访问=======================================";
mysql -e "GRANT ALL PRIVILEGES ON *.* TO 'developer'@'10.204.146.146' IDENTIFIED BY 'helloworld' WITH GRANT OPTION";
echo "=======================================开始还原数据库=======================================";
mysql gamedata < gamedata.sql;
printf "gamedata导入完成\n";
mysql character < character.sql;
printf "character导入完成\n";
mysql account < account.sql;
printf "account导入完成\n";
mysql gamelog < gamelog.sql;
printf "gamelog导入完成\n";
echo "=======================================还原数据库完毕=======================================";
echo "ok" > /tmp/jhinstall2.txt
echo "=======================================开始构建游戏服务器结构=======================================";
cd /root/installtemp;
lftp -u app100625638,zuijian123 cvmftp.tencentyun.com:53000 -e 'get gameinstall/jhserver.tar.gz; quit'
tar -zxvf jhserver.tar.gz
rm -rf /usr/local/jhserver
mv jhserver /usr/local/
echo "=======================================清除pagepage缓存编译文件=======================================";
rm -rf /usr/local/jhserver/gameserver/work/Catalina/localhost;
echo "=======================================配置数据=======================================";
 domainName="s${tencentid}.app100625638.qqopenapp.com"
echo ${domainName}
sed -i "/server.sid=/ {s/.*/server.sid=${serverid}/}" /usr/local/jhserver/gameserver/conf/serverparam.properties
sed -i "/server.dept=/ {s/.*/server.dept=${deptid}/}" /usr/local/jhserver/gameserver/conf/serverparam.properties
sed -i "/server.isInteriorhttplog=/ {s/.*/server.isInteriorhttplog=true/}" /usr/local/jhserver/gameserver/conf/serverparam.properties
mysql -e "delete from gamedata.server_list where loginServerId!=${serverid}";
mysql -e "INSERT INTO gamedata.server_list VALUES ('${serverid}',3000,'${serverid}服','${domainName}','443','${domainName}','443','http://jh.178tc1.com/eflashdir/flashClient','${flashver}',NULL,'http://115.182.49.77:8011/ts100gift/toGiftCheck.do');";
mysql -e "delete from gamedata.lineserver";
mysql -e "INSERT INTO gamedata.lineserver VALUES ('${serverid}',1,'醉剑一线',1);";
mysql -e "INSERT INTO gamedata.lineserver VALUES ('${serverid}',2,'醉剑二线',1);";
mysql -e "INSERT INTO gamedata.lineserver VALUES ('${serverid}',3,'醉剑三线',1);";
mysql -e "INSERT INTO gamedata.serverid_transition VALUES ('${tencentid}','${serverid}','${tencentid}');";
echo "=======================================热加载开始=======================================";
#cd /root/installtemp;
#lftp -u app100625638,zuijian123 cvmftp.tencentyun.com:53000 -e 'get gameinstall/hotload.zip; quit'
# xzvf hotload.tar.gz;
#rm -rf /usr/local/hotload
#mv hotload /usr/local/
#\cp /usr/local/jhserver/gameserver/webapps/gameserverweb/WEB-INF/lib/jh-game.jar /usr/local/hotload/
#ntpdate 192.168.1.211
echo "=======================================校验时间完毕=======================================";
echo "=======================================所有安装已经就绪=======================================";
echo "ok" > /tmp/jhinstall3.txt
echo "=======================================清除安装文件=======================================";
rm -rf /root/serverinstalltemp
echo "=======================================清除安装文件完成=======================================";
