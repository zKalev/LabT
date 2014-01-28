package com.labirinty.zkalev.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.labirinty.zkalev.dao.GoodsDao;
import com.labirinty.zkalev.dao.model.GoodsInStock;
import com.labirinty.zkalev.services.GoodsService;

public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	@Override
	public List<GoodsInStock> getAllGoods() {

		return (List<GoodsInStock>) goodsDao.findAll();
	}

	@Override
	public void save(GoodsInStock gis) {

		goodsDao.save(gis);
	}

	@Override
	public void delete(GoodsInStock gis) {

		GoodsInStock goodsInStock = goodsDao.findByName(gis.getName());

		goodsDao.delete(goodsInStock);
	}

	@Override
	public void update(GoodsInStock gis) {
		if (gis != null && gis.getName() != null
				&& gis.getAmountInstock() != null) {

			goodsDao.updateByName(gis.getName(), gis.getAmountInstock());
		}
	}

	@Override
	public boolean exist(GoodsInStock gis) {
		if (gis == null || gis.getName() == null
				|| gis.getAmountInstock() == null)
			return false;
		GoodsInStock goodsInStock = goodsDao.findByName(gis.getName());
		if (goodsInStock == null || goodsInStock.getId() == null)
			return false;
		Integer index = goodsInStock.getId();
		return goodsDao.exists(index);
	}
}
