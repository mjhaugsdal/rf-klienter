package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class CancellationDate{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("$ref")
	private String ref;

	public String getDescription(){
		return description;
	}

	public String getRef(){
		return ref;
	}
}