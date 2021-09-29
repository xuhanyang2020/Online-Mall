package com.example.store.dao;

import com.example.store.domain.OrderLineItem;

import java.util.List;

public interface OrderLineItemDao {
    OrderLineItem findByPk(long pk);

    List<OrderLineItem> findAll();

    void create(OrderLineItem orderLineItem);

    void modify(OrderLineItem orderLineItem);

    void remove(long pk);
}
