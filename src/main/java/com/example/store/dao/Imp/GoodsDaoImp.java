package com.example.store.dao.Imp;

import com.example.db.core.JdbcTemplate;
import com.example.db.core.PreparedStatementCreator;
import com.example.db.core.RowCallbackHandler;
import com.example.store.dao.GoodsDao;
import com.example.store.domain.Customer;
import com.example.store.domain.Goods;
import com.example.store.domain.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImp implements GoodsDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    @Override
    public Goods findByPk(long pk) {
        String sql = "select * from Goods where id = ?";
        List<Goods> list = new ArrayList<>();
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, pk);
            return ps;
        }, rs -> populate(list, rs));
        if(list.size() == 1) return list.get(0);
        return null;
    }

    @Override
    public List<Goods> findAll() {
        String sql = "select * from Goods";
        List<Goods> list = new ArrayList<>();
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps;
        }, rs -> populate(list, rs));
        return list;
    }
    public void populate(List<Goods> list, ResultSet rs) throws SQLException {
        Goods goods = new Goods();
        goods.setId(rs.getLong("id"));
        goods.setName(rs.getString("name"));
        goods.setPrice(rs.getFloat("price"));
        goods.setDescription(rs.getString("description"));
        goods.setBrand(rs.getString("brand"));
        goods.setCpuBrand(rs.getString("cpu_brand"));
        goods.setCpuType(rs.getString("cpu_type"));
        goods.setMemoryCapacity(rs.getString("memory_capacity"));
        goods.setHdCapacity(rs.getString("hd_capacity"));
        goods.setCardModel(rs.getString("card_model"));
        goods.setDisplaysize(rs.getString("displaysize"));
        goods.setImage(rs.getString("image"));
        list.add(goods);
    }

    @Override
    public void create(Goods goods) {
        String sql = "INSERT INTO Goods VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, goods.getId());
            ps.setString(2, goods.getName());
            ps.setDouble(3, goods.getPrice());
            ps.setString(4, goods.getDescription());
            ps.setString(5, goods.getBrand());
            ps.setString(6, goods.getCpuBrand());
            ps.setString(7, goods.getCpuType());
            ps.setString(8, goods.getMemoryCapacity());
            ps.setString(9, goods.getHdCapacity());
            ps.setString(10, goods.getCardModel());
            ps.setString(11, goods.getDisplaysize());
            ps.setString(12, goods.getImage());
            return ps;
        });
    }

    @Override
    public void modify(Goods goods1) {
        String sql = "UPDATE Goods SET name = ?, price = ?, description = ?, brand = ?, cpu_brand = ?, cpu_type = ?, memory_capacity = ?, hd_capacity = ?, card_model = ?, displaysize = ?, image = ? WHERE id = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(12, goods1.getId());
            ps.setString(1, goods1.getName());
            ps.setDouble(2, goods1.getPrice());
            ps.setString(3, goods1.getDescription());
            ps.setString(4, goods1.getBrand());
            ps.setString(5, goods1.getCpuBrand());
            ps.setString(6, goods1.getCpuType());
            ps.setString(7, goods1.getMemoryCapacity());
            ps.setString(8, goods1.getHdCapacity());
            ps.setString(9, goods1.getCardModel());
            ps.setString(10, goods1.getDisplaysize());
            ps.setString(11, goods1.getImage());
            return ps;
        });
    }

    @Override
    public void remove(long pk) {
        String sql = "DELETE FROM Goods WHERE id = ?";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setLong(1,pk);
                return ps;
            }
        });
    }

    @Override
    public List<Goods> findStartEnd(long start, long end) {
        List<Goods> list = new ArrayList<>();
        String sql = "SELECT * FROM Goods LIMIT ? OFFSET ?";
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setLong(1, (end - start));
                ps.setLong(2, start);
                return ps;
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                populate(list, rs);
            }
        });
        return list;
    }
}
