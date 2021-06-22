package com.application.StockMarketCharting.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.application.StockMarketCharting.dto.SectorCompareRequestDto;
import com.application.StockMarketCharting.dto.StockPriceDto;

@RestController
@RequestMapping("/stockprices")
public class StockPriceController 
{
	@Autowired
	private StockPriceServiceImpl stockPriceService;
	
	@GetMapping(path = "")
	public ResponseEntity<List<StockPriceDto>> findAll() {
		return ResponseEntity.ok(stockPriceService.findAll());
	}
//	1
	@GetMapping(path = "/{id}")
	public ResponseEntity<StockPriceDto> findById(@PathVariable int id)
//			throws StockPriceNotFoundException 
	{
		StockPriceDto stockPriceDto = stockPriceService.findById(id);
		if(stockPriceDto == null) {
//			throw new StockPriceNotFoundException("Stock Price Not Found with id : " + id);
		}
		return ResponseEntity.ok(stockPriceDto);
	}
//	2
	@GetMapping(path = "/compareCompany")
	public ResponseEntity<?> companyComparison(@RequestBody CompanyCompareRequestDto compareRequest)
	{
		List<StockPriceDto> stockPriceDtos = null;
		try {
			stockPriceDtos = stockPriceService.getStockPricesForCompanyComparison(compareRequest);
		} catch (ParseException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Date Format");
		}
		return ResponseEntity.ok(stockPriceDtos);
	}
////3	
	@GetMapping(path = "/compareSector")
//	@HystrixCommand(fallbackMethod = "defaultResponse")
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
//	4
	@PostMapping(path = "")
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
//	5
	@PutMapping(path = "")
	public ResponseEntity<StockPriceDto> update(@RequestBody StockPriceDto stockPriceDto)
//			throws StockPriceNotFoundException
	{
		StockPriceDto updatedStockPriceDto = stockPriceService.update(stockPriceDto);
		if(updatedStockPriceDto == null) {
			System.out.println("No row updated");
			return null;
//			throw new StockPriceNotFoundException("Stock Price not found with id : " + stockPriceDto.getId());
		}
		return ResponseEntity.ok(updatedStockPriceDto);
	}
//	6
	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable int id) {
		stockPriceService.deleteById(id);
	}
	
//	7
//	/* Feign Client Default Response */
//	
//	public ResponseEntity<?> defaultResponse(@RequestBody SectorCompareRequestDto compareRequest) {
//		String err = "Fallback error as the microservice is down.";
//		return ResponseEntity
//				.status(HttpStatus.SERVICE_UNAVAILABLE)
//				.body(err);
//	}
}

