package com.github.villanianalytics.unsqlweb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.villanianalytics.unsql.UnSql;
import com.github.villanianalytics.unsql.model.Result;

@Controller
public class UnSqlController {

	@GetMapping("/")
	public String main(Model model) {
		return "index";
	}

	@PostMapping("/")
	public ModelAndView runQuery(@RequestParam(value = "sqlQuery", required = true) String sqlQuery, @RequestParam(value ="inputText", required = true) String inputText,
			ModelMap modelMap) {
		String error = null;
		List<Result> results = null;
		
		try {
			UnSql unsql = new UnSql(inputText);	
			results = unsql.executeQuery(sqlQuery);
		} catch (Exception e) { 
			error = e.getMessage();
		} 

		modelMap.put("sqlQuery", sqlQuery);
		modelMap.put("inputText", inputText);
		modelMap.put("results", results);
		modelMap.put("error", error);
		modelMap.put("success", error == null);

		return new ModelAndView("index", modelMap);
	}
}
