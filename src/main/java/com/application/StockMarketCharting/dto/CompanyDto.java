package com.application.StockMarketCharting.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.application.StockMarketCharting.entity.Ipo;
import com.application.StockMarketCharting.entity.StockPrice;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class CompanyDto 
{	private int id;
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

	
	
	public CompanyDto() {}
		
	
	public CompanyDto(int id, String companyName, String companyCode, double turnover, String ceo,
			String boardOfDirectors, String stockExchanges, String sector, String remarks) {
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


		public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id=id;
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

