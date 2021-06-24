package com.application.StockMarketCharting.Service.impl;

import java.text.ParseException;
import java.time.LocalDate;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.StockMarketCharting.Service.StockPriceService;
import com.application.StockMarketCharting.dao.CompanyDao;
import com.application.StockMarketCharting.dao.StockPriceDao;
import com.application.StockMarketCharting.dto.CompanyCompareRequestDto;
import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.JsonDto;
import com.application.StockMarketCharting.dto.SectorCompareRequestDto;
import com.application.StockMarketCharting.dto.StockPriceDto;
import com.application.StockMarketCharting.entity.Company;
import com.application.StockMarketCharting.entity.StockPrice;
import com.application.StockMarketCharting.mapper.StockPriceMapper;

import net.minidev.json.JSONObject;
//import org.json.JSONObject;

@Service
public class StockPriceServiceImpl implements StockPriceService {

	@Autowired
	SectorServiceImpl sectorService;
	@Autowired
	StockPriceDao stockPriceRepository;
	
	@Autowired
	StockPriceMapper stockPriceMapper;
	
	@Autowired 
	CompanyDao companyRepository;
	@Override
	public List<StockPriceDto> findAll() {
		// TODO Auto-generated method stub
		
		List<StockPrice> stockPriceList=(List<StockPrice>) stockPriceRepository.findAll();
		return stockPriceMapper.toStockPriceDtos(stockPriceList);
	}

	@Override
	public StockPriceDto findById(int id) {
		StockPrice stockPrice=stockPriceRepository.findById(id);
		if(stockPrice==null)
			return null;
		return stockPriceMapper.toStockPriceDto(stockPrice);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		stockPriceRepository.deleteById(id);
		
	}
	
	
	
	@Override
	public List<StockPriceDto> save(List<StockPriceDto> stockPriceDtos) {
		// TODO Auto-generated method stub
		List<StockPrice> stockPriceList=stockPriceMapper.toStockPrices(stockPriceDtos);
		List<StockPrice> stockPriceAdd = new ArrayList<>();
		for(StockPrice sp : stockPriceList)
		{
			Company company = companyRepository.findByCompanyCode(sp.getCompanyCode());
			sp.setCompany2(company);
//			System.out.println(company);
//			System.out.println(sp);
			stockPriceAdd.add(sp);
//			System.out.println(sp);
		}
		stockPriceAdd=(List<StockPrice>) stockPriceRepository.saveAll(stockPriceAdd);
		
		return stockPriceMapper.toStockPriceDtos(stockPriceAdd);
	}

	@Override
	public StockPriceDto update(StockPriceDto stockPriceDto) {
		// TODO Auto-generated method stub
		StockPrice stockPrice=stockPriceMapper.toStockPrice(stockPriceDto);
		if(stockPriceRepository.findById(stockPriceDto.getId())!=null)
		{
			StockPrice updateStockPrice=stockPriceRepository.findById(stockPriceDto.getId());
			updateStockPrice.setCompanyCode(stockPrice.getCompanyCode());
			updateStockPrice.setCurrentPrice(stockPrice.getCurrentPrice());
			updateStockPrice.setStockExchanges(stockPrice.getStockExchanges());
			updateStockPrice.setDate1(stockPrice.getDate1());
			updateStockPrice.setTime1(stockPrice.getTime1());
			Company company = companyRepository.findByCompanyCode(stockPrice.getCompanyCode());
			updateStockPrice.setCompany2(company);
			updateStockPrice=stockPriceRepository.save(updateStockPrice);
			return stockPriceMapper.toStockPriceDto(updateStockPrice);
		}
		return null;
	}


	@Override
	public List<StockPriceDto> getStockPricesForSectorComparison(SectorCompareRequestDto compareRequest)
			throws ParseException 
	{
		
		LocalDate fromDate = LocalDate.parse(compareRequest.getFromPeriod());
		LocalDate toDate = LocalDate.parse(compareRequest.getToPeriod());
		List<StockPrice> stockPricesForSector = new ArrayList<>();
		for(CompanyDto companyDto: sectorService.getCompanies(compareRequest.getSectorName()))
		{
			List<StockPrice> stockPrices = stockPriceRepository
					.findByCompanyCodeAndStockExchanges(companyDto.getCompanyCode(), compareRequest.getStockExchangeName());
			List<StockPrice> filteredList = stockPrices.stream()
					.filter(stockPrice -> {
						LocalDate date = LocalDate.parse(stockPrice.getDate1());
				
						return date.isAfter(fromDate) && date.isBefore(toDate);
					})
					.collect(Collectors.toList());
			stockPricesForSector.addAll(filteredList);
		}
		return stockPriceMapper.toStockPriceDtos(stockPricesForSector);
	}


	@Override
	public List<StockPriceDto> getStockPricesForCompanyComparison(CompanyCompareRequestDto compareRequest)
			throws ParseException 
	{
		LocalDate fromDate = LocalDate.parse(compareRequest.getFromPeriod());
		LocalDate toDate = LocalDate.parse(compareRequest.getToPeriod());
		List<StockPrice> stockPrices = stockPriceRepository
				.findByCompanyCodeAndStockExchanges(compareRequest.getCompanyCode(), compareRequest.getStockExchangeName());
		List<StockPrice> filteredList = stockPrices.stream()
				.filter(stockPrice -> {
					LocalDate date=LocalDate.parse(stockPrice.getDate1());
					
					
					return date.isAfter(fromDate) && date.isBefore(toDate);
				})
				.collect(Collectors.toList());
		
		//check if this works
		
		return stockPriceMapper.toStockPriceDtos(filteredList);
	}

	@Override
	public JsonDto saveAll(JSONObject[] jsonObj) {
		// TODO Auto-generated method stub
		List<StockPrice> saveStockPrice=new ArrayList<>();
		JsonDto summary=new JsonDto();
		int count=0;
		for(JSONObject j: jsonObj)
		{
			StockPrice stockPrice=new StockPrice();
			 // j.keySet();
			Set<String> keys= j.keySet();
//			System.out.println(keys);
			for(String key : keys)
			{
//				System.out.println("key= "+key+" Value= "+j.get(key));
				if(key.equals("Company Code") && j.getAsString(key)!=null)
				{
					String code= j.getAsString(key);
					if(code.length()>0) {
					stockPrice.setCompanyCode(code.substring(0,6));
					Company company=companyRepository.findByCompanyCode(code.substring(0, 6));
					stockPrice.setCompany2(company);
					count++;
					
					}
				}
				else if(key.equals("Price Per Share(in Rs)"))
				{
					stockPrice.setCurrentPrice((double) Double.parseDouble(j.getAsString(key)));
				}
				else if(key.equals("Stock Exchange") && j.getAsString(key)!=null)
				{
					stockPrice.setStockExchanges(j.getAsString(key));
				}
				else if(key.contains("Date") && j.getAsString(key)!=null) {
//					System.out.println("date= "+j.getAsString(key));
					stockPrice.setDate1(j.getAsString(key));
				}
				else if(key.equals("Time") && j.getAsString(key)!=null) {
					stockPrice.setTime1(j.getAsString(key).substring(2));
				}
				
						
//				System.out.println(.get(key);
			}
			if(stockPrice.getCompanyCode()!=null)
			{
			System.out.println(stockPrice.toString());
//			stockPrice=stockPriceRepository.save(stockPrice);
			saveStockPrice.add(stockPrice);
			}
		}
		List<StockPrice> stockPriceList=(List<StockPrice>) stockPriceRepository.saveAll(saveStockPrice);
		
		if(stockPriceList.isEmpty())
		return null;

		StockPrice stockPriceFirst= stockPriceList.get(0);
		StockPrice stockPriceLast= stockPriceList.get(stockPriceList.size()-1);
		summary.setCompanyName(stockPriceFirst.getCompany2().getCompanyName());
		summary.setCount(count);
		summary.setStockExchange(stockPriceFirst.getStockExchanges());
		summary.setFromDate(stockPriceFirst.getDate1());
		summary.setToDate(stockPriceLast.getDate1());
		
		return summary;
	}
}
