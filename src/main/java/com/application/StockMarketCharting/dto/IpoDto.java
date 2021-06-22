package com.application.StockMarketCharting.dto;

import com.application.StockMarketCharting.entity.Company;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class IpoDto 
{
	@NotNull
	private int id;
	@NotNull
	private String companyName;
	@NotNull
	private String stockExchanges;
	@NotNull
	private double pricePerShare;
	@NotNull
	private int noOfShares;
	@NotNull
	private String openDateTime;
	private String remarks;
//	private Company company1;
	
	public IpoDto() {}
	
	public IpoDto(int id, String companyName, String stockExchanges, double pricePerShare, int noOfShares,
			String openDateTime, String remarks){//, Company company1) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.stockExchanges = stockExchanges;
		this.pricePerShare = pricePerShare;
		this.noOfShares = noOfShares;
		this.openDateTime = openDateTime;
		this.remarks = remarks;
//		this.company1=company1;
	}
	

//	public Company getCompany1() {
//		return company1;
//	}
//	public void setCompany1(Company company1)
//	{
//		this.company1=company1;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStockExchanges() {
		return stockExchanges;
	}
	public void setStockExchanges(String stockExchanges) {
		this.stockExchanges = stockExchanges;
	}
	public double getPricePerShare() {
		return pricePerShare;
	}
	public void setPricePerShare(double pricePerShare) {
		this.pricePerShare = pricePerShare;
	}
	public int getNoOfShares() {
		return noOfShares;
	}
	public void setNoOfShares(int noOfShares) {
		this.noOfShares = noOfShares;
	}
	
	public String getOpenDateTime() {
		return openDateTime;
	}
	public void setOpenDateTime(String openDateTime) {
		this.openDateTime = openDateTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "IpoDto [id=" + id + ", companyName=" + companyName + ", stockExchanges=" + stockExchanges
				+ ", pricePerShare=" + pricePerShare + ", noOfShares=" + noOfShares + ", openDateTime=" + openDateTime
				+ ", remarks=" + remarks +  "]";
	}
	
	
}
