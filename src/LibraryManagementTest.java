import static org.junit.Assert.*;

import org.junit.Test;

public class LibraryManagementTest {
// my understanding of junit4 assertions comes from https://www.baeldung.com/junit-assertions
	@Test
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

}
