package com.application.StockMarketCharting.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.StockMarketCharting.entity.StockPrice;

@Repository
public interface StockPriceDao extends CrudRepository<StockPrice,Integer>{
	public StockPrice findById(int id);

	public List<StockPrice> findByCompanyCodeAndStockExchanges(String companyCode, String stockExchanges);

}
