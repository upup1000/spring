package com.leader.lesson6.aop.aspect;

import java.sql.Connection;
/**
 * @author zss
 */
public class ThreadLocalConn {
    private static final ThreadLocal<Connection> localConn = new ThreadLocal<Connection>();
    public static void setConn(Connection connection) {
        Connection localconnection= localConn.get();
        if (localconnection == null) {
            localConn.set(connection);
        }
    }
    public static Connection getConn() {
    	Connection localconnection = localConn.get();
        if (localconnection != null) {
            return localconnection;
        }
        return null;
    }

    public static Connection removeConn() {
    	Connection localconnection=getConn();
    	localConn.set(null);
        return localconnection;
    }

}

