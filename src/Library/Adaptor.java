package Library;

public class Adaptor extends Device {

	
	public Adaptor(LibrarySystem LS, String name, double rentalCost) throws WrongRentalCost, DuplicateItemID {
		super(LS,name, rentalCost);
		//LS.addDeviceItem("Adaptor", super.getID(), name, rentalCost);
		//System.out.println(name + " Created: "+this.toString());
	}
	//ID constructor
	public Adaptor(LibrarySystem LS, String name, double rentalCost, int id) throws DuplicateItemID, WrongRentalCost {
		super(LS, name, rentalCost,id);
		//LS.addDeviceItem("Adaptor", super.getID(), name, rentalCost);
		//System.out.println(name + " Created: "+this.toString());
	}
	
	//Copy Constructor
	Adaptor(LibrarySystem LS, Adaptor a) throws WrongRentalCost, DuplicateItemID{
		super(LS, a.getName(),a.getDeviceRentalFees());
		System.out.println("Called Copy Constructor");
		
	}
	//Override methods
	@Override
	public double getLateFees(int lateDays) {
		if(lateDays > 0)
		return (2.50 * lateDays) + (0.15 * this.getDeviceRentalFees());
		else
			return 0;
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
