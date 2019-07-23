package com.techelevator.dao.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Survey {
	private int surveyId;
	
	@NotBlank(message="*")
	private String parkCode;
	
	@NotBlank(message="*")
	private String emailAddress;
	
	@NotBlank(message="*")
	private String state;
	
	@NotBlank(message="*")
	private String activityLevel;
	
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
}
