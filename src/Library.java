import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members = new ArrayList<Member>();
    private List<Book> books = new ArrayList<Book>();

    
    ///Q4
    // Add a new member to the library
    public boolean addMember(Member member) {
    	if (findMemberById(member.getId())!= null) {//if there is an existing id return false
    		System.out.println("Member w/ ID: "+ member.getId() + " already exists");
    		return false;
    	}
    	else {
    		 members.add(member);//else add member
    	        System.out.println("ID: "+ member.getId() + ", Member Name: "+ member.getName()+ " added");
    	        return true;
    	}
    	
       
    }
    //Q4
    // Add a new book to the library
    public boolean addBook(Book book) {
    	if(findBookById(book.getId()) != null) {//if existing book exists, return false
    		System.out.println("Book w/ ID: "+ book.getId() + " already exists");
    		return false;
    	}
    	else {
    		 books.add(book);//else add book
    	        System.out.println("ID: "+ book.getId() + ", Book Title: "+ book.getTitle()+ " added");
    	        return true;
    	}
    }

    // Find a member by ID
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // Find a book by ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // Get the list of members
    public List<Member> getMembers() {
        return members;
    }
    
    // Get the list of books
    public List<Book> getBooks() {
        return books;
    }
}
