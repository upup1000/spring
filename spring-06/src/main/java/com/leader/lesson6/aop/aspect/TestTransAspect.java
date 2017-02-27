package com.leader.lesson6.aop.aspect;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zss
 */
@Component
@Aspect
public class TestTransAspect {

	@Autowired
	private DataSource dataSource;

	@Around("execution(* com.leader.lesson6.service.*Service.save*(..)) || "
			+ "execution(* com.leader.lesson6.service.*Service.update*(..)) ||"
			+ "execution(* com.leader.lesson6.service.*Service.delete*(..)) ||"
			+ "execution(* com.leader.lesson6.service.*Service.create*(..))")
	public Object transWriteAspect(ProceedingJoinPoint pjp) {
		Object rst = null;
		Connection conn = null;
		try {
			conn = getConn();
			conn.setAutoCommit(false);
			rst = pjp.proceed();
			conn.commit();
			System.out.println("executed success, commit.");
		} catch (Throwable throwable) {
			if (throwable instanceof RuntimeException) {
				doRollBack(conn, throwable);
			} else {
				doCommit(conn, throwable);
			}
		} finally {
			doClose(conn);
		}
		return rst;
	}

	@Around("execution(* com.leader.lesson6.service.*Service.find*(..)) || "
			+ "execution(* com.leader.lesson6.service.*Service.query*(..))")
	public Object transReadAspect(ProceedingJoinPoint pjp) {
		Object rst = null;
		Connection conn = null;
		try {
			conn = getConn();
			conn.setReadOnly(true);
			conn.setAutoCommit(true);
			rst = pjp.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		} finally {
			doClose(conn);
		}
		return rst;
	}
	private Connection getConn() {
		Connection conn = ThreadLocalConn.getConn();
		try {
			if (conn == null || conn.isClosed()) {
				try {
					conn = dataSource.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ThreadLocalConn.setConn(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	private void doRollBack(Connection conn, Throwable throwable) {
		try {
			System.out.println("is RuntimeException : " + throwable.getMessage() + " ,rollback.");
			conn.rollback();
		} catch (SQLException e) {
		}
	}

	private void doCommit(Connection conn, Throwable throwable) {
		try {
			System.out.println("not RuntimeException :" + throwable.getMessage() + " ,commit.");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doClose(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
}
