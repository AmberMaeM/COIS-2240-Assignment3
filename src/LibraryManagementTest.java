import static org.junit.Assert.*;

import org.junit.Before;//for setup annotation
import org.junit.Test;// for test annotation

import java.lang.reflect.Constructor;//for task2 Q3
import java.lang.reflect.Modifier;//for task2 Q3

public class LibraryManagementTest {
	private Book book;
	private Member member;
	private Transaction transaction;
	
	
	@Before  /////task3 Q2
	public void setUp() { 
		//creating book,member and transaction for testing
		try {
			book = new Book(100,"BookTest");
			member = new Member (1,"MemberTest");
			transaction = Transaction.getTransaction();
		}catch(Exception e) {
			fail("Exception shouldnt be thrown for valid book ID"+ e.getMessage());
		}
		
		
	
	}
	
	@Test  ///task3 Q2
	public void testBorrowReturn() {
		//source for assurttrue/false https://stackoverflow.com/questions/3241105/java-junit-asserttrue-vs-assertfalse
		
		//make sure book is available at start
		assertTrue("Book should be available", book.isAvailable());
		
		
		//borrow the book and assert that it is unavailable
		boolean borrowSuccess = transaction.borrowBook(book, member);
		assertTrue("Borrowing should be successful",borrowSuccess);	
		assertFalse("Book should be unavailable after borrowing", book.isAvailable());
		
		//make sure when book is borrowed, it fails
		boolean borrowFail = transaction.borrowBook(book, member);
		assertFalse("Borrowing for an already borrowed book should fail",borrowFail);
		
		
		
		//return book and make sure its availabe again
		boolean returnSuccess = transaction.returnBook(book,member);
		assertTrue("return should be successful", returnSuccess);
		assertTrue("Book should be available after returning",book.isAvailable());
		
		//try to return book again and make sure it fails. to should book is not borrowed.
		boolean returnFail = transaction.returnBook(book,member);
		assertFalse("returning should fail if the book isnt borrowed",returnFail);
		
	}
	
	
	
	
	
// my understanding of junit4 assertions comes from https://www.baeldung.com/junit-assertions
	@Test ///task 3 Q1
	public void testBookId() {
		try {
			Book book1 = new Book(100, "Book1");
			Book book2 = new Book(999, "Book2");
			//Book invBook1 = new Book(1000, "invBook1");
			
			
			try {
				Book invBook1 = new Book(7874905, "invBook1");
				fail("exception thown for 7874905");
				
			}catch(Exception e) {
				assertEquals("invalid Book ID: 7874905, needs to be between 100 and 999",e.getMessage());
			}
			
			try {
				Book invBook2 = new Book(99, "invBook2");
				fail("exception thrown for 99");
			}catch(Exception e) {
				assertEquals("invalid Book ID: 99, needs to be between 100 and 999",(e.getMessage()));
			}
		
		
		
		}catch(Exception e){
			fail("exception shouldnt be thrown for valid IDs");
		}
		
		
	}
	
	
	@Test  ///task3 Q3
	public void testSingletonTransaction() throws Exception{
		try {
			//grabs the constructor of the transaction class using reflec tion
			Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
			//makes sure coonstructor is private
			int modifiers = constructor.getModifiers();
			//checks if modifier is  private
			assertTrue("the transaction constructor should be private", Modifier.isPrivate(modifiers));
			
		}catch(NoSuchMethodException e){//this exception happens when the constructor youre trying to get theough reflection isnt found
			fail("transaction class should have a private constructor. Error: "+ e.getMessage());
		}
	}
	
	

}
