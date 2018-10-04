/*Purpose: 
 *The purpose of this program is to make a phone directory that includes a person's first name,
 *last name and phone number. The directory should be alphabetical order by last name, if they have
 *the same last name, then first name, and then the phone number. No two records can be exactly 
 *the same. 
 * 
 *Solution: 
 *For my solution, I used a mix of if statements, for loops, nested for loops, while loops
 *and the Scanner class to input the information to put in the directory. I used a Linked List to 
 *create the phone directory with phoneEntry objects consisting of names and phone numbers.
 *
 *Data Structures: 
 *A LinkedList data structure was used for this program. The LinkedList acted as the phone directory 
 *and consisted of phoneEntry objects which are objects with a first name, last name and phone 
 *number.
 *
 *Using the Program:
 *As soon as the user runs the program, a menu will come up asking for a command to execute. From then 
 *on, the user would simply follow the instructions of the program to add a phone entry, delete an entry,
 *change the name or number, set another entry as the current record, show all the records in the
 *directory or quit.
 *
 *Purpose of each class:
 *I have created two classes for the program, a phonedir class and a phoneEntry class. The phoneEntry class
 *includes all the getters and setters of the program, the constructor for phoneEntry and the toString
 *method that formats the phone entry. The phonedir class consists of all other methods and the main
 *method.
 * 
 * 
 * 
 * 
 */

import java.util.Scanner;
import java.util.LinkedList;

public class phonedir {
	
	//made all these static so I can call it from other methods
	static LinkedList<phoneEntry> list = new LinkedList<phoneEntry>();
	static int currentRecord = -1; 
	static Scanner scan = new Scanner(System.in);

//Pre-condition:
//Post condition:
		
	public static void main (String args[]) {
			
		
		char reply = menu();
		
		while(reply != 'q') {
		if(reply == 'n'){
			addRecord();
			getCurrentRecord();
		}if(reply == 'a') {
			System.out.println("First Name\t\tLast Name\t\tPhone Number");
			System.out.println("----------\t\t----------\t\t------------\n");
			showRecords();
			getCurrentRecord();
		}if(reply == 'd') {
			if(currentRecord != -1) {
				System.out.println("Deleted: " + list.get(currentRecord)); 
			}
			deleteRecord();
			currentRecord = -1;
			getCurrentRecord();
		}if(reply == 'f') {
			changeFirstName();
			getCurrentRecord();
		}if(reply == 'l') {
			changeLastName();
			getCurrentRecord();
		}if(reply == 'p') {
			changePhoneNumber();
			getCurrentRecord();
		}if(reply == 's') {
			setCurrentRecord();
			getCurrentRecord();
		}
		reply = menu();
		}
		if(reply == 'q') {
			System.out.println("You have successfully terminated the program");
		}
	}
	
/*Pre-condition: the menu method needs the scanner class to be imported because this asks
 * the user to input a command to execute
 *Post condition: a command the user has chosen will be executed
 */
	public static char menu(){
		System.out.println();
		System.out.println("a  Show all records");
		System.out.println("d  Delete the current record");
		System.out.println("f  Change the first name in the current record");
		System.out.println("l  Change the last name in the current record");
		System.out.println("n  Add a new record");
		System.out.println("p  Change the phone number in the current record");
		System.out.println("q  Quit");
		System.out.println("s  Select a record from the record list to become the current record");
		System.out.println("\nEnter a command from the list above (q to quit): ");
		char reply = scan.next().charAt(0);
		
		return reply;
	}

/*Pre-condition: this methods needs a linkedlist named list to exist so it can print it out
 *Post-condition: prints out the objects in the linked list
 */
	public static void showRecords() {
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

/*Pre-condition: needs the currentRecord to be in the linkedlist so it can delete it (also a 
 * linked list needs to exist)
 *Post-condition: will delete the phone entry in the current record and there will no longer
 *be an entry at the current record
 */	
	public static void deleteRecord() {
		if(currentRecord == -1) {
			System.out.println("No current record\n");
		}else {			
			list.remove(currentRecord);
		}
	}
	
/*Pre-condition: needs a linked list as well as a current record so it knows in which index to 
 * change the first name
 * Post-condition: will change the first name in the current record and then insert it at the
 * right place in alphabetical order 
 */
	
	public static void changeFirstName() {
		if(currentRecord == -1) {
			System.out.println("No current record\n");
		}else {
		
			phoneEntry record = list.get(currentRecord);
			record.getFirstName();
			System.out.println("What do you want to change the first name to?");
			String newFirstName = scan.next();
			record.setFirstName(newFirstName);
			deleteRecord();
		
			insertSorted(record);
		}
	}
	
/*Pre-condition: needs a linked list as well as a current record so it knows in which index to 
 * change the last name
 * Post-condition: will change the last name in the current record and then insert it at the
 * right place in alphabetical order 
 */	
	public static void changeLastName() {
		if(currentRecord == -1) {
			System.out.println("No current record\n");
		}else {
		
			phoneEntry record = list.get(currentRecord);
			record.getLastName();
			System.out.println("What do you want to change the last name to?");
			String newLastName = scan.next();
			record.setLastName(newLastName); 
			deleteRecord();
		
			insertSorted(record);
		}
	}
	
/*Pre-condition: needs a linked list as well as a current record so it knows in which index to 
 * change the phone number
 * Post-condition: will change the phone number in the current record and then insert it at the
 * right place in alphabetical order 
 */	
	public static void changePhoneNumber() {
		if(currentRecord == -1) {
			System.out.println("No current record\n");
		}else {		
			phoneEntry record = list.get(currentRecord);
			record.getPhoneNumber();
			System.out.println("What do you want to change the phone number to?");
			String newPhoneNumber = scan.next();
			newPhoneNumber = formatPhoneNum(newPhoneNumber);
			
			record.setPhoneNumber(newPhoneNumber); 
			deleteRecord();
			insertSorted(record);
		}
	}

/*Pre-condition: needs a phone number as a parameter so it can format it
 * Post-condition: will format a phone number into the xxx-xxx-xxxx form
 */
	
	public static String formatPhoneNum(String phone) {
		String newNumber;
		if(phone.length() == 12) {
			return phone;
		}else if (phone.length() == 10) {
			return(phone.substring(0, 3) + "-" + phone.substring(3,6) + "-" + phone.substring(6, 10));
		}else {
			System.out.println("Enter a valid phone number: ");
			String validNum = scan.next();
			newNumber = formatPhoneNum(validNum);
		}
		return newNumber;		
	}
	
/*Pre-condition: needs a linked list to exist so it has somewhere to add the phone entry to and
 * it needs a Scanner class so the user can input what phone entry they want to add
 * Post-condition: will add a phone entry into the linked list sorted in alphabetical order by last
 * name, then first name, then phone number. It is assumed no two phone entries will be exactly 
 * identical
 * 
 */
	
	public static void addRecord(){
		
		System.out.println("Enter the first name: ");
		String firstName = scan.next();
		System.out.println("Enter the last name: ");
		String lastName = scan.next();
		System.out.println("Enter the phone number: ");
		String phoneNumber = scan.next();
		phoneNumber = formatPhoneNum(phoneNumber); 
		
		
		phoneEntry addEntry = new phoneEntry(firstName, lastName, phoneNumber);

		int addIndex = 0;
		
		if(list.size() == 0) {
			list.add(addEntry);
			currentRecord = 0;
		}else {
			
			for(int i = 0; i < list.size(); i++) {
				phoneEntry compareEntry1 = list.get(i);
				String tempLast = compareEntry1.getLastName();
				String tempFirst = compareEntry1.getFirstName();
				String tempNum = compareEntry1.getPhoneNumber();
				if((tempLast.compareTo(lastName) < 0))  {
					addIndex = i+1;
				}else if(tempLast.compareTo(lastName) == 0) {
					if(tempFirst.compareTo(firstName) < 0) {
						addIndex = i+1;
					}else if(tempNum.compareTo(firstName) == 0) {
						if(tempNum.compareTo(phoneNumber) < 0) {
							addIndex = i+1;
						}
					}					
				}				
			}
			currentRecord = addIndex;
			list.add(addIndex, addEntry);
		}		
		
	}
	
/*pre-condition: this method needs a phoneEntry as a parameter so it can insert it at a correct
 * place in the phone directory
 * post-condition: this method is used when the user decides to change first name, last name or phone
 * number in an already existing phone entry. This method takes the edited phone entry and places it 
 * at its' correct position 
 */	
	public static void insertSorted(phoneEntry record) {
		
		int addIndex = 0;
		String lastName = record.getLastName();
		String firstName = record.getFirstName();
		String phoneNumber = record.getPhoneNumber();
		
		
		for(int i = 0; i < list.size(); i++) {
			phoneEntry compareEntry1 = list.get(i);
			String tempLast = compareEntry1.getLastName();
			String tempFirst = compareEntry1.getFirstName();
			String tempNum = compareEntry1.getPhoneNumber();
			if((tempLast.compareTo(lastName) < 0))  {
				addIndex = i+1;
			}else if(tempLast.compareTo(lastName) == 0) {
				if(tempFirst.compareTo(firstName) < 0) {
					addIndex = i+1;					//currentRecord = i+1;
				}else if(tempNum.compareTo(firstName) == 0) {
					if(tempNum.compareTo(phoneNumber) < 0) {
						addIndex = i+1;
					}
				}
				
			}
			
		}
		currentRecord = addIndex;
		list.add(addIndex, record);
	}
	
/*pre-condition: this method needs the int currentRecord to be available so it can get the
 * object from linked list at that index
 * post-condition: the method will print the phone entry at the index currentRecord
 */
	
	public static void getCurrentRecord() {
		if(currentRecord == -1) {
			System.out.println("\nNO CURRENT RECORD");
		}else {
			System.out.println("\nThe current record is "); 
			System.out.println(list.get(currentRecord));
		}
	}
	
/*Pre-condition: this method needs a scanner class, and the linked list
 *post-condition: it searches for an entry identical to the user input and sets that entry
 *as the current record if it matches or tells the user that there wasn't a match
 */
	
	public static void setCurrentRecord() {
		
		System.out.println("Choose your new current record: ");
		System.out.println("Enter first name: ");
		String fname = scan.next();
		System.out.println("Enter last name: ");
		String lname = scan.next();
		System.out.println("Enter phone number: ");
		String pnum = scan.next();
		pnum = formatPhoneNum(pnum);
		
		
		for(int i = 0; i < list.size(); i++) {
			phoneEntry searchEntry = list.get(i);
			String tempLast = searchEntry.getLastName();
			String tempFirst = searchEntry.getFirstName();
			String tempNum = searchEntry.getPhoneNumber();
			if(tempLast.compareTo(lname) == 0 && tempFirst.compareTo(fname) == 0 
					&& tempNum.compareTo(pnum) == 0){
				currentRecord = i;
				break;
			}
			else {
				currentRecord = -1;
			}
		}
		if(currentRecord != -1) {
			System.out.println("Matching record found");
		}else {
			System.out.println("No matching record found");
		}
	}
		
}


