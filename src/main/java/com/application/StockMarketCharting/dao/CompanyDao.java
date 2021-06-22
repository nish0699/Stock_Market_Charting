package com.application.StockMarketCharting.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.StockMarketCharting.entity.Company;
@Repository
public interface CompanyDao extends JpaRepository<Company,Integer>{

	public Company findById(int id);
	public List<Company> findByCompanyNameIgnoreCaseContaining(String companyName);
	public Company findByCompanyName(String companyName);
	public Company findByCompanyCode(String companyCode);
	@Transactional
	@Modifying
//	@Query(value="delete from stock_company s where s.company_id=2")
	public void deleteById(int id);
}
