public class ResourceType{

	@JsonbProperty("const")
	private String jsonMemberConst;

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("$ref")
	private String ref;

	@JsonbProperty("type")
	private String type;

	@JsonbProperty("items")
	private Items items;

	public String getJsonMemberConst(){
		return jsonMemberConst;
	}

	public String getDescription(){
		return description;
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
