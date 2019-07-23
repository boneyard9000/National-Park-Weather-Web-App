package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.model.Result;
import com.techelevator.dao.model.Survey;

@Component
public class JdbcSurveyDao implements SurveyDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Survey> getAllSurveys() {
		List<Survey> resultList = new ArrayList<>();
		
		String sqlQuery = "SELECT surveyid, parkcode, emailaddress, state, activitylevel FROM survey_result";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery);
		
		while (results.next()) {
			resultList.add(mapRowToSurveyResult(results));
		}

		
		return resultList;
	}

	@Override
	public Survey getSurveyById(int surveyId) {
		Survey surveyResult = null;
		
		String sqlQuery = "SELECT surveyid, parkcode, emailaddress, state, activitylevel FROM survey_result WHERE surveyid = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, surveyId);
		
		if(results.next()) {
			surveyResult = this.mapRowToSurveyResult(results);
		}
		
		return surveyResult;
	}

	@Override
	public List<Survey> getSurveyListByParkCode(String parkCode) {
		List<Survey> resultList = new ArrayList<>();
		
		String sqlQuery = "SELECT surveyid, parkcode, emailaddress, state, activitylevel FROM survey_result WHERE parkcode ILIKE ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, parkCode);
		
		while (results.next()) {
			resultList.add(mapRowToSurveyResult(results));
		}

		
		return resultList;
	}

	@Override
	public void save(Survey newSurvey) {
		
			String sqlSaveSurvey = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) " +
									"VALUES (?,?,?,?) RETURNING surveyid";
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSaveSurvey, newSurvey.getParkCode(),
																			newSurvey.getEmailAddress(),
																			newSurvey.getState(),
																			newSurvey.getActivityLevel());
	}
	@Override
	public List<Result> getAllResults() {
		String sqlResultsQuery = "SELECT park.parkname, park.parkcode, "
				+ "COUNT (survey_result.surveyid) FROM park " + 
				"JOIN survey_result ON park.parkcode = survey_result.parkcode " + 
				"WHERE park.parkcode = survey_result.parkcode " + 
				"GROUP BY (park.parkname, park.parkcode) " + 
				"ORDER BY COUNT (park.parkname) DESC, park.parkname";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlResultsQuery);
		
		List<Result> resultList = new ArrayList<Result>();
		
		while(result.next()) {
			resultList.add(mapRowToResult(result));
		}
		
		return resultList;

	}

	private Survey mapRowToSurveyResult(SqlRowSet row) {
		Survey sr = new Survey();
		
		sr.setSurveyId(row.getInt("surveyid"));
		sr.setParkCode(row.getString("parkcode"));
		sr.setEmailAddress(row.getString("emailaddress"));
		sr.setState(row.getString("state"));
		sr.setActivityLevel(row.getString("activitylevel"));
		
		return sr;
	}
	public Result mapRowToResult(SqlRowSet row) {
		Result r = new Result();
		r.setParkCode(row.getString("parkcode"));
		r.setParkName(row.getString("parkname"));
		r.setSurveyCount(row.getInt("count"));
		
		return r;
	}
}
