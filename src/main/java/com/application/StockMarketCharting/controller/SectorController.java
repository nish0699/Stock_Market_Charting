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

import com.application.StockMarketCharting.Service.impl.SectorServiceImpl;
import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.SectorDto;

@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/sectors")
public class SectorController 
{
	@Autowired
	private SectorServiceImpl sectorService;
	@GetMapping(path = "")
	public ResponseEntity<List<SectorDto>> findAll() {
		
		return ResponseEntity.ok(sectorService.findAll());
	}
	@GetMapping(path = "/{id}")
	public ResponseEntity<SectorDto> findById(@PathVariable int id)
	{
		SectorDto sectorDto = sectorService.findById(id);
		if(sectorDto == null) {
		}
		return ResponseEntity.ok(sectorDto);
	}
	@PostMapping(path = "/add")
	public ResponseEntity<SectorDto> save(@RequestBody SectorDto sectorDto) {
		SectorDto addSector=sectorService.addSector(sectorDto);
		if(addSector==null)
			return null;
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(addSector);
	}
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<SectorDto> update(@RequestBody SectorDto sectorDto,  @PathVariable int id)
	{
		sectorDto.setId(id);
		SectorDto updatedSectorDto = sectorService.updateSector(sectorDto);
		if(updatedSectorDto == null) {
			return null;
		}
		return ResponseEntity.ok(updatedSectorDto);
	}
	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable int id) {
		sectorService.deleteById(id);
	}
	@GetMapping(path = "/{sectorName}/companies")
	public ResponseEntity<List<CompanyDto>> getCompanies(@PathVariable String sectorName)
	{
		List<CompanyDto> companyDtos = sectorService.getCompanies(sectorName);
		if(companyDtos == null) {
		}
		return ResponseEntity.ok(companyDtos);
	}
	
	@PostMapping(path = "/{sectorName}/add-company")
	public void addCompanyToSector(@PathVariable String sectorName, @RequestBody CompanyDto companyDto)
	{
		SectorDto sectorDto = sectorService.addCompanyToSector(sectorName, companyDto);
		if(sectorDto == null) {
			
		}
	}
}
