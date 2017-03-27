package Library;

public class DuplicateCustomerID extends Exception {
	private String message;
		
		public DuplicateCustomerID(String message){
			this.message = message;
		}
		public DuplicateCustomerID(){
			this.message = "Duplicate Customer ID!";
		}
		
		@Override
		public String getMessage(){
			return message;
		}
}
