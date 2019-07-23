package com.techelevator.dao.model;

public class Weather {

	private String parkCode;
	private int fiveDayForecastValue;
	private int lowInF;
	private int highInF;
	private int lowInC;
	private int highInC;
	private String forecast;
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}
	public int getLowInF() {
		return lowInF;
	}
	public void setLowInF(int lowInF) {
		this.lowInF = lowInF;
		this.lowInC = (int)((lowInF - 32) * (5.0/9));
	}
	public int getHighInF() {
		return highInF;
	}
	public void setHighInF(int highInF) {
		this.highInF = highInF;
		this.highInC = (int)((highInF - 32) * (5.0/9));
	}
	public int getHighInC() {
		return highInC;
	}
	public int getLowInC() {
		return lowInC;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public String getWeatherMessage() {
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
		
		return weatherMessage;
	}
	public String getTempMessage() {
		String tempMessage = "";
		if(highInF > 75) {
			tempMessage = "Its going to be a hot one.  Bring an extra gallon of water.  ";
		} else if (lowInF < 20) {
			tempMessage = "Make sure to dress very warm.  Beware hypothermia.  ";
		}
		if(highInF - lowInF > 20) {
			if(tempMessage.isEmpty()) {
				tempMessage = "Wear breathable layers";
			} else {
				tempMessage += ".  Wear breathable layers";
			}
		}
		return tempMessage;
	}
}
