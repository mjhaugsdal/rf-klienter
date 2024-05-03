package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class PackagedProductDefinitionLegalStatusOfSupply{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("additionalProperties")
	private boolean additionalProperties;

	@JsonbProperty("type")
	private String type;

	@JsonbProperty("properties")
	private Properties properties;

	public String getDescription(){
		return description;
	}

	public boolean isAdditionalProperties(){
		return additionalProperties;
	}

	public String getType(){
		return type;
	}

	public Properties getProperties(){
		return properties;
	}
}