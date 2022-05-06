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
	    System.out.println("Press 1 : Search Book By Type:");
		int choice =scanner.nextInt();
		performChoiceByEmployeeID(choice);
	}	

	@Override
	public void performChoiceByEmployeeID(int choice) {
	Scanner scanner=new Scanner(System.in);
		int empId=0;
		switch (choice) {
		case 1:
			System.out.println("enter type of the book to search");
			String bookType=scanner.nextLine();
			List<Book> bookList=libraryService.searchByType(bookType);
			System.out.println("List of Books");
			bookList.stream().forEach(System.out::println);
			break;

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
