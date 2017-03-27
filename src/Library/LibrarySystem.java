package Library;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LibrarySystem {

	//Private instance variables	
	//HASH SETS -- using ID of each class as key
	private HashMap<Integer,Item> itemSet;
	private HashMap<Integer,Customer> customerSet;
	private HashMap<Integer,Rental> transactionSet;

	//Constructor
	public LibrarySystem(){
		itemSet = new HashMap<Integer, Item>();
		customerSet = new HashMap<Integer, Customer>();
		transactionSet = new HashMap<Integer, Rental>();
		System.out.println("Library System Created: "+this.toString());
	}
	//Copy Constructor
	public LibrarySystem(LibrarySystem l){
		this.itemSet = l.itemSet;
		this.customerSet = l.customerSet;
		this.transactionSet = l.transactionSet;
	}
	//Add Item class for all DEVICES
	public void addDeviceItem(int id, Device device) throws DuplicateItemID, WrongRentalCost{
		if(itemSet.containsKey(id))
			throw new DuplicateItemID();
		itemSet.put(id, device);
	}
	//Add Item class for all BOOKS
	public void addBookItem(int id, Book book) throws DuplicateItemID{
		if(itemSet.containsKey(id))
			throw new DuplicateItemID();
		itemSet.put(id, book);
	}
	//Read customers in from file
	public void readCustomers(String filename) throws FileNotFoundException, DuplicateItemID, DuplicateCustomerID, ItemFileReadingException{
		Scanner inFile = new Scanner(new FileReader(filename)); 
		StringTokenizer st; 
		int id;
		//loop through every line of file
		while(inFile.hasNextLine()){
			st = new StringTokenizer(inFile.nextLine(),",");
			//check for comment line in file
			if(st.toString().charAt(0) == '/' && st.toString().charAt(0) == '/'){
				inFile.nextLine();
				continue;
			}
			//check for incorrect number of tokens 
			if(st.countTokens() != 4)
				throw new ItemFileReadingException("Wrong number of tokens found when reading customers from file");
			
			id = Integer.parseInt(st.nextToken().trim());
			
			if(itemSet.containsKey(id))
				throw new DuplicateItemID();
			
			customerSet.put(id, new Customer(this, id, st.nextToken().trim(),st.nextToken().trim(),Customer.type.valueOf(st.nextToken().trim().toLowerCase())));
		}
	}
	//Read rental transactions in from file
	//Line Format: TransactionID,ItemID,CustomerID,TransactionDate,ExpectedReturnDate,ReturnDate
	public void readRentals(String filename) throws FileNotFoundException, DuplicateCustomerID, DuplicateItemID, DuplicateTransactionID{
		Scanner inFile = new Scanner(new FileReader(filename)); 
		StringTokenizer st; 
		
		int transID, itemID, custID;
		DateTime transDate, custDate, ExpRetDate, RetDate;
		String returnDateString;
		// Format for input
		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		// Format for output
		//	DateTimeFormatter dtfOut = DateTimeFormat.forPattern("MM/dd/yyyy");
		//loop through every line of file
		while(inFile.hasNextLine()){
			st = new StringTokenizer(inFile.nextLine(),",");
			//check for comment line in file
			if(st.toString().charAt(0) == '/' && st.toString().charAt(0) == '/'){
				inFile.nextLine();
				continue;
			}
			
			//Get ID's from line -- throw duplicate ID exceptions if ID already contained in any set
			transID = Integer.parseInt(st.nextToken().trim());
				if(transactionSet.containsKey(transID))
					throw new DuplicateTransactionID();
			itemID = Integer.parseInt(st.nextToken().trim());
			custID = Integer.parseInt(st.nextToken().trim());
			
			transDate = dtf.parseDateTime(st.nextToken().trim());
			ExpRetDate = dtf.parseDateTime(st.nextToken().trim());
			returnDateString =  st.nextToken().trim();
			//Check if return date not yet specified
			if(returnDateString.charAt(0) == '0' && returnDateString.charAt(1) == '0'){
				returnDateString = null;
				RetDate = null;
			}else{
			RetDate = dtf.parseDateTime(returnDateString);
			}
			//create and add rental to transaction set
			transactionSet.put(transID, new Rental(this,transID,itemID,custID, transDate,ExpRetDate,RetDate));
		}
	}
	
	//Add items to library system from READ FROM FILE
	public void addItem(String filename) throws FileNotFoundException, DuplicateItemID, NumberFormatException, WrongRentalCost, ItemFileReadingException{
		Scanner inFile = new Scanner(new FileReader(filename)); 
		StringTokenizer st; 
		
		String itemType;
		int itemId;
		
		//Loop through every line of file
		while(inFile.hasNextLine()){
			
			st = new StringTokenizer(inFile.nextLine(),",");
			//check for comment line in file
			if(st.toString().charAt(0) == '/' && st.toString().charAt(0) == '/'){
				inFile.nextLine();
				continue;
			}
			//Check for incorrect number of tokens in file
			if(st.countTokens() != 6 && st.countTokens() != 4){
				throw new ItemFileReadingException("Wrong number of tokens found when reading items from file");
			}
	
			itemType = st.nextToken().trim();
			itemId = Integer.parseInt(st.nextToken().trim());
			
			if(itemSet.containsKey(itemId)){
				throw new DuplicateItemID();
			}
			
			switch(itemType){
			case "Book": 
				new Book(this,st.nextToken(),st.nextToken(),st.nextToken(),Integer.parseInt(st.nextToken().trim()),itemId);
				break;
			case "Textbook": 
				new Textbook(this,st.nextToken(),st.nextToken(),st.nextToken(),Integer.parseInt(st.nextToken().trim()),itemId);
				break;
			case "Magazine": 
				new Magazine(this,st.nextToken(),st.nextToken(),st.nextToken(),Integer.parseInt(st.nextToken().trim()),itemId);
				break;
			case "Device":
				new Device(this,st.nextToken(),Double.parseDouble(st.nextToken().trim()),itemId);
				break;
			case "Laptop":
				new Laptop(this,st.nextToken(),Double.parseDouble(st.nextToken().trim()),itemId);
				break;
			case "Adaptor":
				new Adaptor(this,st.nextToken(),Double.parseDouble(st.nextToken().trim()),itemId);
				break;
			}
			
		}
	}
	
	//Write items in library system to a file
	public void writeItems(String filename) throws IOException{
			
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			String data = "";
			//Loop through all elements
			for (int key: itemSet.keySet()){
	            Item item = itemSet.get(key);	            
	            //Check what kind of item is being written to file (Device/Book)
	            //If item is a device -- write device items variables to file
	            if(item.getClass().getSimpleName().equals("Device") || item.getClass().getSimpleName().equals("Adaptor") || item.getClass().getSimpleName().equals("Laptop")){
	            	data += item.getClass().getSimpleName() + ", "+ key + ", " + item.getName() + ", " + item.getDeviceRentalFees() +"\n";
	            }
	            else{//Item must be a book type in all other cases -- write book items to file
	            	Book temp = (Book)item;//Cast item to book in order to access member variables
	            	data += item.getClass().getSimpleName() + ", " + key + ", " + item.getName() + ", " + temp.getAuthor() + ", " + temp.getPublisher() + ", " + temp.getYear() +"\n";
	            }

			} 
			System.out.print("[Item Data Written to '"+filename+"']\n"+data);
			bw.write(data);
			bw.flush();
			bw.close();
			System.out.println("\n");
	}
	
	public void writeTransactions(String filename) throws IOException{
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		String data = "";
		for (int key: transactionSet.keySet()){
			//Format: TransactionID,ItemID,CustomerID,TransactionDate,ExpectedReturnDate,ReturnDate
			Rental t = transactionSet.get(key);
			data += t.getTransactionID() + ", "+t.getItemID()+", "+t.getCustomerID() + ", "+
					t.getRentalDate()+ ", "+t.getExpectedReturnDate()+ ", "+t.getReturnDate()+"\n";
		}
		System.out.print("[Transaction Data Written to '"+filename+"']\n"+data);
		bw.write(data);
		bw.flush();
		bw.close();
	}
	//Add transaction
	public void AddTransaction(Rental toAdd){
		//Add key and rental to transaction set
		this.transactionSet.put(toAdd.getTransactionID(), toAdd);
	}
	
	public HashMap<Integer, Rental> getTransactionsByDate(DateTime a, DateTime b){
		HashMap<Integer, Rental> result = null;
		
		for(int currentKey : transactionSet.keySet()){
			if(transactionSet.get(currentKey).getRentalDate().isAfter(a.getMillis()) && transactionSet.get(currentKey).getRentalDate().isBefore(b.getMillis()));{
				System.out.println(transactionSet.get(currentKey).toString());;
				result.put(currentKey,transactionSet.get(currentKey));
			}
		}
		return result;
	}
	
	//Methods to search for transactions (transID, custID, itemID)
	public Rental searchTransByTransID(int transID){
		return transactionSet.get(transID);
	}
	public Rental searchTrasnByCustID(int custID){
		for(int currentKey : transactionSet.keySet()){
			if(transactionSet.get(currentKey).getCustomerID() == custID)
				return transactionSet.get(currentKey);
		}
	   return null;
	}
	public Rental searchTransByItemID(int itemID){
		for(int currentKey : transactionSet.keySet()){
			if(transactionSet.get(currentKey).getItemID() == itemID)
				return transactionSet.get(currentKey);
		}
	   return null;
	}
	
	//Methods to search for item by any field (ID/Name)
	public Item searchItemByItemID(int ID){
		return itemSet.get(ID);
	}
	
	public Item searchItemByName(String Name){
		for(int currentKey: itemSet.keySet()){
			if(itemSet.get(currentKey).getName().equals(Name));
				return itemSet.get(currentKey);
		}
		return null;
	}
	
	//Setters and Getters
	public double getTotalLateFees(){
		double result = 0;
		for(int currentKey: transactionSet.keySet()){
			result += transactionSet.get(currentKey).getLateFee(this,false);
		}
		return result;
	}

	public double getTotalRentalCosts(){
		double result = 0;
		for(int currentKey: transactionSet.keySet()){
			if(itemSet.get(transactionSet.get(currentKey).getItemID()) instanceof Device){
				result += transactionSet.get(currentKey).getRentalCost();
			}
		}
		return result;
	}//end rental cost

	public HashMap<Integer, Item> getItemSet() {
		return itemSet;
	}
	public HashMap<Integer, Customer> getCustomerSet() {
		return customerSet;
	}
	public HashMap<Integer, Rental> getTransactionSet() {
		return transactionSet;
	}
	
	//Override Methods
	@Override
	protected LibrarySystem clone() {
		return new LibrarySystem(this);
	}
	@Override
	public String toString() {
		return this.transactionSet.toString();
	}
}
