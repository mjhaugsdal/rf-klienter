package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class OneOfItem{

	@JsonbProperty("$ref")
	private String ref;

	public String getRef(){
		return ref;
	}
}