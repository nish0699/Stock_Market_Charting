package com.application.StockMarketCharting.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.StockMarketCharting.entity.Sector;

@Repository
public interface SectorDao extends CrudRepository<Sector,Integer>{

	public Sector findById(int id);
	public Sector findBySectorName(String sectorName);
}
