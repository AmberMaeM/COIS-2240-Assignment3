
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedWriter;// to write in save transactions
import java.io.FileWriter;
import java.io.BufferedReader;//to read in display transaction details
import java.io.FileReader;
import java.io.IOException;

public class Transaction {
	///Q1
	private static Transaction transactionInstance;//singleton
	private Transaction() {
		
	}
	//Q1, methiod to get single instance of transaction
	public static Transaction getTransaction() {
		if (transactionInstance == null) {
			transactionInstance = new Transaction();
		}
		return transactionInstance;
	}
	
    // Perform the borrowing of a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);//part of Q2
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public boolean returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);//part of Q2
            return true;
        } else {
            System.out.println("This book was not borrowed by the member.");
            return false;
        }
    }

    // Get the current date and time in a readable format
    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    ////part of Q2
    //my understanding of bufferedwriter cones from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
    public void saveTransaction(String transactionDetails) {
    	
    	try(BufferedWriter writer = new BufferedWriter(new FileWriter("transaction.txt",true))){//using true makes sure i dont overwrite the file
    		writer.write(transactionDetails);
    		writer.newLine();
    	}catch (IOException e) {
    		System.out.println("Error:"+e.getMessage());
    	}
    }
    
    
    ////Q3
    public static void displayTransactionHistory() {
    	String transactionDetails = null;
    	
    	try(BufferedReader reader = new BufferedReader(new FileReader("transaction.txt"))){//using true makes sure i dont overwrite the file
    		//String transactionDetails;
    		boolean hasTransactions = false;
           	System.out.println("\n--- Transaction History ---");
           	
           	transactionDetails = reader.readLine(); //reading the first line
           	
           	while(transactionDetails != null) {//while txt isnt empty
           		System.out.println(transactionDetails);//priuntout details
           		hasTransactions =true;
           		transactionDetails= reader.readLine();//next line
           		
           	}
           	if (!hasTransactions) {//if it is empty send messages
           		System.out.println("no history found");
           	}
           	
    	}catch (IOException e) {
    		System.out.println("Error:"+e.getMessage());
    	}
    }
    
    
    
    
}
