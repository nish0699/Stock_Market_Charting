package com.application.StockMarketCharting.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(StockCompanyId.class)
@Table(name="stock_company")
public class StockCompany {
    @Id
    private int stock_id;

    @Id
    private int company_id;

    // other fields, getters and setters
    
}