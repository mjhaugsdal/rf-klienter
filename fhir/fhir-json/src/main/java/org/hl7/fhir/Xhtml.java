package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class Xhtml{

	@JsonbProperty("description")
	private String description;

	public String getDescription(){
		return description;
	}
}