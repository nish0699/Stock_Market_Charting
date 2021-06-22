package com.application.StockMarketCharting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.StockMarketCharting.Service.StockExchangeService;
import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.StockExchangeDto;

@RestController
@RequestMapping("/stock-exchanges")
public class StockExchangeController 
{
	@Autowired
	private StockExchangeService stockExchangeService;
//	1
	@GetMapping(path = "")
	public ResponseEntity<List<StockExchangeDto>> getStockExchangesList() {
		return ResponseEntity.ok(stockExchangeService.getStockExchangesList());
	}
//	2
	@GetMapping(path = "/{id}")
	public ResponseEntity<StockExchangeDto> getStockExchangeDetails(@PathVariable int id)
//			throws StockExchangeNotFoundException
	{
		StockExchangeDto stockExchangeDto = stockExchangeService.findById(id);
		if(stockExchangeDto == null) {
			System.out.println("Stock Exchnage not found at id: "+id);
//			throw new StockExchangeNotFoundException("Stock Exchange Not Found for id : " + id);
		}
		return ResponseEntity.ok(stockExchangeDto);
	}
//	3
	@PostMapping(path = "/add")
	public ResponseEntity<StockExchangeDto> addStockExchange(@RequestBody StockExchangeDto stockExchangeDto) {
		return ResponseEntity.ok(stockExchangeService.addStockExchange(stockExchangeDto));
	}
//	4
	@PutMapping(path = "/edit")
	public ResponseEntity<StockExchangeDto> editStockExchange(@RequestBody StockExchangeDto stockExchangeDto)
//			throws StockExchangeNotFoundException 
	{
		StockExchangeDto updatedStockExchangeDto = stockExchangeService.editStockExchange(stockExchangeDto);
		if(updatedStockExchangeDto == null) {
			System.out.println("Stock Exchnage not found at id: "+stockExchangeDto.getId());
//			throw new StockExchangeNotFoundException("Stock Exchange Not Found for id : " + stockExchangeDto.getId());
		}
		return ResponseEntity.ok(updatedStockExchangeDto);
	}
//	5
	@DeleteMapping(path = "/{id}")
	public void deleteStockExchange(@PathVariable int id) {
		stockExchangeService.deleteStockExchange(id);
	}
//	6
	@GetMapping(path = "/{id}/companies")
	public ResponseEntity<List<CompanyDto>> getCompanies(@PathVariable int id)
//			throws StockExchangeNotFoundException  
	{
		List<CompanyDto> companyDtos = stockExchangeService.getCompanies(id);
		if(companyDtos.isEmpty()) {
			System.out.println("No companies found");
			return null;
//			throw new StockExchangeNotFoundException("Stock Exchange Not Found for id : " + id);
		}
		return ResponseEntity.ok(companyDtos);
	}
//}
	
	/* Feign Client Mapping */
	
	@PostMapping(path = "/{stockExchangeName}/add-company")
	public ResponseEntity<?> addCompanyToStockExchange(@PathVariable String stockExchangeName, @RequestBody CompanyDto companyDto)
//			throws StockExchangeNotFoundException  
	{
		StockExchangeDto stockExchangeDto = stockExchangeService.addCompanyToStockExchange(stockExchangeName, companyDto);
		if(stockExchangeDto == null) {
			System.out.println("Not Added");
//			throw new StockExchangeNotFoundException("Stock Exchange Not Found with name : " + stockExchangeName);
		}
		return ResponseEntity.ok(stockExchangeDto);
	}
}
