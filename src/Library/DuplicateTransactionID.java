package Library;

public class DuplicateTransactionID extends Exception {
	private String message;
	
	public DuplicateTransactionID(String message){
		this.message = message;
	}
	public DuplicateTransactionID() {
		this.message = "Duplicate transaction ID!";
	}
	
	@Override
	public String getMessage(){
		return message;
	}
}
