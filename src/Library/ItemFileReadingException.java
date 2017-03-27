package Library;

public class ItemFileReadingException extends Exception{
private String message;
	
	public ItemFileReadingException(String message){
		this.message = message;
	}
	public ItemFileReadingException(){
		this.message = "Problem reading items from file!";
	}
	
	@Override
	public String getMessage(){
		return message;
	}
}
