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
@Table(name="ipo")
public class Ipo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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


	@NotNull
	@ManyToOne(targetEntity=Company.class,cascade=CascadeType.ALL)
	@JoinColumn(name="company_id",referencedColumnName="id")
	private Company company1;
	
	public Ipo() {}
	public Ipo(int id, String companyName, String stockExchanges, double pricePerShare, int noOfShares,
			String openDateTime, String remarks){	//,Company company1) {
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
	
	
	
	public Company getCompany1() {
		return company1;
	}
	public void setCompany1(Company company1) {
		this.company1 = company1;
	}
	public int getId() {
		return id;
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
		return "Ipo [id=" + id + ", companyName=" + companyName + ", stockExchanges=" + stockExchanges
				+ ", pricePerShare=" + pricePerShare + ", noOfShares=" + noOfShares + ", openDateTime=" + openDateTime
				+ ", remarks=" + remarks + ", company1=" + company1 + "]";
	}
	
	
}
