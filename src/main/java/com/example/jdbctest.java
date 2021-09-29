package com.example;

import com.example.db.core.JdbcTemplate;
import com.example.store.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class jdbctest {
    public static void main(String[] args) throws ClassNotFoundException {
        List<Customer> list = new ArrayList<>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "select * from Customers where id = ?";
        // Use lambda replace anonymous class
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "1");
            return ps;
        }, rs -> {
            /*Customer customer = new Customer();
            customer.setId(rs.getString("id"));
            customer.setName(rs.getString("name"));
            customer.setPassword(rs.getString("password"));
            customer.setAddress(rs.getString("address"));
            customer.setPhone(rs.getString("phone"));
            customer.setBirthday(new Date(rs.getLong("id")));
            list.add(customer);
             */
        });
        System.out.println(list.size());
    }
}
