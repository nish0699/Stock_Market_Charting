package com.application.StockMarketCharting.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.StockMarketCharting.Service.StockExchangeService;
import com.application.StockMarketCharting.dao.CompanyDao;
import com.application.StockMarketCharting.dao.SectorDao;
import com.application.StockMarketCharting.dao.StockExchangeDao;
import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.StockExchangeDto;
import com.application.StockMarketCharting.entity.Company;
import com.application.StockMarketCharting.entity.StockExchange;
import com.application.StockMarketCharting.mapper.CompanyMapper;
import com.application.StockMarketCharting.mapper.StockExchangeMapper;

@Service
public class StockExchangeServiceImpl implements StockExchangeService {

	@Autowired
	StockExchangeDao stockExchangeRepository;

	@Autowired
	SectorDao sectorRepository;
	@Autowired
	CompanyDao companyRepository;
	@Autowired
	StockExchangeMapper stockExchangeMapper;
	
	@Autowired
	CompanyMapper companyMapper;
	@Override
	public List<StockExchangeDto> getStockExchangesList() {
		// TODO Auto-generated method stub
		
		List<StockExchange> stockList= (List<StockExchange>) stockExchangeRepository.findAll();
		
		return stockExchangeMapper.toStockExchangeDtos(stockList);
	}

	@Override
	public StockExchangeDto findById(int id) {
		// TODO Auto-generated method stub
		StockExchange stockExchange =stockExchangeRepository.findById(id);
		if(stockExchange==null)
			return null;
		return stockExchangeMapper.toStockExchangeDto(stockExchange);
	}

	@Override
	public StockExchangeDto addStockExchange(StockExchangeDto stockExchangeDto) {
		// TODO Auto-generated method stub
		
		StockExchange stockExchange=stockExchangeMapper.toStockExchange(stockExchangeDto);
		if(stockExchangeRepository.findByStockExchangeName(stockExchange.getStockExchangeName())==null)
		{
			stockExchange=stockExchangeRepository.save(stockExchange);
			return stockExchangeMapper.toStockExchangeDto(stockExchange);
		}
		
		return null;
	}

	@Override
	public StockExchangeDto editStockExchange(StockExchangeDto stockExchangeDto) {
		// TODO Auto-generated method stub
		StockExchange stockExchange=stockExchangeMapper.toStockExchange(stockExchangeDto);
		if(stockExchangeRepository.findById(stockExchangeDto.getId())!=null)
		{
			StockExchange addStockExchange=stockExchangeRepository.findById(stockExchangeDto.getId()); 
			addStockExchange.setStockExchangeName(stockExchange.getStockExchangeName());
			addStockExchange.setstockDescription(stockExchange.getstockDescription());
			addStockExchange.setAddress(stockExchange.getAddress());
			addStockExchange.setRemarks(stockExchange.getRemarks());
			addStockExchange=stockExchangeRepository.save(addStockExchange);
			return stockExchangeMapper.toStockExchangeDto(addStockExchange);
		}
		return null;
	}

	@Override
	public void deleteStockExchange(int id) {
		// TODO Auto-generated method stub
		StockExchange st=stockExchangeRepository.findById(id);
		if(st!=null)
		{
			System.out.println("Stockto delete " +st.toString());
			stockExchangeRepository.deleteById(id);
		}
		
	}

	@Override
	public List<CompanyDto> getCompanies(int id) {
		// TODO Auto-generated method stub
		StockExchange stockExchange=stockExchangeRepository.findById(id);
		List<Company> companyList=stockExchange.getCompanyList();
		return companyMapper.toCompanyDtos(companyList);
	}

	@Override
	public StockExchangeDto addCompanyToStockExchange(String stockExchangeName, CompanyDto companyDto) {
		// TODO Auto-generated method stub
		StockExchange stockExchange=stockExchangeRepository.findByStockExchangeName(stockExchangeName);
		if(stockExchange==null)
			return null;
		Company company=companyRepository.findById(companyDto.getId());
		
		if(company!=null)
		{
		stockExchange.addCompany(company);
		stockExchange=stockExchangeRepository.save(stockExchange);
		return stockExchangeMapper.toStockExchangeDto(stockExchange);
		}
		
		return null;
	}

	
}