///*	All Code Written by Cory Crowley
// * Student # 10180322
// * Queen's Net ID: 14cc78
// */
//
////TODO Create toString confirmations of object constructions
//package Library;
////Tester Class
//public class Tester {
//
//	//TODO Fix equals() method
//	
//	public static void main(String[] args) {
//		
//	//------------------------------Test Cases---------------------------------	
//	double a = 5.0;
//		
//	//Creating Items
//	//Create a new Library system
//	printSeparator(48);//Visual Divider
//	LibrarySystem myLib = new LibrarySystem();
//	printSeparator(48);//Visual Divider
//	//Create a book item
//	Book Elon = new Book("Elon Musk Biography", "Ashlee Vance", "Publish INC.", 2015);
//	
//	//Create a magazine item
//	Magazine nyt = new Magazine("New York Times", "Arthur Ochs Sulzberger Jr.", "New York Times Company", 2017);
//	
//	//Create a textbook item
//	Textbook elec270 = new Textbook("Discrete Math and It's Applications", "Kenneth H. Rosen", "The McGraw-Hill Companies", 2012);
//	
//	//Create an adaptor item -- Name: Iphone Dongle, Rental Cost $20
//	Adaptor a1 = new Adaptor("Iphone Dongle", 20);
//	
//	//Create laptop item -- Name: Dell, Rental Cost $30
//	Laptop Dell_1 = new Laptop("Dell", 30); 
//	
//	printSeparator(48);//Visual Divider
//	//Creating rentals
//	//Create a rental for the Item
//	Rental BookRent = new Rental(1, 10, Elon);
//	Rental LaptopRent = new Rental(2, 5, Dell_1);
//	Rental MagazineRent = new Rental(3,30,nyt);
//	Rental AdaptorRent = new Rental(4,15,a1);
//	Rental TexbookRent = new Rental(5,180,elec270);
//	
//	printSeparator(48);//Visual Divider			
//	//Add the rentals as transactions to the library system
//	myLib.AddTransaction(BookRent);
//	myLib.AddTransaction(LaptopRent);
//	myLib.AddTransaction(MagazineRent);
//	myLib.AddTransaction(AdaptorRent);
//	myLib.AddTransaction(TexbookRent);
//	
//	
//	//Finances before late days added
//	System.out.println("\n--Finance Statement Before Late Returns--");
//	//Print total late fees
//	System.out.println("\tTotal Late Fees: "+ myLib.getTotalLateFees());
//	//Print total rental cost
//	System.out.println("\tTotal Rental Cost: "+ myLib.getTotalRentalCosts() + "\n" );
//	
//	//Laptop, Magazine, and Textbook were returned late 
//	LaptopRent.setDaysLate(50);
//	MagazineRent.setDaysLate(5);
//	TexbookRent.setDaysLate(180);
//	
//	//Finances after late days added
//	System.out.println("\n--Finance Statement Before Late Returns--");
//	//Print total late fees
//	System.out.println("\tTotal Late Fees: "+ myLib.getTotalLateFees());
//	//Print total rental cost
//	System.out.println("\tTotal Rental Cost: "+ myLib.getTotalRentalCosts() );
//	
//	
//	}
//	
//	public static void printSeparator(int length){
//		String result = "";
//		for(int i = 0; i < length; i ++){
//			result += '-';
//		}
//		System.out.println(result);
//	}
//
//}
