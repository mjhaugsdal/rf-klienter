package org.hl7.fhir;

import java.util.List;
import jakarta.json.bind.annotation.JsonbProperty;

public class PeriodUnit{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("enum")
	private List<String> jsonMemberEnum;

	@JsonbProperty("$ref")
	private String ref;

	public String getDescription(){
		return description;
	}

	public List<String> getJsonMemberEnum(){
		return jsonMemberEnum;
	}

	public String getRef(){
		return ref;
	}
}