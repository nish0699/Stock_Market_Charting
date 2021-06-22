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
//	1
	@GetMapping(path = "")
	public ResponseEntity<List<CompanyDto>> getCompanies() 
	{
		return ResponseEntity
				.ok(companyService.getCompanies());
	}
//	2
	@GetMapping(path = "/{id}")
	public ResponseEntity<CompanyDto> getCompanyDetails(@PathVariable int id)
	//		throws CompanyNotFoundException
	{
		CompanyDto companyDto = companyService.findById(id);
		if(companyDto == null) {
		//	throw new CompanyNotFoundException("Company not found at id : " + id);
		}
		return ResponseEntity.ok(companyDto);
	}
//	3
	@GetMapping(path = "/match/{pattern}")
	public ResponseEntity<List<CompanyDto>> getMatchingCompanies(@PathVariable String pattern) 
	{
		return ResponseEntity.ok(companyService.getMatchingCompanies(pattern));
	}
//	4
	@GetMapping(path = "/{id}/ipos")
	public ResponseEntity<List<IpoDto>> getCompanyIpoDetails(@PathVariable int id)
//			throws CompanyNotFoundException 
	{
		
		List<IpoDto> ipoDtos = companyService.getCompanyIpoDetails(id);
		
		if(ipoDtos == null) {
	//		throw new CompanyNotFoundException("Company not found at id : " + id);
		}
		return ResponseEntity.ok(ipoDtos);
	}
//	5
	@GetMapping(path = "/{companyName}/stockPrices")
	public ResponseEntity<List<StockPriceDto>> getStockPrices(@PathVariable String companyName)
		//	throws CompanyNotFoundException
	{
		List<StockPriceDto> stockPriceDtos = companyService.getStockPrices(companyName);
		
		if(stockPriceDtos == null) {
		//	throw new CompanyNotFoundException("Company not found at id : " + id);
		}
		return ResponseEntity.ok(stockPriceDtos);
		
//		return null;
	}
//	6
	@PostMapping(path = "/add-company",consumes="application/json",produces="application/json")
//	@HystrixCommand(fallbackMethod = "defaultResponse")
	public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto companyDto) {
		
		CompanyDto addCompany= companyService.addCompany(companyDto);
		if(addCompany==null)
			return null;
		return ResponseEntity.ok(addCompany);
	}
//	7
	@PutMapping(path = "/edit-company/{id}")
	public ResponseEntity<CompanyDto> editCompany(@RequestBody CompanyDto companyDto,@PathVariable int id)
//			throws CompanyNotFoundException 
	{
		CompanyDto updatedCompanyDto = companyService.editCompany(companyDto,id);
		if(updatedCompanyDto == null) {
//			throw new CompanyNotFoundException("Company not found at id : " + companyDto.getId());
			System.out.println("Company not found at id: "+companyDto.getId());
		}
		return ResponseEntity.ok(updatedCompanyDto);
	}
//	8
	@DeleteMapping(path = "/{id}")
	public void deleteCompany(@PathVariable int id) {
		try 
		{	
			companyService.deleteCompany(id);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
//		companyService.deleteCompany(id);
		}

	/* Feign Client Mappings */
//	9
	@PostMapping(path = "/{companyName}/add-ipo")
	public ResponseEntity<?> addIpoToCompany(@PathVariable String companyName, @RequestBody IpoDto ipoDto)
//			throws CompanyNotFoundException
	{
		CompanyDto companyDto = companyService.addIpoToCompany(companyName, ipoDto);
		if(companyDto == null) {
//			return null;
			
			System.out.println("Ipo not added");
			return null;
//			throw new CompanyNotFoundException("Company not with name : " + companyName);
		}
		return ResponseEntity.ok(ipoDto);
	}

//	10
	@PostMapping(path = "/{companyCode}/stockPrices")
	public ResponseEntity<?> addStockPriceToCompany(@PathVariable String companyCode, @RequestBody StockPriceDto stockPriceDto) 
//			throws CompanyNotFoundException
	{
		CompanyDto companyDto = companyService.addStockPriceToCompany(companyCode, stockPriceDto);
		if(companyDto == null) {
//			throw new CompanyNotFoundException("Company not with code : " + companyCode);
			return null;
		}
		return ResponseEntity.ok(companyDto);
	}
}
//	
//	/* Feign Client Default Response */
//	11
//	public ResponseEntity<?> defaultResponse(@RequestBody CompanyDto companyDto) {
//		String err = "Fallback error as the microservice is down.";
//		return ResponseEntity
//				.status(HttpStatus.SERVICE_UNAVAILABLE)
//				.body(err);
//	}
//}

