package com.techelevator.dao;

import java.util.List;

import com.techelevator.dao.model.Park;

public interface ParkDao {
	public List<Park> getAllParks();
	
	public Park getParkByCode(String parkCode);
}
