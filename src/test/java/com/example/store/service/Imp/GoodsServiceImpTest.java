package com.example.store.service.Imp;

import com.example.store.domain.Goods;
import com.example.store.service.GoodsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoodsServiceImpTest {

    GoodsService service;
    @BeforeEach
    void setUp() {
        service = new GoodsServiceImp();
    }

    @AfterEach
    void tearDown() {
        service = null;
    }

    @Test
    void queryAll() {
        List<Goods> l = service.queryAll();
        assertEquals(l.size(), 36);
    }

    @Test
    void queryByStartEnd() {
        List<Goods> l = service.queryByStartEnd(10,20);
        assertEquals(l.size(), 10);
    }

    @Test
    void queryDetail() {
        Goods goods = service.queryDetail(3L);
        assertEquals(goods.getCpuBrand(),"Intel ");
    }
}