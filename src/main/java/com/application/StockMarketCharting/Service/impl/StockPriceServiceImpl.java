package com.application.StockMarketCharting.Service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.StockMarketCharting.Service.StockPriceService;
import com.application.StockMarketCharting.dao.CompanyDao;
import com.application.StockMarketCharting.dao.StockPriceDao;
import com.application.StockMarketCharting.dto.CompanyCompareRequestDto;
import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.SectorCompareRequestDto;
import com.application.StockMarketCharting.dto.StockPriceDto;
import com.application.StockMarketCharting.entity.Company;
import com.application.StockMarketCharting.entity.StockPrice;
import com.application.StockMarketCharting.mapper.StockPriceMapper;

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
		Date fromDate = new SimpleDateFormat("dd-MM-yyyy").parse(compareRequest.getFromPeriod());
		Date toDate = new SimpleDateFormat("dd-MM-yyyy").parse(compareRequest.getToPeriod());
		List<StockPrice> stockPricesForSector = new ArrayList<>();
		for(CompanyDto companyDto: sectorService.getCompanies(compareRequest.getSectorName()))
		{
			List<StockPrice> stockPrices = stockPriceRepository
					.findByCompanyCodeAndStockExchanges(companyDto.getCompanyCode(), compareRequest.getStockExchangeName());
			List<StockPrice> filteredList = stockPrices.stream()
					.filter(stockPrice -> {
						Date date = null;
							try {
								date = new SimpleDateFormat("dd-MM-yyyy").parse(stockPrice.getDate1());
							} catch (ParseException e) {
								e.printStackTrace();
							}
						
						return date.after(fromDate) && date.before(toDate);
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
		Date fromDate = new SimpleDateFormat("dd-MM-yyyy").parse(compareRequest.getFromPeriod());
		Date toDate = new SimpleDateFormat("dd-MM-yyyy").parse(compareRequest.getToPeriod());
		List<StockPrice> stockPrices = stockPriceRepository
				.findByCompanyCodeAndStockExchanges(compareRequest.getCompanyCode(), compareRequest.getStockExchangeName());
		List<StockPrice> filteredList = stockPrices.stream()
				.filter(stockPrice -> {
					Date date = null;
					try {
						date = new SimpleDateFormat("dd-MM-yyyy").parse(stockPrice.getDate1());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return date.after(fromDate) && date.before(toDate);
				})
				.collect(Collectors.toList());
		return stockPriceMapper.toStockPriceDtos(filteredList);
	}
}
