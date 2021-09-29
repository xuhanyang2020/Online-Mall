package com.example.store.dao.Imp;

import com.example.db.core.JdbcTemplate;
import com.example.db.core.PreparedStatementCreator;
import com.example.db.core.RowCallbackHandler;
import com.example.store.dao.CustomerDao;
import com.example.store.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDaoImpJdbc implements CustomerDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    @Override
    public Customer findByPk(String pk) {
        List<Customer> list = new ArrayList<>();
        String sql = "select * from Customers where id = ?";
        // Use lambda replace anonymous class
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pk);
            return ps;
        }, rs -> {
            populate(list, rs);
        });
        if (list.size() == 1) return list.get(0);
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "select * from Customers";
        // Use lambda replace anonymous class
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps;
        }, rs -> {
            populate(list, rs);
        });
        return list;
    }
    private void populate (List<Customer> list, ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getString("id"));
        customer.setName(rs.getString("name"));
        customer.setPassword(rs.getString("password"));
        customer.setAddress(rs.getString("address"));
        customer.setPhone(rs.getString("phone"));
        customer.setBirthday(new Date(rs.getLong("id")));
        list.add(customer);
    }

    @Override
    public void create(Customer customer) {
        String sql = "INSERT INTO Customers VALUES(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, customer.getId());
                ps.setString(2,customer.getName());
                ps.setString(3,customer.getPassword());
                ps.setString(4,customer.getAddress());
                ps.setString(5,customer.getPhone());
                ps.setLong(6,customer.getBirthday().getTime());
                return ps;
            }
        });
    }

    @Override
    public void modify(Customer customer) {
        String sql = "UPDATE Customers SET name= ?, password = ?, address = ?, phone = ?, birthday = ? where id = ?";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(6, customer.getId());
                ps.setString(1,customer.getName());
                ps.setString(2,customer.getPassword());
                ps.setString(3,customer.getAddress());
                ps.setString(4,customer.getPhone());
                ps.setLong(5,customer.getBirthday().getTime());
                return ps;
            }
        });
    }

    @Override
    public void remove(String pk) {
        String sql = "DELETE FROM Customers WHERE id = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,pk);
            return ps;
        });
    }
}
