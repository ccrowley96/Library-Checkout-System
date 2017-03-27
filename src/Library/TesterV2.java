/*	All Code Written by Cory Crowley
 *  Student # 10180322
 *  Queen's Net ID: 14cc78
*/
package Library;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.joda.time.DateTime;

public class TesterV2 {

	public static void main(String[] args) {
		//Create Library System LS
		LibrarySystem LS = new LibrarySystem();
		printSeparator(48);
		//Read customers in from file
		try {
			LS.readCustomers("customers.txt");
		} catch (FileNotFoundException | DuplicateItemID | DuplicateCustomerID | ItemFileReadingException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		//Read items in from file
		try {
			LS.addItem("items.txt");
		} catch (NumberFormatException | FileNotFoundException | DuplicateItemID | WrongRentalCost
				| ItemFileReadingException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}	
		//Read transactions in from file
		try {
			LS.readRentals("trans.txt");
		} catch (FileNotFoundException | DuplicateCustomerID | DuplicateItemID | DuplicateTransactionID e2) {
			System.out.println(e2.getMessage());
			//e2.printStackTrace();
		}
		
		//Create a few more items
		//Create a book item
		Book Elon = null;
		Magazine nyt = null;
		Textbook elec270 = null;
		try {
			Elon = new Book(LS,"Elon Musk Biography", "Ashlee Vance", "Publish INC.", 2015);
			//Create a magazine item
			nyt = new Magazine(LS,"New York Times", "Arthur Ochs Sulzberger Jr.", "New York Times Company", 2017);
			//Create a textbook item
			elec270 = new Textbook(LS,"Discrete Math and It's Applications", "Kenneth H. Rosen", "The McGraw-Hill Companies", 2012);
		} catch (DuplicateItemID e1) {
			e1.getMessage();
			
		}
		
		
		
		
		//Create an adaptor item -- Name: Iphone Dongle, Rental Cost $20 
		//Create laptop item -- Name: Dell, Rental Cost $30
		//Creating device must handle wrong rental cost exception
		try {
			
			Adaptor a1 = new Adaptor(LS,"Iphone Dongle", 20);
			Laptop Dell_1 = new Laptop(LS,"Dell", 30); 
			printSeparator(48);
			System.out.println("[PRINTING ITEM SET]");
			for(int key: LS.getItemSet().keySet()){
				System.out.println(LS.getItemSet().get(key).toString());
			}
			//Creating Device transaction with old date
			Rental LaptopRent = new Rental(LS,78,Dell_1.getID(),10,new DateTime(1985,2,1,0,0,0,0));
			LS.AddTransaction(LaptopRent);
			
			Rental BookRent = new Rental(LS,78,Elon.getID(),30,new DateTime(1980,1,1,0,0,0,0));
			LS.AddTransaction(BookRent);
			
			Rental MagazineRent = new Rental(LS,78,nyt.getID(),20,new DateTime(1990,3,1,0,0,0,0));
			LS.AddTransaction(MagazineRent);
			
			
			
			//Printing transaction set
			printSeparator(48);
			System.out.println("[PRINTING TRANSACTION SET]");
			for(int key: LS.getTransactionSet().keySet()){
				System.out.println(LS.getTransactionSet().get(key).toString());
			}
			printSeparator(48);
			
			//Returning items
			LaptopRent.itemReturned(new DateTime());
			LaptopRent.getLateFee(LS,true);
			LaptopRent.getTotalToBePayed(LS,true);
			printSeparator(48);
			BookRent.itemReturned(new DateTime());
			BookRent.getLateFee(LS,true);
			BookRent.getTotalToBePayed(LS,true);
			printSeparator(48);
			MagazineRent.itemReturned(new DateTime());
			MagazineRent.getLateFee(LS,true);
			MagazineRent.getTotalToBePayed(LS,true);
			printSeparator(48);
			
		} catch (DuplicateCustomerID | DuplicateItemID | WrongRentalCost | DateReturnedBeforeDateRented e) {
				System.out.println(e.getMessage());
		}
		
		System.out.println("LIST OF LATE TRANSACTIONS");
		for(int key: LS.getTransactionSet().keySet()){
			if(LS.getTransactionSet().get(key).isLate())
				System.out.println(LS.getTransactionSet().get(key).toString());
		}
		
		printSeparator(48);
		System.out.println("SEARCHING FOR OLD TRANSACTION: Transaction ID (1)");
		System.out.println(LS.searchTransByTransID(1));
		
		printSeparator(48);
		try {
			LS.writeItems("itemOutput.txt");
			LS.writeTransactions("transactionOutput.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		printSeparator(48);
		
	}
	
	
	public static void printSeparator(int length){
		String result = "";
		for(int i = 0; i < length; i ++){
			result += '-';
		}
		System.out.println(result);
	}
	
}
