package com.techelevator;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.dao.ParkDao;
import com.techelevator.dao.SurveyDao;
import com.techelevator.dao.model.Park;
import com.techelevator.dao.model.Result;
import com.techelevator.dao.model.Survey;

@Controller
public class SurveyController {
	@Autowired
	private SurveyDao surveyDao;
	
	@Autowired
	private ParkDao parkDao;
	
	@Autowired
	private AuthProvider auth;
	
	@GetMapping(path="/survey")
	public String showSurveyPage(
			ModelMap mm, 
			HttpSession session,
			RedirectAttributes redirectAttributes) throws UnauthorizedException {
		
		if(auth.isLoggedIn() == false) {
			redirectAttributes.addFlashAttribute("message", "Please login to view page");
			return "redirect:/login";
		}
		
		if(mm.containsAttribute("survey") == false) {
			Survey empty = new Survey();
			mm.put("survey", empty);
		}
		
		List<Park> parkList = parkDao.getAllParks();
		mm.put("parkList", parkList);
		
		return "survey";
	}
	
	@PostMapping(path="/survey")
	public String processSurvey(
			@Valid @ModelAttribute Survey survey, 
			BindingResult results, 
			RedirectAttributes flash, 
			ModelMap mm) {
		
		if(results.hasErrors()) {
			flash.addFlashAttribute("survey", survey);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX+"survey", results);
			return "redirect:/survey";
		}
		surveyDao.save(survey);
		return "redirect:/surveyResults";
	}
	
	@GetMapping(path="/surveyResults")
	public String showSurveyResultsPage(ModelMap mm, RedirectAttributes redirectAttributes) throws UnauthorizedException {
		if(auth.isLoggedIn() == false) {
			redirectAttributes.addFlashAttribute("message", "Please login to view page");
			return "redirect:/login";
		}
		
		List<Result> resultList = surveyDao.getAllResults();
		
		mm.put("resultList", resultList);
		
		return "surveyResults";
	}	
	
}
