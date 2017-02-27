package com.leader.lesson6.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.leader.lesson6.bean.Order;

/**
 * @author zss
 */
public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setProductName(rs.getString("product_name"));
        order.setPrice(rs.getDouble("price"));
        return order;
    }
}
