package com.application.StockMarketCharting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCompareRequestDto
{
	private String companyCode;
	private String stockExchangeName;
	private String fromPeriod;
	private String toPeriod;
	private String periodicity;
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getStockExchangeName() {
		return stockExchangeName;
	}
	public void setStockExchangeName(String stockExchangeName) {
		this.stockExchangeName = stockExchangeName;
	}
	public String getFromPeriod() {
		return fromPeriod;
	}
	public void setFromPeriod(String fromPeriod) {
		this.fromPeriod = fromPeriod;
	}
	public String getToPeriod() {
		return toPeriod;
	}
	public void setToPeriod(String toPeriod) {
		this.toPeriod = toPeriod;
	}
	public String getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}
	@Override
	public String toString() {
		return "CompanyCompareRequestDto [companyCode=" + companyCode + ", stockExchangeName=" + stockExchangeName
				+ ", fromPeriod=" + fromPeriod + ", toPeriod=" + toPeriod + ", periodicity=" + periodicity + "]";
	}
	
	
	
}
