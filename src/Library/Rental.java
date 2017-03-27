package Library;

import java.util.Date;
import org.joda.time.*;

public class Rental { // Note ** Using JODA time library for dates and times

static int transactionGlobalID = 1;

public enum rentalStatus{active,late,closed};
//Item and Customer Reference
private Item item;
private Customer customer;
//ID's
private int customerID;
private int transactionID;
private int itemID;
private LibrarySystem LS;
//Date-Time
private int rentalDays;
private int daysLate;
private DateTime rentalDate;
private DateTime estimatedReturn;
private DateTime returnDate;

private rentalStatus status;

public Rental(LibrarySystem LS, int custID, int itemID, int rentalDays, DateTime rentalDate)throws DuplicateCustomerID, DuplicateItemID{
	
	//Get customer and item from library system -> customer/item set by key
	if(LS.getCustomerSet().containsKey(custID)){
		this.customer = LS.getCustomerSet().get(custID);
	}else{
		System.out.println("Customer ID: "+custID+" does not exist!!");
	}
	if(LS.getItemSet().containsKey(itemID)){
		this.item = LS.getItemSet().get(itemID);
	}else{
		System.out.println("Item ID: "+itemID+" does not exist!");
	}
	
	//Date and Time
	this.rentalDate = rentalDate;
	this.estimatedReturn = rentalDate; 
	this.estimatedReturn.plusDays(rentalDays);//set estimated return date based on checkout time and rental days
	
	this.LS = LS;
	this.transactionID = transactionGlobalID++;
	this.customerID = custID;
	this.rentalDays = rentalDays;
	this.itemID =  itemID;

	if(this.isLate()){//check if late
		daysLate = ((int) (new DateTime().minus(this.rentalDate.getMillis()).getMillis() / (1000*60*60*24)) - rentalDays);
		this.status = rentalStatus.late;
	}else{
		daysLate = 0;
		this.status = rentalStatus.active;
	}
}
//Read file constructor
public Rental(LibrarySystem LS, int transID, int itemID, int custID,DateTime TransactionDate,DateTime ExpectedReturnDate, DateTime ReturnDate) throws DuplicateItemID, DuplicateCustomerID, DuplicateTransactionID{
	//Check for duplicate ID exception
	if(LS.getTransactionSet().containsKey(transID))
		throw new DuplicateTransactionID();
	
	//Date and Time
	this.rentalDate = TransactionDate;
	this.estimatedReturn = ExpectedReturnDate; 
	this.rentalDays = this.estimatedReturn.getDayOfYear() - TransactionDate.getDayOfYear();
	//If return date specified
	if(ReturnDate != null){
		//check if late or not
		this.returnDate = ReturnDate;	
		if(this.isLate()){
			//daysLate = 
			daysLate = ((int) (this.returnDate.minus(this.estimatedReturn.getMillis()).getMillis() / (1000*60*60*24)));
			this.status = rentalStatus.closed;
		}else{//not late
			daysLate = 0;
			this.status = rentalStatus.active;
		}
	}else{//Return date not specified -- rental still active
		if(this.isLate()){
			daysLate = (int) (new DateTime().minus(this.estimatedReturn.getMillis())).getMillis()  / (1000*60*60*24);
			//daysLate = [DATE now] - [DATE rentalDate+rentalDays]
			this.status = rentalStatus.late;
		}else{
			daysLate = 0;
			this.status = rentalStatus.active;
			}
		
	}
	
	this.LS = LS;
	this.transactionID = transID;
	this.customerID = custID;
	this.itemID =  itemID;
	
	//Get customer and item from library system -> customer/item set by key
	this.customer = LS.getCustomerSet().get(custID);
	this.item = LS.getItemSet().get(itemID);
	if(this.customer == null){
		System.out.println("Customer ID: "+custID+", not found");
	}
	if(this.item == null){
		System.out.println("Item ID: "+itemID+", not found");
	}
}
//Copy Constructor
public Rental(Rental r){
	this.customerID = r.getCustomerID();
	this.rentalDays = r.getRentalDays();
	this.item = r.getItem();
	this.transactionID = transactionGlobalID++;
}

//Operations
public boolean isLate(){
	Instant timeStamp = new Instant();
	if(this.returnDate!=null){
		if(this.returnDate.isAfter(this.estimatedReturn))
			return true;
		else
			return false;
	}
	if(timeStamp.isAfter(this.estimatedReturn)){
		//this.status = rentalStatus.late;
		return true;
	}
	else 
		return false;
}
//TODO incorporate rental days into days late calculation
public void itemReturned(DateTime returnTime) throws DateReturnedBeforeDateRented{
	//check for return before rented exception
	if(returnTime.isBefore(this.rentalDate.getMillis())){
		throw new DateReturnedBeforeDateRented();
	}
	returnDate = returnTime;
	daysLate = (int) (returnTime.minus(this.rentalDate.getMillis()).getMillis() / (1000*60*60*24)) - this.rentalDays;
	System.out.println("Item '"+this.item.getName() +"' returned, "+daysLate+" days late");
	status = rentalStatus.closed;
}


//Getters and Setters
public DateTime getReturnDate(){
	return this.returnDate;
}
public DateTime getExpectedReturnDate(){
	return this.estimatedReturn;
}
public int getCustomerID() {
	return customerID;
}
public void setCustomerID(int customerID) {
	if(customerID > 0)
	this.customerID = customerID;
	else
		System.out.println("ID must be positive!");
}
public int getRentalDays() {
	return rentalDays;
}
public void setRentalDays(int rentalDays) {
	if(rentalDays > 0)
	this.rentalDays = rentalDays;
	else
		System.out.println("# of Rental Days must be positive!");
	
}
public void setItem(Item item) {
	if(item != null)
	this.item = item;
	else
		System.out.println("Item cannot be null!");
}
//public void setDaysLate(int daysLate) {
//	if(daysLate > 0){
//	this.daysLate = daysLate;
//	//Print late fee and days late for clarity
//	System.out.println("'"+this.getItem().getName()+"'" + " returned "+daysLate + " days late -- Fee: "+this.getItem().getLateFees(daysLate) + " :(");
//	}
//	else
//		System.out.println("Late Days must be positive!");
//}
public int getDaysLate(){
	return this.daysLate;
}
public Item getItem(){
	return this.LS.getItemSet().get(this.itemID);
}
public int getTransactionID(){
	return this.transactionID;
}
public int getItemID(){
	return this.itemID;
}

public double getLateFee(LibrarySystem LS, boolean print){
	double result = LS.getItemSet().get(this.itemID).getLateFees(this.daysLate);
	if(print)
		System.out.println(this.getItem().getName() +" late fee: $"+result);
	return result;
}

public double getRentalCost(){ // get rental cost -- Student get 25% discount before late fee
	double result;
	result = this.getItem().getDeviceRentalFees();
	if(this.customer.getType().equals(Customer.type.student))
		result -= result * .25;
	return result;
}
public DateTime getRentalDate(){
	return this.rentalDate;
}

public void setDaysLate(int daysLate) {
	this.daysLate = daysLate;
}
public void setRentalDate(DateTime rentalDate) {
	this.rentalDate = rentalDate;
}
public void setReturnDate(DateTime returnDate) {
	this.returnDate = returnDate;
}
public double getTotalToBePayed(LibrarySystem LS,boolean print){
	double result;
	result = this.getRentalCost() + this.getLateFee(LS,false);
	if(print)
		System.out.println(this.item.getName() + " total fee: $"+result);
	return result;
}
//Override Methods
@Override
public boolean equals(Object obj) {
	if (obj == null)
		return false;
	if(!(obj instanceof Rental))
		return false;
	Rental i = (Rental) obj;
	return i.transactionID == this.transactionID;
}
@Override
protected Object clone() throws CloneNotSupportedException {
	return this.clone();
}
@Override
public String toString() {
	String returnD;
	if(this.returnDate!=null)
		returnD = this.returnDate.toString();
	else
		returnD = "Not yet returned";
	
	return "\nRental - "+this.LS.getItemSet().get(this.itemID).getName()+": \n\tCustomer ID: "+this.customerID+"\n\tTransaction ID: "+this.transactionID+"\n\tItem ID:"+this.itemID+"\n\tRental Date: "+this.rentalDate +"\n\tExpected Return Date: "+this.estimatedReturn+"\n\tReturn Date: "+returnD+"\n\tRental Days: "+this.getRentalDays() + "\n\tDays Late: "+this.daysLate+"\n\tRental Status: "+this.status.toString();
}


}
