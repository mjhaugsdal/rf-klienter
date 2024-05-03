public class Signature{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("additionalProperties")
	private boolean additionalProperties;

	@JsonbProperty("type")
	private String type;

	@JsonbProperty("properties")
	private Properties properties;

	@JsonbProperty("$ref")
	private String ref;

	@JsonbProperty("items")
	private Items items;

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

	public String getRef(){
		return ref;
	}

	public Items getItems(){
		return items;
	}
}
