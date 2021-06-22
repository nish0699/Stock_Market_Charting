package com.application.StockMarketCharting.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.StockMarketCharting.entity.StockExchange;

@Repository
public interface StockExchangeDao extends CrudRepository<StockExchange,Integer> {
	public StockExchange findById(int id);
	public StockExchange findByStockExchangeName(String name);
}
