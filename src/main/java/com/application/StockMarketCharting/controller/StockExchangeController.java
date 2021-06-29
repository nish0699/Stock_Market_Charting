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

import com.application.StockMarketCharting.Service.StockExchangeService;
import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.StockExchangeDto;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/stock-exchanges")
public class StockExchangeController 
{
	@Autowired
	private StockExchangeService stockExchangeService;
	@GetMapping(path = "")
	public ResponseEntity<List<StockExchangeDto>> getStockExchangesList() {
		return ResponseEntity.ok(stockExchangeService.getStockExchangesList());
	}
	@GetMapping(path = "/{id}")
	public ResponseEntity<StockExchangeDto> getStockExchangeDetails(@PathVariable int id)
	{
		StockExchangeDto stockExchangeDto = stockExchangeService.findById(id);
		if(stockExchangeDto == null) {
			System.out.println("Stock Exchnage not found at id: "+id);
		}
		return ResponseEntity.ok(stockExchangeDto);
	}
	@PostMapping(path = "/add")
	public ResponseEntity<StockExchangeDto> addStockExchange(@RequestBody StockExchangeDto stockExchangeDto) {
		return ResponseEntity.ok(stockExchangeService.addStockExchange(stockExchangeDto));
	}
	@PutMapping(path = "/edit/{id}")
	public ResponseEntity<StockExchangeDto> editStockExchange(@RequestBody StockExchangeDto stockExchangeDto,@PathVariable int id)
	{
		stockExchangeDto.setId(id);
		StockExchangeDto updatedStockExchangeDto = stockExchangeService.editStockExchange(stockExchangeDto);
		if(updatedStockExchangeDto == null) {
			System.out.println("Stock Exchnage not found at id: "+stockExchangeDto.getId());
		}
		return ResponseEntity.ok(updatedStockExchangeDto);
	}
	@DeleteMapping(path = "/{id}")
	public void deleteStockExchange(@PathVariable int id) {
		System.out.println("Delete");
		stockExchangeService.deleteStockExchange(id);
	}
	@GetMapping(path = "/{id}/companies")
	public ResponseEntity<List<CompanyDto>> getCompanies(@PathVariable int id)
	{
		List<CompanyDto> companyDtos = stockExchangeService.getCompanies(id);
		if(companyDtos.isEmpty()) {
			System.out.println("No companies found");
			return null;
		}
		return ResponseEntity.ok(companyDtos);
	}
	
	
	@PostMapping(path = "/{stockExchangeName}/add-company")
	public ResponseEntity<?> addCompanyToStockExchange(@PathVariable String stockExchangeName, @RequestBody CompanyDto companyDto)
	{
		StockExchangeDto stockExchangeDto = stockExchangeService.addCompanyToStockExchange(stockExchangeName, companyDto);
		if(stockExchangeDto == null) {
			System.out.println("Not Added");
		}
		return ResponseEntity.ok(stockExchangeDto);
	}
}
