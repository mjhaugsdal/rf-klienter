public class Base64Binary{

	@JsonbProperty("description")
	private String description;

	@JsonbProperty("type")
	private String type;

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}
}
