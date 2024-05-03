public class PatternUuid{

	@JsonbProperty("pattern")
	private String pattern;

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("type")
	private String type;

	@JsonbProperty("$ref")
	private String ref;

	public String getPattern(){
		return pattern;
	}

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}

	public String getRef(){
		return ref;
	}
}
