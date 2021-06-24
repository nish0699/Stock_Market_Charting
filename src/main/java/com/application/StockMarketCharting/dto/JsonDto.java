package com.application.StockMarketCharting.dto;

import lombok.Data;

@Data
public class JsonDto {
	 private String companyName;
	 private String stockExchange;
	 private int count;
	 private String fromDate;
	 private String toDate;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	@Override
	public String toString() {
		return "JsonDto [companyName=" + companyName + ", stockExchange=" + stockExchange + ", count=" + count
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	 
	 
	
}
