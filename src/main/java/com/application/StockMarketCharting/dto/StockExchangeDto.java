package com.application.StockMarketCharting.dto;

import java.util.List;

import javax.persistence.Column;

import com.application.StockMarketCharting.entity.Company;

import lombok.Data;
import lombok.NonNull;
@Data
public class StockExchangeDto {

	private int id;	
	@NonNull
	@Column(unique=true)
	private String stockExchangeName;
	private String stockDescription;
	private String address;
	private String remarks;
//	List<Company> companyList;

	public StockExchangeDto() {}
	public StockExchangeDto(int id, @NonNull String stockExchangeName, String stockDescription,
			 String address,  String remarks){//, List<Company> companyList) {
		super();
		this.id = id;
		this.stockExchangeName = stockExchangeName;
		this.stockDescription = stockDescription;
		this.address = address;
		this.remarks = remarks;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id=id;
	}
//	public List<Company> getCompanyList() {
//		return companyList;
//	}
//	public void addCompany(Company company) {
//		this.companyList.add(company);
//	}
//	public void removeCompany(Company company) {
//		this.companyList.remove(company);
//	}
	public String getStockExchangeName() {
		return stockExchangeName;
	}
	public void setStockExchangeName(String stockExchangeName) {
		this.stockExchangeName = stockExchangeName;
	}
	public String getstockDescription() {
		return stockDescription;
	}
	public void setstockDescription(String stockDescription) {
		this.stockDescription = stockDescription;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "StockExchange [id=" + id + ", stockExchangeName=" + stockExchangeName + ", stockDescription="
				+ stockDescription + ", address=" + address + ", remarks=" + remarks + "]";
	}

}
