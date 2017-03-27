package Library;

public class Customer {
	public enum type {student,employee};
	
	private String name;
	private String department;
	private int ID;
	private type t;
	
	public Customer(LibrarySystem LS, int id, String name, String department, type t)throws DuplicateCustomerID{
		//Check for duplicate customer ID
		if(LS.getCustomerSet().containsKey(id))
			throw new DuplicateCustomerID();
		
		this.name = name;
		this.department = department;
		//TODO check for duplicate customer IDs throw exception if so
		this.ID = id;
		this.t = t; //Set enum type to student/employee
	}
	
	public type getType(){
		return this.t;
	}
	
}
