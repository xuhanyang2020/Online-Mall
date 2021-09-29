package com.example.store.service.Imp;

import com.example.store.dao.GoodsDao;
import com.example.store.dao.Imp.GoodsDaoImp;
import com.example.store.domain.Goods;
import com.example.store.service.GoodsService;

import java.util.ArrayList;
import java.util.List;

public class GoodsServiceImp implements GoodsService {

    GoodsDao goodsDao = new GoodsDaoImp();
    @Override
    public List<Goods> queryAll() {
        List<Goods> list = goodsDao.findAll();
        return list;
    }

    @Override
    public List<Goods> queryByStartEnd(long start, long end) {
        List<Goods> list = goodsDao.findStartEnd(start, end);
        return list;
    }

    @Override
    public Goods queryDetail(long goodsid) {
        return goodsDao.findByPk(goodsid);
    }
}
