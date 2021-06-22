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
	
		List<Company> companyList= companyRepository.findByCompanyNameIgnoreCaseContaining(pattern);
		return companyMapper.toCompanyDtos(companyList);
		
	}

	@Override
	public CompanyDto addCompany(CompanyDto companyDto) {
		
		
		//System.out.println("Company dto orignal"+companyDto);
		Company company= companyMapper.toCompany(companyDto);
		//System.out.println("Company "+company);
		
		if(sectorRepository.findBySectorName(company.getSector())==null)
		{
			Sector sector= new Sector();
			sector.setSectorName(company.getSector());
			sector=sectorRepository.save(sector);
		}
		company.setSector1(sectorRepository.findBySectorName(company.getSector()));
		
		
		Company addCompany =companyRepository.findByCompanyName(company.getCompanyName());
//		System.out.println("Company to add "+addCompany);
		
		if(addCompany==null)
		{
		try {	
			company=companyRepository.save(company);
		}catch(Exception E)
		{
//			System.out.println(E);
			return null;
		}
			return companyMapper.toCompanyDto(company);
		}
		return null;
	}

	@Override
	public CompanyDto editCompany(CompanyDto companyDto,int id) {
		// TODO Auto-generated method stub
		
		Company company= companyMapper.toCompany(companyDto);
		Company updateCompany=companyRepository.findById(id);
//		System.out.println("Orignal -"+company);
//		System.out.println("Orignal 1 -"+updateCompany);
		
		if(updateCompany!=null)
		{
//			System.out.println("company found at id: "+id);
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		Ipo ipo=ipoMapper.toIpo(ipoDto);
//		System.out.println(ipo.toString()+"\n");
		Company company=companyRepository.findByCompanyName(companyName);
//		System.out.println(company.toString());
		if(company==null)
			return null;
		//after mapping
		ipo.setCompany1(companyRepository.findByCompanyName(companyName));
		ipo=ipoRepository.save(ipo);
//		company.getIpoList().add(ipo);
//		System.out.println(ipo.toString()+"\n");
		company.addIpo(ipo);
		company=companyRepository.save(company);
//		for(Ipo i: company.getIpoList())
//			System.out.println(i.toString());
		return companyMapper.toCompanyDto(company);
//		return null;
	}

	@Override
	public List<IpoDto> getCompanyIpoDetails(int id) {
//		// TODO Auto-generated method stub
		Company company=companyRepository.findById(id);
		if(company==null)
			return null;
		
		List<Ipo> ipoList=company.getIpoList();
		for(Ipo i:ipoList)
			System.out.println(i.toString());
		return ipoMapper.toIpoDtos(ipoList);
//		return null;
		
		
	}

	@Override
	public CompanyDto addStockPriceToCompany(String companyCode, StockPriceDto stockPriceDto) {
		// TODO Auto-generated method stub
		Company company= companyRepository.findByCompanyCode(companyCode);
//		System.out.println(stockPriceDto.toString());
		StockPrice stockPrice =stockPriceMapper.toStockPrice(stockPriceDto);
//		System.out.println(stockPrice.toString()+"\n");
		if(company==null)
			return null;
		stockPrice.setCompany2(company);
		stockPrice=stockPriceRepository.save(stockPrice);
		company.addStockPrice(stockPrice);
		company=companyRepository.save(company);
//		for(StockPrice s: company.getStockPriceList())
//			System.out.println(s.toString());
		return companyMapper.toCompanyDto(company);
//		return null;
	}

	@Override
	public List<StockPriceDto> getStockPrices(String companyName) {
			Company company=companyRepository.findByCompanyName(companyName);
//			System.out.println(company.toString());
			if(company==null)
				return null;
			
			List<StockPrice> stockPriceList=company.getStockPriceList();
			return stockPriceMapper.toStockPriceDtos(stockPriceList);
//			return null;
	}


}
