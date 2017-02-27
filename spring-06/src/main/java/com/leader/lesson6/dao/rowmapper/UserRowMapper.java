package com.leader.lesson6.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.leader.lesson6.bean.User;

/**
 * @author zss
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User u = new User();
        u.setId(rs.getLong("id"));
        u.setName(rs.getString("name"));
        u.setPass(rs.getString("pass"));
        u.setAge(rs.getInt("age"));
        return u;
    }
}
