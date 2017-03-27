package Library;

public class Textbook extends Book {

	public Textbook(LibrarySystem LS, String name, String author, String publisher, int year) throws DuplicateItemID {
		super(LS,name, author, publisher, year);
		//LS.addBookItem("Textbook", super.getID(), name, author, publisher, year);
		
		//System.out.println(name + " Created: "+this.toString());
	}
	//ID constructor
	public Textbook(LibrarySystem LS, String name, String author, String publisher, int year,int id) throws DuplicateItemID {
		super(LS,name, author, publisher, year, id);
		//LS.addBookItem("Textbook", super.getID(), name, author, publisher, year);
		//System.out.println(name + " Created: "+this.toString());
	}
	//Copy Constructor
	Textbook(LibrarySystem LS,Textbook t) throws DuplicateItemID{
		super(LS,t.getName(),t.getAuthor(),t.getPublisher(),t.getYear());
		//LS.addBookItem("Textbook", super.getID(), t.getName(), t.getAuthor(), t.getPublisher(), t.getYear());
		System.out.println("Called Copy Constructor");
		
	}
	//Override Methods	
	@Override
	public double getLateFees(int lateDays) {
		if(lateDays > 0)
		return 1.00 * lateDays;
		else 
			return 0;
		
	}
	protected Object clone(LibrarySystem LS) throws CloneNotSupportedException, DuplicateItemID {
		return new Book(LS,this);
	}
	@Override
	public String toString() {
		return "ID: "+this.getID()+", Name: "+this.getName() + ", Author: "+this.getAuthor()+", Publisher: "+this.getPublisher()+", Year: "+this.getYear();
	}

}
