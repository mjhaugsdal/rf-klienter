public class ResourceList{

	@JsonbProperty("oneOf")
	private List<OneOfItem> oneOf;

	public List<OneOfItem> getOneOf(){
		return oneOf;
	}
}
