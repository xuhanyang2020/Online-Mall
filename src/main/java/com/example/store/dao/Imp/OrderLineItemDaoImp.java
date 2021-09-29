package com.example.store.dao.Imp;

import com.example.db.core.JdbcTemplate;
import com.example.db.core.PreparedStatementCreator;
import com.example.db.core.RowCallbackHandler;
import com.example.store.dao.GoodsDao;
import com.example.store.dao.OrderLineItemDao;
import com.example.store.dao.OrdersDao;
import com.example.store.domain.Goods;
import com.example.store.domain.OrderLineItem;
import com.example.store.domain.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineItemDaoImp implements OrderLineItemDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    @Override
    public OrderLineItem findByPk(long pk) {
        String sql = "select * from OrderLineItems where id = ?";
        List<OrderLineItem> list = new ArrayList<>();
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1,pk);
            return ps;
        }, rs -> populate(list, rs));
        if (list.size() == 1) return list.get(0);
        return null;
    }

    @Override
    public List<OrderLineItem> findAll() {
        String sql = "select * from OrderLineItems";
        List<OrderLineItem> list = new ArrayList<>();
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps;
        }, rs -> populate(list, rs));
        return list;
    }

    private void populate(List<OrderLineItem> list, ResultSet rs) throws SQLException {
        OrderLineItem lineItem = new OrderLineItem();

        lineItem.setId(rs.getLong("id"));
        lineItem.setQuantity(rs.getInt("quantity"));
        lineItem.setSubTotal(rs.getFloat("sub_total"));

        Orders orders = new Orders();
        orders.setId(rs.getString("orderid"));
        lineItem.setOrders(orders);

        Goods goods = new Goods();
        goods.setId(rs.getLong("goodsid"));
        lineItem.setGoods(goods);

        list.add(lineItem);
    }
    @Override
    public void create(OrderLineItem orderLineItem) {
        String sql = "INSERT INTO OrderLineItems VALUES(?,?,?,?,?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1,orderLineItem.getId());
            ps.setLong(2,orderLineItem.getGoods().getId());
            ps.setString(3,orderLineItem.getOrders().getId());
            ps.setInt(4,orderLineItem.getQuantity());
            ps.setDouble(5,orderLineItem.getSubTotal());
            return ps;
        });
    }

    @Override
    public void modify(OrderLineItem orderLineItem) {
        String sql = "UPDATE OrderLineItems SET goodsid = ?, orderid = ?, quantity = ?, sub_total = ? WHERE id = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(5,orderLineItem.getId());
            ps.setLong(1,orderLineItem.getGoods().getId());
            ps.setString(2,orderLineItem.getOrders().getId());
            ps.setInt(3,orderLineItem.getQuantity());
            ps.setDouble(4,orderLineItem.getSubTotal());
            return ps;
        });
    }

    @Override
    public void remove(long pk) {
        String sql = "DELETE FROM OrderLineItems WHERE id = ?";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setLong(1,pk);
                return ps;
            }
        });
    }
}
