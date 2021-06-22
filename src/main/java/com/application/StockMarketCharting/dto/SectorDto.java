package com.application.StockMarketCharting.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.application.StockMarketCharting.entity.Company;

import lombok.Data;
import lombok.NonNull;

@Data
public class SectorDto {
	private int id;
	@NonNull
	private String sectorName;
	private String remarks;
	
//	@OneToMany(mappedBy="sector1",targetEntity=Company.class,fetch=FetchType.EAGER)
//	private List<Company> companyList = new ArrayList<>();

	public SectorDto() {}
	public SectorDto(int id, @NonNull String sectorName, String remarks){//, List<Company> companyList) {
		super();
		this.id = id;
		this.sectorName = sectorName;
		this.remarks = remarks;
		//this.companyList = companyList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

//	public List<Company> getCompanyList() {
//		return companyList;
//	}
//
//	public void addCompany(Company company) {
//		this.companyList.add(company);// company;
//	}
//	public void removeCompany(Company company) {
//		this.companyList.remove(company);// company;
//	}
	@Override
	public String toString() {
		return "Sector [id=" + id + ", sectorName=" + sectorName + ", remarks=" + remarks +  "]";
	}

	
}
