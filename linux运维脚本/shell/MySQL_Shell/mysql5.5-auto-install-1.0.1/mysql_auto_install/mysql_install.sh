#!/bin/bash
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin
export PATH

#read config
. ./conf/config

#read script
. ./script/check.sh
. ./script/main_install.sh

#check run user
if [[ `id -u` != "0" ]]
then
	echo "Error: You must be root to run this script!"
	exit 1
fi

#check number of cpu cores
CPU_CORE=`awk '/processor/{a++}END{print a}' /proc/cpuinfo`


#print infomation
cat <<EOF
                +---------------------------------+
               /|                                /| 
              +---------------------------------+ |
              | |                               | |
              | +---- MYSQL AUTO INSTALL -------|-+
              |/                                |/  
              +_____Version: 1.0.6______________+
             /                                 / 
            /     QQ group:31013074           /              
           /_________________________________/
EOF


install_head(){
	read -p "If you don't read conf/config, Press N to exit or  Press Y to continue:" INSTALL_Y_N
	case $INSTALL_Y_N in
	Y|y)
		install_main
	;;
	n|N)
		exit 0
	;;
	*)
		install_head
	;;
	esac
} 
install_head
