public class Items{

	@JsonbProperty("$ref")
	private String ref;

	@JsonbProperty("enum")
	private List<String> jsonMemberEnum;

	public String getRef(){
		return ref;
	}

	public List<String> getJsonMemberEnum(){
		return jsonMemberEnum;
	}
}
