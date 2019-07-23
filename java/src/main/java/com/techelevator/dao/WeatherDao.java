package com.techelevator.dao;

import java.util.List;

import com.techelevator.dao.model.Weather;

public interface WeatherDao {
	public List<Weather> getAllWeather();
	public List<Weather> getWeatherListByParkCode(String parkCode);
}
