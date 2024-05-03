package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class Encounter{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("$ref")
	private String ref;

	@JsonbProperty("type")
	private String type;

	@JsonbProperty("items")
	private Items items;

	@JsonbProperty("additionalProperties")
	private boolean additionalProperties;

	@JsonbProperty("properties")
	private Properties properties;

	@JsonbProperty("required")
	private List<String> required;

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

	public boolean isAdditionalProperties(){
		return additionalProperties;
	}

	public Properties getProperties(){
		return properties;
	}

	public List<String> getRequired(){
		return required;
	}
}