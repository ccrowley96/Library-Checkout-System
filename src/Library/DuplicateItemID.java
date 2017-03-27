package Library;

public class DuplicateItemID extends Exception {
	private String message;
	
	public DuplicateItemID(String message){
		this.message = message;
	}
	public DuplicateItemID(){
		this.message = "Duplicate item ID!";
	}
	
	@Override
	public String getMessage(){
		return message;
	}
}
