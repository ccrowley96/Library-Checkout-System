package Library;

public class Laptop extends Device {

	public Laptop(LibrarySystem LS, String name, double rentalCost) throws WrongRentalCost, DuplicateItemID {
		super(LS,name, rentalCost);
		//LS.addDeviceItem("Laptop", super.getID(), name, rentalCost);
		//System.out.println(name + " Created: "+this.toString());
		
	}
	//ID constructor
	public Laptop(LibrarySystem LS, String name, double rentalCost, int id) throws DuplicateItemID, WrongRentalCost {
		super(LS, name, rentalCost, id);
		//LS.addDeviceItem("Laptop", super.getID(), name, rentalCost);
		//System.out.println(name + " Created: "+this.toString());
		
	}
	//Copy Constructor
	Laptop(LibrarySystem LS, Laptop l) throws WrongRentalCost, DuplicateItemID{
		super(LS,l.getName(),l.getDeviceRentalFees());
		System.out.println("Called Copy Constructor");
		
	}
	//Override Methods
	@Override
	public double getLateFees(int lateDays) {
		if(lateDays > 0)
		return (5.00 * lateDays) + (0.20 * this.getDeviceRentalFees());
		else
			return 0.0;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return this.clone();
	}
	@Override
	public String toString() {
		return "ID: "+this.getID()+", Name: "+this.getName() + " Rental Cost: "+this.getDeviceRentalFees();
	}

}
