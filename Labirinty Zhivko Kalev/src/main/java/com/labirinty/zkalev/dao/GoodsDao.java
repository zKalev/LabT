package com.labirinty.zkalev.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.labirinty.zkalev.dao.model.GoodsInStock;

@Repository
public interface GoodsDao extends CrudRepository<GoodsInStock, Integer> {

	public GoodsInStock findByName(String name);

	@Transactional(readOnly = false)
	@Modifying 
	@Query(value = "update GoodsInStock g set g.amountInstock = ?2 where g.name = ?1", nativeQuery = false)
	public void updateByName(String good_name, Integer in_stock);
}
