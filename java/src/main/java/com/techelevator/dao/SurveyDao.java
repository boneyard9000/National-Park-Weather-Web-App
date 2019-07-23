package com.techelevator.dao;

import java.util.List;

import com.techelevator.dao.model.Result;
import com.techelevator.dao.model.Survey;

public interface SurveyDao {
	public List<Survey> getAllSurveys();
	
	public Survey getSurveyById(int surveyId);
	
	public List<Survey> getSurveyListByParkCode(String parkCode);
	
	public void save(Survey surveyResult);
	
	public List<Result> getAllResults();
}
