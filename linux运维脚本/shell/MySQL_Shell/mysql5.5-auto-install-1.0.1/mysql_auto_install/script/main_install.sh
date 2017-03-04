#!/bin/bash
install_down(){
	if [ ! -f /usr/local/src/$MYSQL_VERSION.tar.gz ]
	then
		wget http://dev.mysql.com/get/Downloads/MySQL-5.5/$MYSQL_VERSION.tar.gz/from/http://mirror.services.wisc.edu/mysql/  -P /usr/local/src
	fi
}

install_end(){
	service mysqld start
	MYSQL_CHECK=`echo "show databases"|mysql -uroot`
	if [ "x$MYSQL_CHECK" != "x" ]
	then
		cat << EOF
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                   mysql is install sucessfully                                   
 -------------------------------------------------------------------------------- 
      MySQL SCRIPTS               /etc/init.d/mysqld start                        
      MySQL DATAPATH              $MYSQL_DATA
      MYSQL PATH                  $MYSQL_PREFIX
 --------------------------------------------------------------------------------
      by:qinhantangsongyuan       qq group:31013074                           
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
EOF
	else
		echo "mysql is install fails !!!! "
	fi
}

install_main(){
	yum -y install gcc gcc-c++ cmake  ncurses-devel bison 
	check_user_group
	check_file $MYSQL_PREFIX
	check_file "/etc/init.d/mysqld"
	check_file $MYSQL_CNF
	check_file $MYSQL_DATA
	
	install_down
	cd /usr/local/src
	tar xvf $MYSQL_VERSION.tar.gz
	check_result $? 
	cd $MYSQL_VERSION

	CFLAGS="-O3" CXX=gcc
	CXXFLAGS="-O3 -felide-constructors -fno-exceptions -fno-rtti"
	$MYSQL_CMAKE
	check_result $?
        
	make -j $CPU_CORE
	check_result $?

	make install
	check_result $?

	chown -R $MYSQL_GROUP:$MYSQL_USER $MYSQL_PREFIX
	
	mv /usr/local/src/$MYSQL_VERSION/support-files/my-medium.cnf $MYSQL_CNF
	mv /usr/local/src/$MYSQL_VERSION/support-files/mysql.server /etc/init.d/mysqld
	chmod +x /etc/init.d/mysqld 
	chkconfig --level 345 mysqld on

	$MYSQL_PREFIX/scripts/mysql_install_db --user=$MYSQL_USER \
	--defaults-file=$MYSQL_CNF \
	--basedir=$MYSQL_PREFIX \
	--datadir=$MYSQL_DATA 
	
	echo "export PATH=/usr/local/mysql-5.5.21/bin:$PATH" >>/etc/profile
	source /etc/profile
	install_end
}
