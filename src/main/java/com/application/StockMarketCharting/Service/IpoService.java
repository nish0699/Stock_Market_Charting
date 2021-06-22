package com.application.StockMarketCharting.Service;

import java.util.List;

import com.application.StockMarketCharting.dto.IpoDto;

public interface IpoService {
	public List<IpoDto> findAll();
	public IpoDto findById(int id);
	public IpoDto save(IpoDto ipoDto);
	public IpoDto update(IpoDto ipoDto);
	public void deleteById(int id);

}
