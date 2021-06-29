package com.application.StockMarketCharting.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.StockMarketCharting.entity.StockPrice;

@Repository
public interface StockPriceDao extends CrudRepository<StockPrice,Integer>{
	public StockPrice findById(int id);
//	@Query(value="select * from stock_price s where s.company_code=:code and s.stock_exchanges=:stock and s.date1=:date1 and s.time1=:time1 and current_price=:price", nativeQuery=true)
//	public StockPrice search(@Param("code") String code, @Param("stock") String stock,@Param("date1") String date1,@Param("time1") String time1,@Param("price")double d);
	@Query(value="select * from stock_price s where s.company_code=:companyCode and s.stock_exchanges=:stockExchanges", nativeQuery =true)
	public List<StockPrice> findByCompanyCodeAndStockExchanges(@Param("companyCode") String companyCode,@Param("stockExchanges")  String stockExchanges);

//	public List<StockPrice> findByCompanyNameAndStockExchanges(String companyName, String stockExchange);
	
}
