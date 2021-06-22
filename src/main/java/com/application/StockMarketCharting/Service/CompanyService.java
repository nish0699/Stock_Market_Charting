package com.application.StockMarketCharting.Service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.IpoDto;
import com.application.StockMarketCharting.dto.StockPriceDto;

public interface CompanyService {
	public List<CompanyDto> getCompanies();
	public CompanyDto findById(int id);
	public List<CompanyDto> getMatchingCompanies(String pattern);
	public CompanyDto addCompany(CompanyDto companyDto);
	public CompanyDto editCompany(CompanyDto companyDto,int id);
	public void deleteCompany(int id);
	public CompanyDto addIpoToCompany(String companyName, IpoDto ipoDto);
	public List<IpoDto> getCompanyIpoDetails(int id);
	public CompanyDto addStockPriceToCompany(String companyCode, StockPriceDto stockPriceDto);
	public List<StockPriceDto> getStockPrices(String companyName);
}
