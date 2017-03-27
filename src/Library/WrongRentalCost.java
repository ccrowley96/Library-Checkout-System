package Library;

public class WrongRentalCost extends Exception {
	private String message;
	
	public WrongRentalCost(String message){
		this.message = message;
	}
	public WrongRentalCost(){
		this.message = "Wrong Rental Cost!";
	}
	
	@Override
	public String getMessage(){
		return message;
	}
}
