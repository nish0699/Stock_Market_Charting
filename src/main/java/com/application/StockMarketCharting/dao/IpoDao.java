package com.application.StockMarketCharting.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.StockMarketCharting.entity.Ipo;

@Repository
public interface IpoDao extends CrudRepository<Ipo,Integer>{
	public Ipo findById(int id);
	public void deleteById(int id);

}
