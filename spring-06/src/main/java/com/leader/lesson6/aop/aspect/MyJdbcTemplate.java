package com.leader.lesson6.aop.aspect;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.util.Assert;
/**
 * @author zss
 */
public class MyJdbcTemplate extends JdbcTemplate {
	public MyJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
	@Override
	public <T> T execute(ConnectionCallback<T> action) throws DataAccessException {
		Assert.notNull(action, "Callback object must not be null");
		T rst = null;
		try {
			Connection conToUse = ThreadLocalConn.getConn();
			rst = action.doInConnection(conToUse);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rst;
	}
	@Override
	public <T> T execute(StatementCallback<T> action) throws DataAccessException {
		Assert.notNull(action, "Callback object must not be null");
		T result = null;
		Statement stmt = null;
		try {
			Connection conToUse = ThreadLocalConn.getConn();
			stmt = conToUse.createStatement();
			applyStatementSettings(stmt);
			Statement stmtToUse = stmt;
			result = action.doInStatement(stmtToUse);
			handleWarnings(stmt);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
