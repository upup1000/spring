DATE=$(date +%Y-%m-%d)
grep 'Exception:' -A 20 /data/heeking/apache-tomcat-8.0.26/logs/catalina.out|grep 2016-05-06 -A 20 > /usr/tomcat/logs/outMonitorLog.log
