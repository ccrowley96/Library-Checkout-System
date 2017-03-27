package Library;

public class Magazine extends Book  {

	public Magazine(LibrarySystem LS, String name, String author, String publisher, int year) throws DuplicateItemID {
		super(LS,name, author, publisher, year);
		//LS.addBookItem("Magazine", super.getID(), name, author, publisher, year);
		//System.out.println(name + " Created: "+this.toString());
	}
	//ID constructor
	public Magazine(LibrarySystem LS, String name, String author, String publisher, int year, int id) throws DuplicateItemID {
		super(LS,name, author, publisher, year, id);
		//LS.addBookItem("Magazine", id, name, author, publisher, year);
		//System.out.println(name + " Created: "+this.toString());
	}
	
	//Copy Constructor
	Magazine(LibrarySystem LS, Magazine m) throws DuplicateItemID{
		super(LS,m.getName(),m.getAuthor(),m.getPublisher(),m.getYear());
		//LS.addBookItem("Magazine", super.getID(), m.getName(), m.getAuthor(), m.getPublisher(), m.getYear());
		System.out.println("Called Copy Constructor");
		
	}
	//Override Methods
	@Override
	public double getLateFees(int lateDays) {
		if(lateDays > 0)
		return .75 * lateDays;
		else
			return 0;
	}
	
	protected Object clone(LibrarySystem LS) throws CloneNotSupportedException, DuplicateItemID {
		return new Magazine(LS, this);
	}
	@Override
	public String toString() {
		return "ID: "+this.getID()+", Name: "+this.getName() + ", Author: "+this.getAuthor()+", Publisher: "+this.getPublisher()+", Year: "+this.getYear();
	}

}
