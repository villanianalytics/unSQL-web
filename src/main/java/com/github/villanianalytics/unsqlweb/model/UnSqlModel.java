package com.github.villanianalytics.unsqlweb.model;

import java.io.Serializable;

public class UnSqlModel implements Serializable {

	private static final long serialVersionUID = 2086164612018150646L;
	private String sqlQuery;
	private String inputText;
	private String outputType;
	
	
	public String getSqlQuery() {
		return sqlQuery;
	}
	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	public String getInputText() {
		return inputText;
	}
	public void setInputText(String inputText) {
		this.inputText = inputText;
	}
	public String getOutputType() {
		return outputType;
	}
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
}
