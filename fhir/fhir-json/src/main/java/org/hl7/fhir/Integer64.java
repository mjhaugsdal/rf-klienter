package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class Integer64{

	@JsonbProperty("pattern")
	private String pattern;

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("type")
	private String type;

	public String getPattern(){
		return pattern;
	}

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}
}