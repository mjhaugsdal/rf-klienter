package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class Discriminator{

	@JsonbProperty("mapping")
	private Mapping mapping;

	@JsonbProperty("propertyName")
	private String propertyName;

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("type")
	private String type;

	@JsonbProperty("items")
	private Items items;

	public Mapping getMapping(){
		return mapping;
	}

	public String getPropertyName(){
		return propertyName;
	}

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}

	public Items getItems(){
		return items;
	}
}