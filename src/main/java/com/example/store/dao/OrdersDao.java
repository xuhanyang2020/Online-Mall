package com.example.store.dao;

import com.example.store.domain.Goods;
import com.example.store.domain.Orders;

import java.util.List;

public interface OrdersDao {
    Orders findByPk(String pk);

    List<Orders> findAll();

    void create(Orders orders);

    void modify(Orders orders);

    void remove(String pk);
}
