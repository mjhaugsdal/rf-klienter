package org.hl7.fhir;

import java.util.List;
import jakarta.json.bind.annotation.JsonbProperty;

public class ResourceList{

	@JsonbProperty("oneOf")
	private List<OneOfItem> oneOf;

	public List<OneOfItem> getOneOf(){
		return oneOf;
	}
}