package com.application.StockMarketCharting.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.NonNull;

@Entity
@Table(name="sector")
public class Sector {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NonNull
	@Column(unique=true)
	private String sectorName;
	private String remarks;
	
	@OneToMany(mappedBy="sector1",targetEntity=Company.class,fetch=FetchType.EAGER)
	private List<Company> companyList = new ArrayList<>();

	public Sector() {}
	public Sector(int id, @NonNull String sectorName, String remarks){//, List<Company> companyList) {
		super();
		this.id = id;
		this.sectorName = sectorName;
		this.remarks = remarks;
		//this.companyList = companyList;
	}

	public int getId() {
		return id;
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

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void addCompany(Company company) {
		this.companyList.add(company);// company;
	}
	public void removeCompany(Company company) {
		this.companyList.remove(company);// company;
	}
	@Override
	public String toString() {
		return "Sector [id=" + id + ", sectorName=" + sectorName + ", remarks=" + remarks +  "]";
	}

	
}
