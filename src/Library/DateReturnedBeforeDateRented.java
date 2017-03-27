package Library;

public class DateReturnedBeforeDateRented extends Exception {
	private String message;
	
	public DateReturnedBeforeDateRented(String message){
		this.message = message;
	}
	public DateReturnedBeforeDateRented(){
		this.message = "Date returned before date rented!";
	}
	
	@Override
	public String getMessage(){
		return message;
	}
}
