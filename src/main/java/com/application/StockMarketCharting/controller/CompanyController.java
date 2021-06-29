package com.application.StockMarketCharting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.StockMarketCharting.Service.CompanyService;
import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.IpoDto;
import com.application.StockMarketCharting.dto.StockPriceDto;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/companies")
public class CompanyController 
{
	@Autowired
	private CompanyService companyService;
	@GetMapping(path = "")
	public ResponseEntity<List<CompanyDto>> getCompanies() 
	{
		return ResponseEntity
				.ok(companyService.getCompanies());
	}
	@GetMapping(path = "/{id}")
	public ResponseEntity<CompanyDto> getCompanyDetails(@PathVariable int id)

	{
		CompanyDto companyDto = companyService.findById(id);
		if(companyDto == null) {
			System.out.println("Company not found at id : " + id);
			return null;
		}
		return ResponseEntity.ok(companyDto);
	}
	@GetMapping(path = "/match/{pattern}")
	public ResponseEntity<List<CompanyDto>> getMatchingCompanies(@PathVariable String pattern) 
	{
		return ResponseEntity.ok(companyService.getMatchingCompanies(pattern));
	}
//	4
	@GetMapping(path = "/{id}/ipos")
	public ResponseEntity<List<IpoDto>> getCompanyIpoDetails(@PathVariable int id)
	{
		
		List<IpoDto> ipoDtos = companyService.getCompanyIpoDetails(id);
		
		if(ipoDtos == null) {
		}
		return ResponseEntity.ok(ipoDtos);
	}
//	5
	@GetMapping(path = "/{companyName}/stockPrices")
	public ResponseEntity<List<StockPriceDto>> getStockPrices(@PathVariable String companyName)
	{
		List<StockPriceDto> stockPriceDtos = companyService.getStockPrices(companyName);
		
		if(stockPriceDtos == null) {
		//	throw new CompanyNotFoundException("Company not found at id : " + id);
		}
		return ResponseEntity.ok(stockPriceDtos);
		
	}
	@PostMapping(path = "/add-company",consumes="application/json",produces="application/json")
	public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto companyDto) {
		
		CompanyDto addCompany= companyService.addCompany(companyDto);
		if(addCompany==null)
			return null;
		return ResponseEntity.ok(addCompany);
	}
	@PutMapping(path = "/edit-company/{id}")
	public ResponseEntity<CompanyDto> editCompany(@RequestBody CompanyDto companyDto,@PathVariable int id)
	{
		CompanyDto updatedCompanyDto = companyService.editCompany(companyDto,id);
		if(updatedCompanyDto == null) {
			System.out.println("Company not found at id: "+companyDto.getId());
		}
		return ResponseEntity.ok(updatedCompanyDto);
	}
	@DeleteMapping(path = "/{id}")
	public void deleteCompany(@PathVariable int id) {
		try 
		{	
			companyService.deleteCompany(id);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		}

	@PostMapping(path = "/{companyName}/add-ipo")
	public ResponseEntity<?> addIpoToCompany(@PathVariable String companyName, @RequestBody IpoDto ipoDto)
	{
		CompanyDto companyDto = companyService.addIpoToCompany(companyName, ipoDto);
		if(companyDto == null) {
			
			System.out.println("Ipo not added");
			return null;
		}
		return ResponseEntity.ok(ipoDto);
	}

	@PostMapping(path = "/{companyCode}/stockPrices")
	public ResponseEntity<?> addStockPriceToCompany(@PathVariable String companyCode, @RequestBody StockPriceDto stockPriceDto) 
	{
		CompanyDto companyDto = companyService.addStockPriceToCompany(companyCode, stockPriceDto);
		if(companyDto == null) {
			return null;
		}
		return ResponseEntity.ok(companyDto);
	}
}
