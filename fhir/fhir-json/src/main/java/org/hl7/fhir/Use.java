package org.hl7.fhir;

import java.util.List;
import jakarta.json.bind.annotation.JsonbProperty;

public class Use{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("enum")
	private List<String> jsonMemberEnum;

	@JsonbProperty("$ref")
	private String ref;

	@JsonbProperty("type")
	private String type;

	@JsonbProperty("items")
	private Items items;

	public String getDescription(){
		return description;
	}

	public List<String> getJsonMemberEnum(){
		return jsonMemberEnum;
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