package com.application.StockMarketCharting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.application.StockMarketCharting.Service.IpoService;
import com.application.StockMarketCharting.dto.IpoDto;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/ipos")
public class IpoController 
{
	@Autowired
	private IpoService ipoService;
//	1
	@GetMapping(path = "")
	public ResponseEntity<List<IpoDto>> findAll() {
		return ResponseEntity.ok(ipoService.findAll());
	}
//	2
	@GetMapping(path = "/{id}")
	public ResponseEntity<IpoDto> findById(@PathVariable int id)
			//throws IpoNotFoundException
	{
		IpoDto ipoDto = ipoService.findById(id);
		if(ipoDto == null) {
//			throw new IpoNotFoundException("Ipo not found for id : " + id);
		}
		return ResponseEntity.ok(ipoDto);
	}
//	3
	@PostMapping(path = "/add-ipo")
	public ResponseEntity<IpoDto> save(@RequestBody IpoDto ipoDto)
//			throws CompanyNotFoundException
	{
		
		IpoDto addedIpoDto = ipoService.save(ipoDto);
		if(addedIpoDto == null) {
//			throw new CompanyNotFoundException("Company not found with name : " + ipoDto.getCompanyName());
			System.out.println("ipo not added");
			return null;
		}
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(addedIpoDto);
	}
//	4
	@PutMapping(path = "/update-ipo/{id}")
	public ResponseEntity<IpoDto> update(@RequestBody IpoDto ipoDto,@PathVariable int id)
//			throws IpoNotFoundException
	{
		if(ipoDto==null)
			return null;
		System.out.println(ipoDto.toString());
		IpoDto updatedIpoDto = ipoService.update(ipoDto,id);
		if(updatedIpoDto == null) {
			System.out.println("ipo not updated");
			return null;
//			throw new IpoNotFoundException("Ipo not found for id : " + ipoDto.getId());
		}
		return ResponseEntity.ok(updatedIpoDto);
	}
//	5
	@CrossOrigin(origins="http://localhost:3000")
	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable int id) {
		ipoService.deleteById(id);
	}
}
