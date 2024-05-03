public class DefinitionUri{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("type")
	private String type;

	@JsonbProperty("items")
	private Items items;

	@JsonbProperty("$ref")
	private String ref;

	@JsonbProperty("pattern")
	private String pattern;

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

	public String getPattern(){
		return pattern;
	}
}
