package com.example.store.dao.Imp;

import com.example.store.dao.OrdersDao;
import com.example.store.domain.Orders;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class OrdersDaoImpTest {
    OrdersDao dao;

    @BeforeEach
    void setUp(){
        dao = new OrdersDaoImp();
    }

    @AfterEach
    void tearDown(){
        dao = null;
    }

    @Test
    void findByPk() {
        Orders orders = dao.findByPk("1");
        assertEquals(orders.getStatus(), 1);
        assertEquals(orders.getOrderDate().getTime(),20200820);
    }

    @Test
    void findAll() {
        List<Orders> l = dao.findAll();
        assertEquals(1,l.size());
    }

    @Test
    void create() {
        Orders orders = new Orders();
        orders.setId("50");
        orders.setOrderDate(new Date(20200820));
        orders.setTotal(65.3);
        orders.setStatus(1);
        dao.create(orders);
    }

    @Test
    void modify() {
        Orders orders = new Orders();
        orders.setId("100");
        orders.setOrderDate(new Date(20200820));
        orders.setTotal(4.9);
        orders.setStatus(1);
        dao.modify(orders);
    }

    @Test
    void remove() {
        dao.remove("1");
    }
}