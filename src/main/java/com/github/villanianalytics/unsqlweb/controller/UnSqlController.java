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
import com.github.villanianalytics.unsql.exception.UnSqlException;
import com.github.villanianalytics.unsql.model.Result;

@Controller
public class UnSqlController {

	@GetMapping("/")
	public String main(Model model) {
		return "index";
	}

	@PostMapping("/")
	public ModelAndView runQuery(@RequestParam("sqlQuery") String sqlQuery, @RequestParam("inputText") String inputText,
			ModelMap modelMap) {
		UnSql unsql = new UnSql(inputText);
		List<Result> results = null;
		try {
			results = unsql.executeQuery(sqlQuery);
		} catch (UnSqlException e) {
			modelMap.put("errors",  e.getMessage());
		}

		modelMap.put("results", results);

		return new ModelAndView("index", modelMap);
	}
}
