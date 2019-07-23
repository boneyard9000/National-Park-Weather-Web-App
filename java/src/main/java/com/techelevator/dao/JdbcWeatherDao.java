package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.model.Weather;

@Component
public class JdbcWeatherDao implements WeatherDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getAllWeather() {
		List<Weather> weatherList = new ArrayList<>();
		
		String sqlQuery = "SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery);
		
		while(results.next()) {
			weatherList.add(mapRowToWeather(results));
		}
		
		return weatherList;
	}

	@Override
	public List<Weather> getWeatherListByParkCode(String parkCode) {
		List<Weather> weatherList = new ArrayList<>();
		
		String sqlQuery = "SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather WHERE parkcode ILIKE ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, parkCode);
		
		while (results.next()) {
			weatherList.add(mapRowToWeather(results));
		}
		
		return weatherList;
	}

	private Weather mapRowToWeather(SqlRowSet row) {
		Weather w = new Weather();
		
		
		int highInF = row.getInt("high");
		int lowInF = row.getInt("low");
		int highInC = (int)((highInF - 32) * (5.0/9));
		int lowInC = (int)((lowInF - 32) * (5.0/9));		
		
		String tempMessage = "";
		if(highInF > 75) {
			tempMessage = "Its going to be a hot one.  Bring an extra gallon of water";
		} else if (lowInF < 20) {
			tempMessage = "Make sure to dress very warm.  Beware hypothermia";
		}
		if(highInF - lowInF > 20) {
			if(tempMessage.isEmpty()) {
				tempMessage = "Wear breathable layers";
			} else {
				tempMessage += ".  Wear breathable layers";
			}
		}
		
		String forecast = row.getString("forecast");
		String weatherMessage = "";
		if(forecast.equals("partly cloudy")) {
			forecast = "partlyCloudy";
		} else if(forecast.equals("snow")) {
			weatherMessage = "Pack your snow shoes";
		} else if(forecast.equals("rain")) {
			weatherMessage = "Make sure to wear waterproof shoes and pack rain gear";
		} else if(forecast.equals("thunderstorms")) {
			weatherMessage = "Seek shelter and avoid hiking on exposed ridges";
		} else if(forecast.equals("sunny")) {
			weatherMessage = "Bring sunblock";
		}
		
		w.setParkCode(row.getString("parkcode"));
		w.setFiveDayForecastValue(row.getInt("fivedayforecastvalue"));
		w.setHighInF(row.getInt("high"));
		w.setLowInF(row.getInt("low"));
		if(row.getString("forecast").equals("partly cloudy")) {
			w.setForecast("partlyCloudy");
		} else {
			w.setForecast(row.getString("forecast"));
		}
		
		return w;
	}
}
