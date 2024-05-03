package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class Endpoint{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("type")
	private String type;

	@JsonbProperty("items")
	private Items items;

	@JsonbProperty("$ref")
	private String ref;

	@JsonbProperty("additionalProperties")
	private boolean additionalProperties;

	@JsonbProperty("properties")
	private Properties properties;

	@JsonbProperty("required")
	private List<String> required;

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}

	public Items getItems(){
		return items;
	}

	public String getRef(){
		return ref;
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