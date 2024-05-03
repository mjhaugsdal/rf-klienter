public class DurationUnit{

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
