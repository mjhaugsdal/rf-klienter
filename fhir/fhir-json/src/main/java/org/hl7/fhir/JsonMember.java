package org.hl7.fhir;

import java.util.List;
import jakarta.json.bind.annotation.JsonbProperty;

public class JsonMember{

	@JsonbProperty("oneOf")
	private List<OneOfItem> oneOf;

	@JsonbProperty("$schema")
	private String schema;

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("id")
	private String id;

	@JsonbProperty("definitions")
	private Definitions definitions;

	@JsonbProperty("discriminator")
	private Discriminator discriminator;

	public List<OneOfItem> getOneOf(){
		return oneOf;
	}

	public String getSchema(){
		return schema;
	}

	public String getDescription(){
		return description;
	}

	public String getId(){
		return id;
	}

	public Definitions getDefinitions(){
		return definitions;
	}

	public Discriminator getDiscriminator(){
		return discriminator;
	}
}