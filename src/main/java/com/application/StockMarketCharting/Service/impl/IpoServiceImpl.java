package com.application.StockMarketCharting.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.StockMarketCharting.Service.IpoService;
import com.application.StockMarketCharting.dao.CompanyDao;
import com.application.StockMarketCharting.dao.IpoDao;
import com.application.StockMarketCharting.dto.IpoDto;
import com.application.StockMarketCharting.entity.Company;
import com.application.StockMarketCharting.entity.Ipo;
import com.application.StockMarketCharting.mapper.IpoMapper;

@Service
public class IpoServiceImpl implements IpoService {

	@Autowired
	IpoDao ipoRepository;
	
	@Autowired
	IpoMapper ipoMapper;
	
	@Autowired 
	CompanyDao companyRepository;
	@Override
	public List<IpoDto> findAll() {
		
		List<Ipo> ipos=(List<Ipo>) ipoRepository.findAll();
		return ipoMapper.toIpoDtos(ipos);
	}

	@Override
	public IpoDto findById(int id) {
		Ipo ipo =ipoRepository.findById(id);
		if(ipo==null)
			return null;
		return ipoMapper.toIpoDto(ipo);
	}

	@Override
	public IpoDto save(IpoDto ipoDto) {
		// TODO Auto-generated method stub
		System.out.println(ipoDto.toString());
		Ipo ipo=ipoMapper.toIpo(ipoDto);
		System.out.println(ipo.toString());
		ipo.setCompany1(companyRepository.findByCompanyName(ipo.getCompanyName()));
		
		try 
		{
			ipo=ipoRepository.save(ipo);
		}catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
		return ipoMapper.toIpoDto(ipo);
		
	}

	@Override
	public IpoDto update(IpoDto ipoDto,int id) {
		
//		System.out.println(ipoDto.toString());
		Ipo ipo=ipoMapper.toIpo(ipoDto);
//		System.out.println(ipo.toString());

		if(ipoRepository.findById(id)==null)
			return null;
		Ipo updateIpo=ipoRepository.findById(id);
//		System.out.println(updateIpo);
		
		updateIpo.setCompanyName(ipo.getCompanyName());
		updateIpo.setStockExchanges(ipo.getStockExchanges());
		updateIpo.setPricePerShare(ipo.getPricePerShare());
		updateIpo.setNoOfShares(ipo.getNoOfShares());
		updateIpo.setRemarks(ipo.getRemarks());
		updateIpo.setOpenDateTime(ipo.getOpenDateTime());
		Company company= companyRepository.findByCompanyName(ipo.getCompanyName());
//		System.out.println(company.toString());
		updateIpo.setCompany1(company);
		

		updateIpo=ipoRepository.save(updateIpo);
//		System.out.println(updateIpo.toString());
//		System.out.println(ipo);
		return ipoMapper.toIpoDto(updateIpo);
	}

	@Override
	public void deleteById(int id) {
		ipoRepository.deleteById(id);
	}

}
