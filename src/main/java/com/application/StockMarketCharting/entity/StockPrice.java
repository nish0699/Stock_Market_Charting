package com.application.StockMarketCharting.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="stock_price")
public class StockPrice 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@ManyToOne(targetEntity=Company.class,cascade=CascadeType.ALL)
	@JoinColumn(name="company_id",referencedColumnName="id")
	private Company company2;
	
	public StockPrice() {}
	public StockPrice(int id, String companyCode, String stockExchanges, double currentPrice, String date1,
			String time1) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.stockExchanges = stockExchanges;
		this.currentPrice = currentPrice;
		this.date1 = date1;
		this.time1 = time1;
	}
	
	
	public Company getCompany2() {
		return company2;
//		return companyRepository.findByCompanyCode(companyCode);
	}
	public void setCompany2(Company company2) {
		this.company2 = company2;
	}
	public int getId() {
		return id;
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
		return "StockPrice [id=" + id + ", companyCode=" + companyCode + ", stockExchanges=" + stockExchanges
				+ ", currentPrice=" + currentPrice + ", date1=" + date1 + ", time1=" + time1 + ", company2=" + company2 + " ]";
	}
	
	
	
	
	

}


