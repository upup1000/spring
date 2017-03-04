#!/bin/bash

check_result(){
	if [ $1 != "0" ]
	then
		echo "ERROR!!!! exit mysql_auto_install."
		exit 1
	fi
}

check_user_group(){
	CHECK_USER=`cat /etc/passwd|awk -F: '{a[$1]}END{if("'$MYSQL_USER'" in a){print "yes"}else print "no"}'`
	CHECK_GROUP=`cat /etc/passwd|awk -F: '{a[$1]}END{if("'$MYSQL_GROUP'" in a){print "yes"}else print "no"}'`
	
	if [[ $CHECK_USER == "no" && $CHECK_GROUP == "no" ]]
	then
		useradd -M -s  /sbin/nologin $MYSQL_USER
	elif [[ $CHECK_USER == "yes" && $CHECK_GROUP == "no" ]]
	then
		groupadd $MYSQL_GROUP
		usermod -G $MYSQL_GROUP $MYSQL_USER
	elif [[ $CHECK_USER == "no" && $CHECK_GROUP == "yes" ]]
	then
		useradd -M -g $MYSQL_GROUP -s /sbin/nologin $MYSQL_USER
	fi	
}

check_file(){
	if [ -e $1 ]
	then
		read  -p "$1 is already exists,Press D to deletet ,S to save,q to exit:" CHECK_FILE
	
		case $CHECK_FILE in 
		d|D)
			rm -rf $1
		;;
		s|S)
			mkdir -p $MYSQL_BACK
			mv $1 $MYSQL_BACK/`echo $1|sed 's/.*\/\([^\]\+$\)/\1/'`.`date +%y%m%d%H%M%S`
			check_result $?
			echo "Save $1 to $MYSQL_BACK$1.`date +%y%m%d%H%M%S`"
			sleep 2
		;;
		q|Q)
			exit 0
		;;
		*)
			check_file $1
		;;
		esac

	fi
}

