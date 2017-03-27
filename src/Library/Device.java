package Library;

public class Device extends Item {
private double rentalCost;

	public Device(LibrarySystem LS, String name, double rentalCost) throws WrongRentalCost, DuplicateItemID{
		super(name);
		if(rentalCost < 0)
			throw new WrongRentalCost();
		this.rentalCost = rentalCost;
		System.out.println(this.getClass().getSimpleName() + " Created: "+this.toString());
		LS.addDeviceItem(super.getID(), this);
	}
	//ID constructor for file reading
	public Device(LibrarySystem LS, String name, double rentalCost,int id) throws DuplicateItemID, WrongRentalCost {
		super(LS, name,id);
		this.rentalCost = rentalCost;
		System.out.println(this.getClass().getSimpleName() + " Created: "+this.toString());
		LS.addDeviceItem(id, this);
	}
	//Copy Constructor
	Device(LibrarySystem LS, Device d) throws DuplicateItemID, WrongRentalCost{
		super(d.getName());
		this.rentalCost = d.getDeviceRentalFees();
		LS.addDeviceItem(super.getID(), this);
	}

	//Override Methods
	@Override
	public double getLateFees(int lateDays) {
		if(lateDays > 0)
		return (2.00 * lateDays) + (0.1 * rentalCost);
		else 
			return 0;
	}
	
	@Override
	public double getDeviceRentalFees() {
		// TODO Auto-generated method stub
		return this.rentalCost;
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
