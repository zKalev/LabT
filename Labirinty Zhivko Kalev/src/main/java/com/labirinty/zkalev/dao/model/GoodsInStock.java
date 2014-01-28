package com.labirinty.zkalev.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

@Entity
@Table(name = "goods")
@NamedQueries({ @NamedQuery(name = "GoodsInStock.findAll", query = "SELECT u FROM GoodsInStock u"), })
public class GoodsInStock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	//@NotNull
	@Column(name = "good_name")
	private String name;
	//@NotNull
	@Column(name = "in_stock")
	private Integer amountInstock;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmountInstock() {
		return amountInstock;
	}

	public void setAmountInstock(Integer amountInstock) {
		this.amountInstock = amountInstock;
	}
}
