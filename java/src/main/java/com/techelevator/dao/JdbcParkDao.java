package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.model.Park;

@Component
public class JdbcParkDao implements ParkDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	@Override
	public List<Park> getAllParks() {
		List<Park> parkList = new ArrayList<>();
		
		String sqlQuery = "SELECT parkcode, parkname, state, acreage, elevationinfeet, " 
									+ "milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, "
										+ "inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies " 
										+ "FROM park ORDER BY parkname";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery);
		
		while (results.next()) {
			parkList.add(mapRowToPark(results));
		}
		
		return parkList;
	}
	@Override
	public Park getParkByCode(String parkCode) {
		Park park = null;
		
		String sqlQuery = "SELECT parkcode, parkname, state, acreage, elevationinfeet, "
						+ "milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, "
						+ "inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies "
						+ "FROM park WHERE parkcode = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, parkCode);
		
		if(results.next()) {
			park = mapRowToPark(results);
		}
		
		return park;
	}

	private Park mapRowToPark(SqlRowSet row) {
		Park p = new Park();
		
		p.setParkCode(row.getString("parkcode"));
		p.setParkName(row.getString("parkname"));
		p.setState(row.getString("state"));
		p.setAcreage(row.getInt("acreage"));
		p.setElevationInFeet(row.getInt("elevationinfeet"));
		p.setMilesOfTrail(row.getDouble("milesoftrail"));
		p.setNumberOfCampsites(row.getInt("numberofcampsites"));
		p.setClimate(row.getString("climate"));
		p.setYearFounded(row.getInt("yearfounded"));
		p.setAnnualVisitorCount(row.getInt("annualvisitorcount"));
		p.setInspirationalQuote(row.getString("inspirationalquote"));
		p.setInspirationalQuoteSource(row.getString("inspirationalquotesource"));
		p.setParkDescription(row.getString("parkdescription"));
		p.setEntryFee(row.getInt("entryfee"));
		p.setNumberOfAnimalSpecies(row.getInt("numberofanimalspecies"));
		
		return p;
	}
}
