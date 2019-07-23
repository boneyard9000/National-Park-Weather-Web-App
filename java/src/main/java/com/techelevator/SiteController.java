package com.techelevator;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.dao.ParkDao;
import com.techelevator.dao.WeatherDao;

@Controller
public class SiteController {
	
    @Autowired
    private AuthProvider auth;
	@Autowired
	private ParkDao parkDao;
	
	@Autowired
	private WeatherDao weatherDao;
	
	
	@GetMapping(path= {"/", "/homePage"})
	public String getMainScreen(ModelMap mm, RedirectAttributes flash) throws UnauthorizedException {
		if (auth.isLoggedIn() == false) {
			flash.addFlashAttribute("message", "Please login to view page");
			return "redirect:/login";
		}
		
		mm.put("parkList", parkDao.getAllParks());
		return "homePage";
	}
	
	@GetMapping(path="/details")
	public String displayDetailsPage(
			@RequestParam String parkCode, 
			ModelMap mm, HttpSession session, 
			RedirectAttributes flash) throws UnauthorizedException {
		if(auth.isLoggedIn() == false) {
			flash.addFlashAttribute("message", "Please login to view page");
			return "redirect:/login";
		}
		if (session.getAttribute("tempChoice") == null) {
			session.setAttribute("tempChoice",  "F");
		}
		session.setAttribute("parkCode", parkCode);
		
		mm.put("park", parkDao.getParkByCode(parkCode));
		mm.put("weatherList", weatherDao.getWeatherListByParkCode(parkCode));
		return "details";
	}
	
	@PostMapping(path="/details")
	public String changeDetailsPageTemp (
			HttpSession session,
			@RequestParam String tempChoice,
			RedirectAttributes redirectAttributes) throws UnauthorizedException {
		
		if(auth.isLoggedIn() == false) {
			return "redirect:/login";
		}
		
		session.setAttribute("tempChoice", tempChoice);
		redirectAttributes.addAttribute("parkCode", session.getAttribute("parkCode"));
		
		return "redirect:/details";
	}
	
	
	
		
}
