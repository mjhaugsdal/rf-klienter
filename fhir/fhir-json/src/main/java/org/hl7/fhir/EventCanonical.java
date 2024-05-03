package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class EventCanonical{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("$ref")
	private String ref;

	@JsonbProperty("pattern")
	private String pattern;

	@JsonbProperty("type")
	private String type;

	public String getDescription(){
		return description;
	}

	public String getRef(){
		return ref;
	}

	public String getPattern(){
		return pattern;
	}

	public String getType(){
		return type;
	}
}