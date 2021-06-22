package com.application.StockMarketCharting.dto;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class StockPriceDto {
	private int id;
	@NotNull
	private String companyCode;
	@NotNull
	private String stockExchanges;
	@NotNull
	private double currentPrice;
	@NotNull
	private String date1;
	@NotNull
	private String time1;
	
	
	public StockPriceDto() {}
	public StockPriceDto(int id, String companyCode, String stockExchanges, double currentPrice, String date1,
			String time1) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.stockExchanges = stockExchanges;
		this.currentPrice = currentPrice;
		this.date1 = date1;
		this.time1 = time1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getStockExchanges() {
		return stockExchanges;
	}
	public void setStockExchanges(String stockExchanges) {
		this.stockExchanges = stockExchanges;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	@Override
	public String toString() {
		return "StockPriceDto [id=" + id + ", companyCode=" + companyCode + ", stockExchanges=" + stockExchanges
				+ ", currentPrice=" + currentPrice + ", date1=" + date1 + ", time1=" + time1 + "]";
	}
	
	
}
