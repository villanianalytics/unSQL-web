package com.github.villanianalytics.unsqlweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.villanianalytics.unsql.UnSql;
import com.github.villanianalytics.unsql.UnSql.EXPORT_FORMAT;
import com.github.villanianalytics.unsql.exception.UnSqlException;
import com.github.villanianalytics.unsqlweb.model.UnSqlModel;

@Controller
public class UnSqlController {

	private static final String JSON_OUT = "json";
	private static final String TABLE_OUT = "table";
	private static final String TEXT_WITH_HEADERS_OUT = "texth";

	private static final String delimiter = "#";

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("unsqlModel", new UnSqlModel());
		return "index";
	}

	@PostMapping("/")
	public String runQuery(@ModelAttribute UnSqlModel unsqlModel, BindingResult bindingResult, Model model) {
		String error = null;
		try {
			executeQuery(model, unsqlModel.getOutputType(), unsqlModel.getInputText(), unsqlModel.getSqlQuery());
		} catch (Exception e) {
			error = e.getMessage();
		}

		model.addAttribute("sqlQuery", unsqlModel.getSqlQuery());
		model.addAttribute("unsqlModel", unsqlModel);
		model.addAttribute("error", error);
		model.addAttribute("success", error == null);

		return "index";
	}

	private void executeQuery(Model model, String outputType, String raw, String query) throws UnSqlException {
		String result = "";
		if (JSON_OUT.equalsIgnoreCase(outputType)) {
			result = new UnSql(raw)
					.withExportFormat(EXPORT_FORMAT.JSON)
					.execute(query);
			
			model.addAttribute("resultJson", result);
		} else if (TABLE_OUT.equalsIgnoreCase(outputType)) {
			result = new UnSql(raw)
					.withExportFormat(EXPORT_FORMAT.VALUES)
					.withHeaders(true)
					.withRowDelimiter(delimiter)
					.execute(query);
			
			String[] rows = result.split("\n");
			if (rows.length > 0) {
				List<String[]> rowsFormatted = new ArrayList<>();
				for (int i = 1; i < rows.length; i++) {
					rowsFormatted.add(rows[i].split(delimiter));
				}
				
				model.addAttribute("headers", rows[0].split(delimiter));
				model.addAttribute("rows", rowsFormatted);
			}
		} else if (TEXT_WITH_HEADERS_OUT.equalsIgnoreCase(outputType)) {
			result = new UnSql(raw)
					.withExportFormat(EXPORT_FORMAT.VALUES)
					.withHeaders(true)
					.withRowDelimiter(delimiter)
					.execute(query);
			
			model.addAttribute("resultText", result);
		} else {
			result = new UnSql(raw)
					.withExportFormat(EXPORT_FORMAT.VALUES)
					.withHeaders(false)
					.withRowDelimiter(delimiter)
					.execute(query);
			
			model.addAttribute("resultText", result);
		}
	}
}
