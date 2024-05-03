package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class ValueRange{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("$ref")
	private String ref;

	@JsonbProperty("type")
	private String type;

	@JsonbProperty("items")
	private Items items;

	public String getDescription(){
		return description;
	}

	public String getRef(){
		return ref;
	}

	public String getType(){
		return type;
	}

	public Items getItems(){
		return items;
	}
}