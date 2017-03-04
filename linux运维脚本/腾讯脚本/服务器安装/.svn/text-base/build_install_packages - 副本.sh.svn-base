#!/bin/bash
tar -C /usr/local/ -zcvf jhserver.tar.gz  jhserver --exclude=jhserver/gameserver/webapps/gameserverweb/WEB-INF/runtimejar/* --exclude=jhserver/gameserver/logs/*
mysqldump --opt -d character>character.sql
mysqldump --opt -d account >account.sql
mysqldump --opt -d gamelog >gamelog.sql
mysqldump gamedata server_list > server_list.sql;
mysqldump gamedata lineserver > lineserver.sql;
mysql -e "delete  from gamedata.server_list "
mysql -e "delete  from gamedata.lineserver "
mysqldump gamedata >gamedata.sql
mysql gamedata < server_list.sql;
mysql gamedata < lineserver.sql;
tar -zcvf db.tar.gz character.sql account.sql gamelog.sql gamedata.sql
