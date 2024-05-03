package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class Citation{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("$ref")
	private String ref;

	@JsonbProperty("additionalProperties")
	private boolean additionalProperties;

	@JsonbProperty("type")
	private String type;

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

	public boolean isAdditionalProperties(){
		return additionalProperties;
	}

	public String getType(){
		return type;
	}

	public Properties getProperties(){
		return properties;
	}

	public List<String> getRequired(){
		return required;
	}
}