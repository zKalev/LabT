package com.labirinty.zkalev.services;

import java.util.List;

import com.labirinty.zkalev.dao.model.GoodsInStock;

public interface GoodsService {

	public List<GoodsInStock> getAllGoods();

	public void save(GoodsInStock gis);

	public void delete(GoodsInStock gis);

	public void update(GoodsInStock gis);

	public boolean exist(GoodsInStock gis);
}
