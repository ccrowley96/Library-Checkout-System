package Library;

public class Book extends Item{
	
private String author;
private String publisher;
private int year;

	public Book(LibrarySystem LS, String name, String author, String publisher, int year) throws DuplicateItemID {
		super(name);
		
		this.author = author;
		this.publisher = publisher;
		this.year = year;
		System.out.println(this.getClass().getSimpleName() + " Created: "+this.toString());
		//LS.addBookItem("Book", super.getID(), name, author, publisher, year);
		LS.addBookItem(super.getID(), this);
	}
	//ID constructor for file reading
	public Book(LibrarySystem LS, String name, String author, String publisher, int year,int id) throws DuplicateItemID {
		super(LS, name,id);
		//LS.addBookItem("Book", super.getID(), name, author, publisher, year);
		this.author = author;
		this.publisher = publisher;
		this.year = year;
		System.out.println(this.getClass().getSimpleName() + " Created: "+this.toString());
		LS.addBookItem(id, this);
	}

	//Copy Constructor
	Book(LibrarySystem LS, Book b) throws DuplicateItemID{
		super(b.getName());
		this.author = b.getAuthor();
		this.publisher = b.getPublisher();
		this.year = b.getYear();
		System.out.println("Called Copy Constructor");
		LS.addBookItem(super.getID(), this);
		
	}
	//Override Methods
	@Override
	public double getLateFees(int lateDays) {
		if(lateDays > 0)
		return .5 * lateDays;
		else
			return 0;
		
	}

	@Override
	public double getDeviceRentalFees() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	protected Object clone(LibrarySystem LS) throws CloneNotSupportedException, DuplicateItemID {
		return new Book(LS,this);
	}
	@Override
	public String toString() {
		return "ID: "+this.getID()+", Name: "+this.getName() + ", Author: "+this.getAuthor()+", Publisher: "+this.getPublisher()+", Year: "+this.getYear();
	}
	
	//Getters and Setters
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

}
