package com.application.StockMarketCharting.Service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.StockMarketCharting.Service.CompanyService;
import com.application.StockMarketCharting.dao.CompanyDao;
import com.application.StockMarketCharting.dao.IpoDao;
import com.application.StockMarketCharting.dao.SectorDao;
import com.application.StockMarketCharting.dao.StockPriceDao;
import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.IpoDto;
import com.application.StockMarketCharting.dto.StockPriceDto;
import com.application.StockMarketCharting.entity.Company;
import com.application.StockMarketCharting.entity.Ipo;
import com.application.StockMarketCharting.entity.Sector;
import com.application.StockMarketCharting.entity.StockPrice;
import com.application.StockMarketCharting.mapper.CompanyMapper;
import com.application.StockMarketCharting.mapper.IpoMapper;
import com.application.StockMarketCharting.mapper.StockPriceMapper;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	SectorDao sectorRepository;
	@Autowired 
	CompanyDao companyRepository;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired 
	IpoDao ipoRepository;
	
	@Autowired
	IpoMapper ipoMapper;
	
	@Autowired
	StockPriceMapper stockPriceMapper;
	@Autowired
	StockPriceDao stockPriceRepository;
	
	@Override
	public List<CompanyDto> getCompanies() {

		List<Company> companyList= (List<Company>) companyRepository.findAll();
		return companyMapper.toCompanyDtos(companyList);
	}

	@Override
	public CompanyDto findById(int id) {
		Company company= companyRepository.findById(id);
		if(company==null)
		return null;
		
		return companyMapper.toCompanyDto(company);
	}

	@Override
	public List<CompanyDto> getMatchingCompanies(String pattern) {
	
		if(pattern=="" || pattern ==null)
		{
			List<Company> companies= companyRepository.findAll();
			return companyMapper.toCompanyDtos(companies);
		}
		List<Company> companyList= companyRepository.findByCompanyNameIgnoreCaseContaining(pattern);
		return companyMapper.toCompanyDtos(companyList);
		
	}

	@Override
	public CompanyDto addCompany(CompanyDto companyDto) {
		
		
		Company company= companyMapper.toCompany(companyDto);
		
		if(sectorRepository.findBySectorName(company.getSector())==null)
		{
			Sector sector= new Sector();
			sector.setSectorName(company.getSector());
			sector=sectorRepository.save(sector);
		}
		company.setSector1(sectorRepository.findBySectorName(company.getSector()));
		
		
		Company addCompany =companyRepository.findByCompanyName(company.getCompanyName());
		
		if(addCompany==null)
		{
		try {	
			company=companyRepository.save(company);
		}catch(Exception E)
		{
			return null;
		}
			return companyMapper.toCompanyDto(company);
		}
		return null;
	}

	@Override
	public CompanyDto editCompany(CompanyDto companyDto,int id) {
		
		Company company= companyMapper.toCompany(companyDto);
		Company updateCompany=companyRepository.findById(id);
		
		if(updateCompany!=null)
		{
			updateCompany.setCompanyName(company.getCompanyName());
			updateCompany.setCompanyCode(company.getCompanyCode());
			updateCompany.setTurnover(company.getTurnover());
			updateCompany.setCeo(company.getCeo());
			updateCompany.setBoardOfDirectors(company.getBoardOfDirectors());
			updateCompany.setSector(company.getSector());
			updateCompany.setStockExchanges(company.getStockExchanges());
			updateCompany.setremarks(company.getremarks());
			updateCompany=companyRepository.save(updateCompany);
			CompanyDto updateCompanyDto=companyMapper.toCompanyDto(updateCompany);
			System.out.println("Company- "+updateCompany);
			System.out.println("Company Dto- "+updateCompanyDto);
			return updateCompanyDto;
		}
		return null;
	}

	@Override
	public void deleteCompany(int id) {
		Company company = companyRepository.findById(id);
		System.out.println("\n"+company.toString()+"\n");
		if(company!=null)
		{
			System.out.println("Company found, deleting company");
			companyRepository.deleteById(id);
			System.out.println("Company found, deleted");
			
		}
	}
	@Override
	public CompanyDto addIpoToCompany(String companyName, IpoDto ipoDto) {
		Ipo ipo=ipoMapper.toIpo(ipoDto);
		Company company=companyRepository.findByCompanyName(companyName);
		if(company==null)
			return null;
		ipo.setCompany1(companyRepository.findByCompanyName(companyName));
		ipo=ipoRepository.save(ipo);
		company.addIpo(ipo);
		company=companyRepository.save(company);
		return companyMapper.toCompanyDto(company);
	}

	@Override
	public List<IpoDto> getCompanyIpoDetails(int id) {
		Company company=companyRepository.findById(id);
		if(company==null)
			return null;
		
		List<Ipo> ipoList=company.getIpoList();
		for(Ipo i:ipoList)
			System.out.println(i.toString());
		return ipoMapper.toIpoDtos(ipoList);
	}

	@Override
	public CompanyDto addStockPriceToCompany(String companyCode, StockPriceDto stockPriceDto) {
		Company company= companyRepository.findByCompanyCode(companyCode);
		StockPrice stockPrice =stockPriceMapper.toStockPrice(stockPriceDto);
		if(company==null)
			return null;
		stockPrice.setCompany2(company);
		stockPrice=stockPriceRepository.save(stockPrice);
		company.addStockPrice(stockPrice);
		company=companyRepository.save(company);
		return companyMapper.toCompanyDto(company);
	}

	@Override
	public List<StockPriceDto> getStockPrices(String companyName) {
			Company company=companyRepository.findByCompanyName(companyName);
			if(company==null)
				return null;
			
			List<StockPrice> stockPriceList=company.getStockPriceList();
			return stockPriceMapper.toStockPriceDtos(stockPriceList);
	}


}
