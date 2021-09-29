package com.example.store.service;

import com.example.store.dao.GoodsDao;
import com.example.store.domain.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> queryAll();

    List<Goods> queryByStartEnd(long start, long end);

    Goods queryDetail(long goodsid);
}
