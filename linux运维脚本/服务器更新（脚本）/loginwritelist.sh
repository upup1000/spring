#!/bin/bash
dbip=$1
###1开启0关闭###
isopen=$2
###如果开启时给玩家的提示
tips='服务器处于维护期间，预计11点开服，尽情期待'
mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} -e "update heeking_gamedata.game_param set v=${isopen} where id=68"
mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} -e "update heeking_gamedata.game_param set v='${tips}' where id=69"
mysql --default-character-set=utf8 -usaintdbcon -p87ohB07jG7 -h${dbip} -e "update heeking_gamedata.dbcachelist set version=version+1 where cachename='game_param'"