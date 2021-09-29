package com.example.store.dao.Imp;

import com.example.db.core.JdbcTemplate;
import com.example.db.core.PreparedStatementCreator;
import com.example.db.core.RowCallbackHandler;
import com.example.store.dao.OrdersDao;
import com.example.store.domain.Goods;
import com.example.store.domain.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersDaoImp implements OrdersDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    @Override
    public Orders findByPk(String pk) {
        String sql = "select * from Orders where id = ?";
        List<Orders> list = new ArrayList<>();
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,pk);
            return ps;
        }, rs -> populate(list, rs));
        if (list.size() == 1) return list.get(0);
        return null;
    }

    @Override
    public List<Orders> findAll() {
        String sql = "select * from Orders";
        List<Orders> list = new ArrayList<>();
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps;
        }, rs -> populate(list, rs));
       return list;
    }

    private void populate(List<Orders> list, ResultSet rs) throws SQLException {
        Orders orders = new Orders();
        orders.setId(rs.getString("id"));
        orders.setOrderDate(new Date(rs.getLong("order_date")));
        orders.setStatus(rs.getInt("status"));
        orders.setTotal(rs.getFloat("total"));
        list.add(orders);
    }
    @Override
    public void create(Orders orders) {
        String sql = "INSERT INTO Orders VALUES(?,?,?,?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,orders.getId());
            ps.setLong(2,orders.getOrderDate().getTime());
            ps.setInt(3,orders.getStatus());
            ps.setDouble(4,orders.getTotal());
            return ps;
        });
    }

    @Override
    public void modify(Orders orders) {
        String sql = "UPDATE Orders SET order_date = ?, status = ?, total = ? WHERE id = ?";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(4,orders.getId());
                ps.setLong(1,orders.getOrderDate().getTime());
                ps.setInt(2,orders.getStatus());
                ps.setDouble(3,orders.getTotal());
                return ps;
            }
        });
    }

    @Override
    public void remove(String pk) {
        String sql = "DELETE FROM Orders WHERE id = ?";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,pk);
                return ps;
            }
        });
    }
}
