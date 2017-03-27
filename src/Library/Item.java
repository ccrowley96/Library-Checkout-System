package Library;

import org.joda.time.DateTime;

public abstract class Item {
	
private int ID;
private String Name;
static int Global_ID = 1;

public Item(String name){
	//auto-increment with static ID
	this.ID = ++Global_ID;
	this.Name = name;
}
//Item Constructor
public Item(LibrarySystem LS, String name, int id) throws DuplicateItemID{
	if(LS.getItemSet().containsKey(id))
		throw new DuplicateItemID();
	this.ID = id;
	this.Name = name;
}

//Copy Constructor
Item(Item i){
	this.ID = i.getID();
	this.Name = i.getName();
}

public abstract double getLateFees(int daysLate);
public abstract double getDeviceRentalFees();

//Getters and Setters
public int getID(){
	return ID;
}

public String getName(){
	return Name;
}


public void setName(String name){
	if(name != null)
	this.Name = name;
	else
		System.out.println("Name must not be an empty string!");
}

//Override Methods
@Override
public boolean equals(Object obj) {
	if (obj == null)
		return false;
	if(!(obj instanceof Item))
		return false;
	Item i = (Item) obj;
	return i.ID == ID;
}

@Override
protected Object clone() throws CloneNotSupportedException {
	return this.clone();
}
@Override
public String toString() {
	return "ID: "+ID+", Name: "+Name;
}



}
