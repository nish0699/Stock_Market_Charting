package com.application.StockMarketCharting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.application.StockMarketCharting.Service.StockPriceService;

@SpringBootApplication
public class StockMarketChartingApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StockMarketChartingApplication.class, args);
	}
	
	@Autowired
	StockPriceService stock;
	@Override
	public void run(String... args) throws Exception {
		

	}

}
