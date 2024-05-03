package org.hl7.fhir;

import java.util.List;
import jakarta.json.bind.annotation.JsonbProperty;

public class Questionnaire{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("additionalProperties")
	private boolean additionalProperties;

	@JsonbProperty("type")
	private String type;

	@JsonbProperty("properties")
	private Properties properties;

	@JsonbProperty("required")
	private List<String> required;

	@JsonbProperty("items")
	private Items items;

	@JsonbProperty("$ref")
	private String ref;

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

	public List<String> getRequired(){
		return required;
	}

	public Items getItems(){
		return items;
	}

	public String getRef(){
		return ref;
	}
}