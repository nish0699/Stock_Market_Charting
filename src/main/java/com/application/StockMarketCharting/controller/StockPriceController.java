package com.application.StockMarketCharting.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.application.StockMarketCharting.Service.impl.StockPriceServiceImpl;
import com.application.StockMarketCharting.dto.CompanyCompareRequestDto;
import com.application.StockMarketCharting.dto.JsonDto;
import com.application.StockMarketCharting.dto.SectorCompareRequestDto;
import com.application.StockMarketCharting.dto.StockPriceDto;
import com.application.StockMarketCharting.entity.StockPrice;

import net.minidev.json.JSONObject;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/stockprices")
public class StockPriceController 
{
	@Autowired
	private StockPriceServiceImpl stockPriceService;
	
	@GetMapping(path = "")
	public ResponseEntity<List<StockPriceDto>> findAll() {
		return ResponseEntity.ok(stockPriceService.findAll());
	}
	@GetMapping(path = "/{id}")
	public ResponseEntity<StockPriceDto> findById(@PathVariable int id)
	{
		StockPriceDto stockPriceDto = stockPriceService.findById(id);
		if(stockPriceDto == null) {
		}
		return ResponseEntity.ok(stockPriceDto);
	}
	@PostMapping(path = "/compareCompany")
	public ResponseEntity<?> companyComparison(@RequestBody CompanyCompareRequestDto compareRequest)
	{
		System.out.println(compareRequest.toString());
		List<StockPriceDto> stockPriceDtos = null;
		try {
			stockPriceDtos = stockPriceService.getStockPricesForCompanyComparison(compareRequest);
			for(StockPriceDto s: stockPriceDtos)
				System.out.println("stockprice=>"+s);
		} catch (ParseException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Date Format");
		}
		return ResponseEntity.ok(stockPriceDtos);
	}
	@PostMapping(path = "/compareSector")
	public ResponseEntity<?> sectorComparison(@RequestBody SectorCompareRequestDto compareRequest)
	{
		List<StockPriceDto> stockPriceDtos = null;
		try {
			stockPriceDtos = stockPriceService.getStockPricesForSectorComparison(compareRequest);
		} catch (ParseException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Date Format");
		}
		return ResponseEntity.ok(stockPriceDtos);
	}
	@PostMapping(path = "/")
	public ResponseEntity<?> save(@RequestBody List<StockPriceDto> stockPriceDtos) {
		
		List<StockPriceDto> stockList=stockPriceService.save(stockPriceDtos);
		if(stockList.isEmpty())
		{
			System.out.println("No List added");
			return null;
		}
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(stockList);
	}
	
	
	@PutMapping(path = "")
	public ResponseEntity<StockPriceDto> update(@RequestBody StockPriceDto stockPriceDto)
	{
		StockPriceDto updatedStockPriceDto = stockPriceService.update(stockPriceDto);
		if(updatedStockPriceDto == null) {
			System.out.println("No row updated");
			return null;
		}
		return ResponseEntity.ok(updatedStockPriceDto);
	}
	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable int id) {
		stockPriceService.deleteById(id);
	}
	
	@PostMapping(path="/import")/*, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)*/
	public ResponseEntity<JsonDto> saveAll( @RequestBody JSONObject[] jsonObj)
	{
		
		JsonDto summary=stockPriceService.saveAll(jsonObj);
		
		System.out.println(summary.toString());
		return ResponseEntity.ok(summary);
	}
	
}
