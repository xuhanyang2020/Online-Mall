package com.example.store.service.Imp;

import com.example.store.domain.OrderLineItem;
import com.example.store.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImpTest {
    OrderService service;
    @BeforeEach
    void setUp() {
        service = new OrderServiceImp();
    }

    @AfterEach
    void tearDown() {
        service = null;
    }

    @Test
    void submitOrders() {
        List<Map<String, Object>> cart = new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        item1.put("goodsid",3L);
        item1.put("quantity",2);
        cart.add(item1);
        Map<String, Object> item2 = new HashMap<>();
        item2.put("goodsid",8L);
        item2.put("quantity",3);
        cart.add(item2);
        String ordersid = service.submitOrders(cart);

        assertNotNull(ordersid);


    }
}