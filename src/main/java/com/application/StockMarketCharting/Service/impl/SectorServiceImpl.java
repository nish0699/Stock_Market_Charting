package com.application.StockMarketCharting.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.StockMarketCharting.Service.SectorService;
import com.application.StockMarketCharting.dao.SectorDao;
import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.SectorDto;
import com.application.StockMarketCharting.entity.Company;
import com.application.StockMarketCharting.entity.Sector;
import com.application.StockMarketCharting.mapper.CompanyMapper;
import com.application.StockMarketCharting.mapper.SectorMapper;

@Service
public class SectorServiceImpl implements SectorService{

	
	@Autowired 
	SectorMapper sectorMapper;
	
	@Autowired
	SectorDao sectorRepository;
	
//	@Autowired
//	CompanyDao companyRepository;
	
	@Autowired
	CompanyMapper companyMapper;
//	
	@Override
	public SectorDto save(SectorDto sectorDto) {
		Sector sector=sectorMapper.toSector(sectorDto);
		sector=sectorRepository.save(sector);
		return sectorMapper.toSectorDto(sector);
	}

	@Override
	public List<SectorDto> findAll() {
//		try {}
		List<Sector> sectorList= (List<Sector>) sectorRepository.findAll();
		return sectorMapper.toSectorDtos(sectorList);
	}

	@Override
	public SectorDto findById(int id) {
		// TODO Auto-generated method stub
		
		Sector sector= sectorRepository.findById(id);
		if(sector==null)
			return null;
		else
			return sectorMapper.toSectorDto(sector);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		sectorRepository.deleteById(id);
		
	}



	@Override
	public SectorDto addCompanyToSector(String sectorName, CompanyDto companyDto) {
		// TODO Auto-generated method stub
		Sector sector= sectorRepository.findBySectorName(sectorName);
		Company company= companyMapper.toCompany(companyDto);
		if(sector==null)
			return null;
		if(company==null)
			return null;
		sector.addCompany(company);
		sector=sectorRepository.save(sector);
		return sectorMapper.toSectorDto(sector);
		
		
	}

	@Override
	public SectorDto addSector(SectorDto sector) {
		// TODO Auto-generated method stub
		
		Sector addSector=sectorMapper.toSector(sector);
		if(sectorRepository.findBySectorName(sector.getSectorName())==null)
		{
			addSector=sectorRepository.save(addSector);
			return sectorMapper.toSectorDto(addSector);
		}
		
		return null;
	}

	@Override
	public SectorDto updateSector(SectorDto sector) {
		// TODO Auto-generated method stub
		Sector updateSector=sectorRepository.findById(sector.getId());
		if(updateSector!=null)
		{
			updateSector.setSectorName(sector.getSectorName());
			updateSector.setRemarks(sector.getRemarks());
			updateSector=sectorRepository.save(updateSector);
			return sectorMapper.toSectorDto(updateSector);
		}
		
		return null;
		
	}

	@Override
	public List<CompanyDto> getCompanies(String sectorName) {
	
			Sector sector=sectorRepository.findBySectorName(sectorName);
			if(sector==null)
				return null;
			List<Company> companyList= sector.getCompanyList();
			return companyMapper.toCompanyDtos(companyList);
		
	}

}
