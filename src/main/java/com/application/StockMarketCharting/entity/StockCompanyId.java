package com.application.StockMarketCharting.entity;

import java.io.Serializable;

public class StockCompanyId implements Serializable  {

	private int stock_id;
	private int company_id;
	public StockCompanyId() {}
	public StockCompanyId(int stock_id, int company_id) {
		super();
		this.stock_id = stock_id;
		this.company_id = company_id;
	}

	
	
	
}
