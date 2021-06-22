package com.application.StockMarketCharting.controller;

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

import com.application.StockMarketCharting.Service.impl.SectorServiceImpl;
import com.application.StockMarketCharting.dto.CompanyDto;
import com.application.StockMarketCharting.dto.SectorDto;

@RestController
@RequestMapping("/sectors")
public class SectorController 
{
	@Autowired
	private SectorServiceImpl sectorService;
//	1
	@GetMapping(path = "")
	public ResponseEntity<List<SectorDto>> findAll() {
		
		return ResponseEntity.ok(sectorService.findAll());
	}
//	2
	@GetMapping(path = "/{id}")
	public ResponseEntity<SectorDto> findById(@PathVariable int id)
//			throws SectorNotFoundException 
	{
		SectorDto sectorDto = sectorService.findById(id);
		if(sectorDto == null) {
//			throw new SectorNotFoundException("Sector not found for id : " + id);
		}
		return ResponseEntity.ok(sectorDto);
	}
//	3
	@PostMapping(path = "/add")
	public ResponseEntity<SectorDto> save(@RequestBody SectorDto sectorDto) {
		SectorDto addSector=sectorService.addSector(sectorDto);
		if(addSector==null)
			return null;
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(addSector);
	}
//	4
	@PutMapping(path = "/update")
	public ResponseEntity<SectorDto> update(@RequestBody SectorDto sectorDto)
//			throws SectorNotFoundException
	{
		
		SectorDto updatedSectorDto = sectorService.updateSector(sectorDto);
		if(updatedSectorDto == null) {
			return null;
//			throw new SectorNotFoundException("Sector not found for id : " + sectorDto.getId());
		}
		return ResponseEntity.ok(updatedSectorDto);
	}
//	5
	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable int id) {
		sectorService.deleteById(id);
	}
//	6
	@GetMapping(path = "/{sectorName}/companies")
	public ResponseEntity<List<CompanyDto>> getCompanies(@PathVariable String sectorName)
//			throws SectorNotFoundException 
	{
		List<CompanyDto> companyDtos = sectorService.getCompanies(sectorName);
		if(companyDtos == null) {
//			throw new SectorNotFoundException("Sector not found for id : " + id);
		}
		return ResponseEntity.ok(companyDtos);
	}
//}
	
	/* Feign Client Mapping */
//	7	
	@PostMapping(path = "/{sectorName}/add-company")
	public void addCompanyToSector(@PathVariable String sectorName, @RequestBody CompanyDto companyDto)
//			throws SectorNotFoundException 
	{
		SectorDto sectorDto = sectorService.addCompanyToSector(sectorName, companyDto);
		if(sectorDto == null) {
//			throw new SectorNotFoundException("Sector not found with name : " + sectorName);
		}
	}
}
//}
//