package com.application.StockMarketCharting.Service;

import java.util.List;

import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.SectorDto;

public interface SectorService {
	public SectorDto save(SectorDto sectorDto);
	public List<SectorDto> findAll() ;
	public SectorDto findById(int id);
	public void deleteById(int id);
	public List<CompanyDto> getCompanies(String sectorName);
	public SectorDto addCompanyToSector(String sectorName, CompanyDto companyDto);
	public SectorDto addSector(SectorDto sector);
	public SectorDto updateSector(SectorDto sector);
}
