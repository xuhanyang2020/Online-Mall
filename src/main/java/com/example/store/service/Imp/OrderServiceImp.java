package com.example.store.service.Imp;

import com.example.store.dao.GoodsDao;
import com.example.store.dao.Imp.GoodsDaoImp;
import com.example.store.dao.Imp.OrderLineItemDaoImp;
import com.example.store.dao.Imp.OrdersDaoImp;
import com.example.store.dao.OrderLineItemDao;
import com.example.store.dao.OrdersDao;
import com.example.store.domain.Goods;
import com.example.store.domain.OrderLineItem;
import com.example.store.domain.Orders;
import com.example.store.service.OrderService;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImp implements OrderService {

    GoodsDao goodsDao = new GoodsDaoImp();
    OrdersDao ordersDao = new OrdersDaoImp();
    OrderLineItemDao orderLineItemDao = new OrderLineItemDaoImp();

    @Override
    public String submitOrders(List<Map<String, Object>> cart) {

        Orders orders = new Orders();

        Date date = new Date();
        String ordersid = String.valueOf(date.getTime()) + String.valueOf((int)(Math.random() * 100));
        orders.setId(ordersid);
        // not shipped status 1
        orders.setStatus(1);
        orders.setOrderDate(date);
        orders.setTotal(0.0f);
        // create orders first as the order_id in orderlineitem referencing the order_id
        ordersDao.create(orders);
        System.out.println(orders.getId());
        double total = 0.0;
        for (Map<String, Object> item : cart){
            Long goodsid = (Long) item.get("goodsid");
            Integer quantity = (Integer) item.get("quantity");
            Goods goods = goodsDao.findByPk(goodsid);

            total += quantity * goods.getPrice();

            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setQuantity(quantity);
            orderLineItem.setGoods(goods);
            orderLineItem.setOrders(orders);
            orderLineItem.setSubTotal(quantity * goods.getPrice());
            orderLineItemDao.create(orderLineItem);
        }

        orders.setTotal(total);
        System.out.println(orders.getId());
        ordersDao.modify(orders);

        return ordersid;
    }
}
