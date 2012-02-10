package com.fm.template.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fm.template.exception.CountryNotFoundException;
import com.fm.template.manager.CountryManager;
import com.fm.template.model.Country;
import com.fm.template.util.ResponseCode;
import com.fm.template.util.ResponseWrapper;
import com.google.gson.Gson;

@Controller
public class CountryController {
	private CountryManager countryManager;

	public void setCountryManager(CountryManager countryManager) {
		this.countryManager = countryManager;
	}
	
	@RequestMapping(value = "/countries.html", method=RequestMethod.GET)
	public @ResponseBody String countries(HttpServletRequest request, HttpServletResponse response) {
		Gson gson = new Gson();
		ResponseWrapper responseWrapper = new ResponseWrapper();
		List<Country> countries = countryManager.getCountries();
		responseWrapper.setResponseCode(ResponseCode.OK);
		responseWrapper.setResponseObject(countries);
		response.setStatus(HttpServletResponse.SC_OK);
		return gson.toJson(responseWrapper);
	}
	
	@RequestMapping(value = "/cities.html", method=RequestMethod.GET)
	public @ResponseBody String cities(Integer id, HttpServletRequest request, HttpServletResponse response) {
		Gson gson = new Gson();
		ResponseWrapper responseWrapper = new ResponseWrapper();
		
		Country country;
		try {
			country = countryManager.getCountryById(id);
			responseWrapper.setResponseCode(ResponseCode.OK);
			responseWrapper.setResponseObject(country);
			response.setStatus(HttpServletResponse.SC_OK);
			
		} catch (CountryNotFoundException e) {
			responseWrapper.setResponseCode(ResponseCode.NOT_FOUND);
			responseWrapper.setResponseObject(null);
			responseWrapper.setResponseText("Country with id = " + id + " not found.");
			response.setStatus(HttpServletResponse.SC_OK);
			
		} catch(Exception e) {
			responseWrapper.setResponseCode(ResponseCode.GENERIC_ERROR);
			responseWrapper.setResponseObject(null);
			responseWrapper.setResponseText("Generic server error");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return gson.toJson(responseWrapper);
	}

}
