package com.example.store.dao.Imp;

import com.example.store.dao.OrderLineItemDao;
import com.example.store.domain.Goods;
import com.example.store.domain.OrderLineItem;
import com.example.store.domain.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineItemDaoImpTest {
    OrderLineItemDao dao;
    @BeforeEach
    void setUp() {
        dao = new OrderLineItemDaoImp();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void findByPk() {
        OrderLineItem orderLineItem = dao.findByPk(1);
        assertNotNull(orderLineItem);
        System.out.println(orderLineItem);
    }

    @Test
    void findAll() {

    }

    @Test
    void create() {
        OrderLineItem orderLineItem = new OrderLineItem();
        //orderLineItem.setId(1);
        orderLineItem.setQuantity(23);
        orderLineItem.setSubTotal(12.38);
        orderLineItem.setOrders(new Orders());
        orderLineItem.getOrders().setId("50");
        orderLineItem.setGoods(new Goods());
        orderLineItem.getGoods().setId(14);
        dao.create(orderLineItem);
    }

    @Test
    void modify() {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setId(107);
        orderLineItem.setQuantity(23);
        orderLineItem.setSubTotal(12.5);
        orderLineItem.setOrders(new Orders());
        orderLineItem.getOrders().setId("1");
        orderLineItem.setGoods(new Goods());
        orderLineItem.getGoods().setId(10);
        dao.modify(orderLineItem);
    }

    @Test
    void remove() {
        dao.remove(100);
    }
}