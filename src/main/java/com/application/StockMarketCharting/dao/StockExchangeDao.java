package com.application.StockMarketCharting.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.StockMarketCharting.entity.StockExchange;
@Transactional
@Repository
public interface StockExchangeDao extends CrudRepository<StockExchange,Integer> {
	public StockExchange findById(int id);
	public StockExchange findByStockExchangeName(String name);
}
