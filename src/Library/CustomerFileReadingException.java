package Library;

public class CustomerFileReadingException extends Exception {
	private String message;
		
		public CustomerFileReadingException(String message){
			this.message = message;
		}
		public CustomerFileReadingException(){
			this.message = "Problem reading customers from file!";
		}
		
		@Override
		public String getMessage(){
			return message;
		}
}
