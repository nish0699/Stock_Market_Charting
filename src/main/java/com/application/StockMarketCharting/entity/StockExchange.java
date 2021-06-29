package com.application.StockMarketCharting.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.NonNull;

@Entity
@Table(name="stock_exchange")
public class StockExchange {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NonNull
	@Column(unique=true)
	private String stockExchangeName;
	private String stockDescription;
	
	private String address;
	private String remarks;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL,targetEntity=Company.class)
	@JoinTable(name="stock_company", 
			joinColumns={@JoinColumn(name="stock_id")}
			,inverseJoinColumns= {@JoinColumn(name="company_id")})
	List<Company> companyList;
//
	public StockExchange() {}
	public StockExchange(int id, @NonNull String stockExchangeName,  String stockDescription,
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

	public List<Company> getCompanyList() {
		return companyList;
	}
	public void addCompany(Company company) {
		this.companyList.add(company);
	}
	public void removeCompany(Company company) {
		this.companyList.remove(company);
	}
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
