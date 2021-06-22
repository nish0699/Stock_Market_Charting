package com.application.StockMarketCharting.Service;

import java.text.ParseException;
import java.util.List;

import com.application.StockMarketCharting.dto.CompanyCompareRequestDto;
import com.application.StockMarketCharting.dto.SectorCompareRequestDto;
import com.application.StockMarketCharting.dto.StockPriceDto;

public interface StockPriceService {
	public List<StockPriceDto> findAll();
	public StockPriceDto findById(int id);
	public void deleteById(int id);
	public List<StockPriceDto> save(List<StockPriceDto> stockPriceDtos);
	public StockPriceDto update(StockPriceDto stockPriceDto);
	public List<StockPriceDto> getStockPricesForCompanyComparison(CompanyCompareRequestDto compareRequest) throws ParseException;
	public List<StockPriceDto> getStockPricesForSectorComparison(SectorCompareRequestDto compareRequest) throws ParseException;

}
