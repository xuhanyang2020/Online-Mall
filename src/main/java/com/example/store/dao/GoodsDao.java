package com.example.store.dao;

import com.example.store.domain.Customer;
import com.example.store.domain.Goods;

import java.util.List;

public interface GoodsDao {
    Goods findByPk(long pk);

    List<Goods> findAll();

    void create(Goods goods);

    void modify(Goods goods);

    void remove(long pk);

    /**
     *
     * @param start 分页查询
     * @param end
     * @return
     */
    List<Goods> findStartEnd(long start, long end);
}
