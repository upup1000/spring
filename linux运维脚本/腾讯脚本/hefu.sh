#!/bin/bash
zhengip=$1
zhengus=$2
zhengps=$3
fuip=$4
fuus=$5
fups=$6
zhenggameip=$7
fugameip=$8
#为需要合服的数据库服务器安装java环境
#scp -P36000 -r /root/installpackage/jdk1.6.0_23.tar.gz root@${zhengip}:/tmp/
#ssh -p36000 ${zhengip} "/root/gamescript/install_jdk.sh" 
#echo "java环境部署完毕"
echo "复制之前合服程序及备份"
ssh -p36000 ${zhengip} "mv /root/helo/ helo_bak && mkdir -pv /root/helo"
echo "负服为正服授权"
ssh -p36000 ${fuip} "mysql -e \"GRANT ALL PRIVILEGES ON *.* TO 'hefu'@'${zhengip}' IDENTIFIED BY 'hefu' WITH GRANT OPTION\""
ssh -p36000 ${zhengip} "mysql -e \"GRANT ALL PRIVILEGES ON *.* TO 'hefu'@'${zhengip}' IDENTIFIED BY 'hefu' WITH GRANT OPTION\""
echo "下载合服程序并开始合服"
ssh -p36000 "sed -i '/^max_binlog_cache_size/ {s/.*/max_binlog_cache_size = 4G/}' /data/mysql/3306/my.cnf && /etc/init.d/mysqld restart && mkdir /root/hfbak && mkdir -pv /root/helo/hefu && mkdir -pv /root/helo/bak && cd /root/helo && lftp -u app100625638,zuijian123 cvmftp.tencentyun.com:53000 -e 'get hefu/hf.jar; quit' && lftp -u app100625638,zuijian123 cvmftp.tencentyun.com:53000 -e 'get hefu/hf.sh; quit' && lftp -u app100625638,zuijian123 cvmftp.tencentyun.com:53000 -e 'get hefu/mysql-connector-java-5.1.8-bin.jar; quit' && chmod +x hf.sh && /root/helo/hf.sh '${zhengip}' '${zhengus}' '${zhengps}' '${fuip}' '${fuus}' '${fups}'"
echo "导出负服的serverlist"
ssh -p36000 ${fuip} "mysqldump --no-create-info gamedata server_list > /tmp/server_list.sql"
ssh -p36000 ${fuip} "mysqldump --no-create-info gamedata serverid_transition > /tmp/serverid_transition.sql"

echo "开始将负服serverlist拷贝到本地
scp -P36000 {fuip}:/tmp/server_list.sql /tmp
scp -P36000 {fuip}:/tmp/serverid_transition.sql /tmp
echo "从本地拷贝到正服" 
scp -P36000 /tmp/server_list.sql root@$zhengip:/tmp
scp -P36000 /tmp/serverid_transition.sql root@$zhengip:/tmp
#导入serverlist
ssh -p36000 ${zhengip} "mysql gamedata < /tmp/server_list.sql"
ssh -p36000 ${zhengip} "mysql gamedata < /tmp/serverid_transition.sql"
echo "server_list合并完毕"
#删除本地 维护列表中的 负服ip
mysql  -ss -e"delete from serverlist.serverlist where gameIp='${fuip}'"
echo "本地负服IP删除完毕"
#更新 发货服务器
fahuo1Ip='10.142.52.24'
fahuo2Ip='10.190.161.33'
fahuo3Ip='10.190.160.197'
ssh -p36000 ${fahuo1Ip} "mysql -e \"update qq_fahuo.qq_serverconfig set ip='${zhenggameip}' where ip='${fugameip}'\""
ssh -p36000 ${fahuo2Ip} "mysql -e \"update qq_fahuo.qq_serverconfig set ip='${zhenggameip}' where ip='${fugameip}'\""
ssh -p36000 ${fahuo3Ip} "mysql -e \"update qq_fahuo.qq_serverconfig set ip='${zhenggameip}' where ip='${fugameip}'\""
ssh -p36000 ${fahuo1Ip} "mysql -e \"update qq_fahuo.dbcachelist set version=version+1 where cachename='qqserverconfig'\""
ssh -p36000 ${fahuo2Ip} "mysql -e \"update qq_fahuo.dbcachelist set version=version+1 where cachename='qqserverconfig'\""
ssh -p36000 ${fahuo3Ip} "mysql -e \"update qq_fahuo.dbcachelist set version=version+1 where cachename='qqserverconfig'\""
echo "发货服务器列表更新完毕"
