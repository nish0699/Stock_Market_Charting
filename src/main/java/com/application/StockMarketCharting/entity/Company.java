package com.application.StockMarketCharting.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import org.springframework.data.annotation.Id;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sun.istack.NotNull;

@Entity
@Table(name="company")
public class Company 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column(unique=true)
	private String companyName;
	@NotNull
	@Column(unique=true)
	private String companyCode;
	@NotNull
	private double turnover;
	@NotNull
	private String ceo;
	@NotNull
	private String boardOfDirectors;
	private String stockExchanges;	
	@NotNull
	private String sector;
	private String remarks;
	
	@OneToMany(mappedBy="company1", targetEntity=Ipo.class,fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	List<Ipo> ipoList;
	
	@OneToMany( mappedBy="company2",targetEntity=StockPrice.class,fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	List<StockPrice> stockPriceList;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,targetEntity= StockExchange.class,mappedBy="companyList")
	List<StockExchange> stockExchangeList;
//	
	@NotNull
	@ManyToOne(targetEntity=Sector.class,cascade=CascadeType.ALL)
	@JoinColumn(name="sector_id", referencedColumnName="id")
	private Sector sector1;
	
	
	public Company() {}
		
	public Company(int id, String companyName, String companyCode, double turnover, String ceo, String boardOfDirectors,
			String stockExchanges, String sector, String remarks) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.companyCode = companyCode;
		this.turnover = turnover;
		this.ceo = ceo;
		this.boardOfDirectors = boardOfDirectors;
		this.stockExchanges = stockExchanges;
		this.sector = sector;
		this.remarks = remarks;
	}
	
	//not sure check if this is okay 
	public Sector getSector1() {
		return sector1;
//		return sectorRepository.findBySectorName(sector);
	}

	public void setSector1(Sector sector1) {
		this.sector1=sector1;
//		sector1= sectorRepository.findBySectorName(sector);
	}

	public List<Ipo> getIpoList() {
		return ipoList;
	}

	public void addIpo(Ipo ipo) {
		this.ipoList.add(ipo);
	}

	public void removeIpo(Ipo ipo) {
		this.ipoList.remove(ipo);
	}
		
	public List<StockPrice> getStockPriceList() {
		return stockPriceList;
	}

	public void addStockPrice(StockPrice stockPrice) {
		this.stockPriceList.add(stockPrice);
	}
	public void removeStockPrice(StockPrice stockPrice) {
		this.stockPriceList.remove(stockPrice);
	}
	
	public void addStockExchangeList(StockExchange stockExchange) {
		this.stockExchangeList.add(stockExchange);
	}
	public void removeStockPriceList(StockExchange stockExchange) {
		this.stockExchangeList.remove(stockExchange);
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
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public double getTurnover() {
		return turnover;
	}
	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getBoardOfDirectors() {
		return boardOfDirectors;
	}
	public void setBoardOfDirectors(String boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}
	public String getStockExchanges() {
		return stockExchanges;
	}
	public void setStockExchanges(String stockExchanges) {
		this.stockExchanges = stockExchanges;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getremarks() {
		return remarks;
	}
	public void setremarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", companyCode=" + companyCode + ", turnover="
				+ turnover + ", ceo=" + ceo + ", boardOfDirectors=" + boardOfDirectors + ", stockExchanges="
				+ stockExchanges + ", sector=" + sector + ", remarks=" + remarks + "]";
	}
	
	
	
}

