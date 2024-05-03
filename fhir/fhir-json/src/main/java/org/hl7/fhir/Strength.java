package org.hl7.fhir;

import jakarta.json.bind.annotation.JsonbProperty;

public class Strength{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("$ref")
	private String ref;

	@JsonbProperty("enum")
	private List<String> jsonMemberEnum;

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

	public List<String> getJsonMemberEnum(){
		return jsonMemberEnum;
	}

	public String getType(){
		return type;
	}

	public Items getItems(){
		return items;
	}
}