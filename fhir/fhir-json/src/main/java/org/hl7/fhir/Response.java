public class Response{

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

	@JsonbProperty("$ref")
	private String ref;

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

	public String getRef(){
		return ref;
	}
}
