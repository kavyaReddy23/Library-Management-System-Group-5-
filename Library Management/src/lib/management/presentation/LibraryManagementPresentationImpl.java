package lib.management.presentation;
import lib.management.entity.*;
import lib.management.service.LibraryService;
import lib.management.service.LibraryServiceImpl;
import java.util.List;
import java.util.*;

public class LibraryManagementPresentationImpl implements LibraryManagementPresentation {
	Scanner scanner=new Scanner(System.in);
	private LibraryService libraryService=new LibraryServiceImpl();
	@Override
	public void login() {
		System.out.println("1. Login as Employee");
		System.out.println("2. Login as Librarian");
		int choice=scanner.nextInt();
		if(choice==1)
			showMenuForEmployee();
			else
				showMenuforLibrarian();
	}

	public void showMenuforLibrarian() {
	System.out.println("Press 1:Add New Employee");
	int choice1=scanner.nextInt();
		
	}

	public void showMenuForEmployee() {
	    System.out.println("Press 1: Search Employee Details:");
	    System.out.println("Press 2: Search Book By Type:");
	    System.out.println("Press 3: Search Book By Name:");
	    System.out.println("Press 4: Search Book By Keyword:");
	    System.out.println("Press 5: Search Book By Author Name:");
	    System.out.println("Press 6: Search My Details:");   
	    System.out.println("Press 7: Issue Book:");
	    System.out.println("Press 8: Return Book:");
	    
		int choice =scanner.nextInt();
		performChoiceByEmployeeID(choice);
	}	

	@Override
	public void performChoiceByEmployeeID(int choice) {
	Scanner scanner=new Scanner(System.in);
		int empId=0;
		int bookId=0;
		switch (choice) {
		case 1:
			System.out.println("Employee Details");
		 empId=scanner.nextInt();
		//	List<Employee> employeeList=libraryService.
		//	employeeList.stream().forEach(System.out::println);
			
			break;
		case 2:
			System.out.println("Enter type of the book to search");
			String bookType=scanner.nextLine();
			List<Book> bookTypeList=libraryService.searchByType(bookType);
			System.out.println("List of Books");
			bookTypeList.stream().forEach(System.out::println);
			break;
		case 3:
			System.out.println("Enter Name of the book to search");
			String bookName=scanner.nextLine();
			List<Book> bookNameList=libraryService.searchByName(bookName);
			System.out.println("List of Books");
			bookNameList.stream().forEach(System.out::println);
			break;
		case 4:
			System.out.println("Enter Keyword of the book to search");
			String bookKeyword=scanner.nextLine();
			List<Book> bookKeywordList=libraryService.searchByKeyword(bookKeyword);
			System.out.println("List of Books");
			bookKeywordList.stream().forEach(System.out::println);
			break;
		case 5:
			System.out.println("Enter Author Name of the book to search");
			String bookAuthorName=scanner.nextLine();
			List<Book> bookAuthorNameList=libraryService.searchByAuthorName(bookAuthorName);
			System.out.println("List of Books");
			bookAuthorNameList.stream().forEach(System.out::println);
			break;
		case 6:
			System.out.println("My Details");
			empId=scanner.nextInt();
		//	List<Employee> employeeDetails=libraryService.searchMyDetails(empId);
			//employeeDetails.stream().forEach(System.out::println);
			break;
		case 7:
			System.out.println("Issuing Book");
			 bookId=scanner.nextInt();
			// List<Book> issueBookList=libraryService.searchByAuthorName(bookAuthorName);
		//	 issueBookList.stream().forEach(System.out::println);
			break;
		case 8:
			System.out.println("Returning Book");
			 bookId=scanner.nextInt();
				// List<Book> returnBookList=libraryService.searchByAuthorName(bookAuthorName);
			//	returnBookList.stream().forEach(System.out::println);
			break;
			default:
				System.out.println("Invalid Input Details");

	}

}

	@Override
	public void performChoicebyLibrarian(int choice1) {
		switch(choice1) {
		case 1:
			System.out.println("Enter new Employee Details:");
			break;
		}
		
	}
}
