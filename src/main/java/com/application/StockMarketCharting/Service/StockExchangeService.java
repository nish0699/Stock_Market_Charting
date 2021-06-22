package com.application.StockMarketCharting.Service;

import java.util.List;

import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.StockExchangeDto;


public interface StockExchangeService {
	public List<StockExchangeDto> getStockExchangesList();
	public StockExchangeDto findById(int id);
	public StockExchangeDto addStockExchange(StockExchangeDto stockExchangeDto);
	public StockExchangeDto editStockExchange(StockExchangeDto stockExchangeDto);
	public void deleteStockExchange(int id);
	public List<CompanyDto> getCompanies(int id);
	public StockExchangeDto addCompanyToStockExchange(String stockExchangeName, CompanyDto companyDto);

}
