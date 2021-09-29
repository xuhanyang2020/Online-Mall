package com.example.store.dao.Imp;

import com.example.store.dao.GoodsDao;
import com.example.store.domain.Goods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoodsDaoImpTest {

    GoodsDao dao;
    @BeforeEach
    void setUp() {
        dao = new GoodsDaoImp();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void findByPk() {
        List<Goods> list = dao.findStartEnd(3,10);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i ++){
            System.out.println(list.get(i));
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void populate() {
    }

    @Test
    void create() {
        Goods goods = new Goods();
        goods.setId(101);
        goods.setName("fantasic");
        dao.create(goods);
    }

    @Test
    void modify() {
        Goods goods = dao.findByPk(7);
        goods.setCpuBrand("Apple M1 chip");
        System.out.println(goods);
        dao.modify(goods);
    }

    @Test
    void remove() {
        dao.remove(100);
    }
}