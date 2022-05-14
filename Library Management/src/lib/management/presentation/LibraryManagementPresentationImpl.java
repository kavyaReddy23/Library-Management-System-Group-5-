package lib.management.presentation;

import lib.management.entity.*;
import lib.management.service.LibraryService;
import lib.management.service.LibraryServiceImpl;

import java.util.*;

public class LibraryManagementPresentationImpl implements LibraryManagementPresentation {
	Scanner scanner = new Scanner(System.in);
	private LibraryService libraryService = new LibraryServiceImpl();
	private int empId;
	private boolean runEmp;

	@Override
	public void start() {
		while (true) {
			System.out.println("1. Login as Employee");
			System.out.println("2. Login as Librarian");
			int choice = scanner.nextInt();
			if (choice == 1) {
				this.runEmp = true;
				System.out.println("Enter your employee Id:");
				this.empId = scanner.nextInt();
				while (runEmp)
					showMenuForEmployee();
			} else
				while (true)
					showMenuforLibrarian();
		}
	}

	public void showMenuforLibrarian() {
	System.out.println("Press 1:Add New Employee");
	System.out.println("Press 2:Make Payment Zero of an Employee by Id");
	int choice1=scanner.nextInt();
	performChoicebyLibrarian(choice1);

	}

	public void showMenuForEmployee() {
		// System.out.println("Press 1: Search book details:");
		System.out.println("Press 1: Search Book By Type:");
		System.out.println("Press 2: Search Book By Name:");
		System.out.println("Press 3: Search Book By Keyword:");
		System.out.println("Press 4: Search Book By Author Name:");
		System.out.println("Press 5: Search My Details:");
		System.out.println("Press 6: Issue Book:");
		System.out.println("Press 7: Return Book:");
		System.out.println("Press 0: To logout");
		System.out.println("Press -1: To exit");

		int choice = scanner.nextInt();
		performChoiceByEmployeeID(choice);
	}

	@Override
	public void performChoiceByEmployeeID(int choice) {
		int bookId = 0;
		Scanner sc = new Scanner(System.in);
		switch (choice) {
		case -1:
			System.exit(0);
		case 0:
			this.runEmp = false;
			break;
		case 1:
			System.out.println("Enter type of the book to search");
			String bookType = sc.nextLine();
			List<Book> bookTypeList = libraryService.searchByType(bookType);
			System.out.println("List of Books");
			bookTypeList.stream().forEach(b->printBook(b));
			break;
		case 2:
			System.out.println("Enter Name of the book to search");
			String bookName = sc.nextLine();
			List<Book> bookNameList = libraryService.searchByName(bookName);
			System.out.println("List of Books");
			bookNameList.stream().forEach(b->printBook(b));
			break;
		case 3:
			System.out.println("Enter Keyword of the book to search");
			String bookKeyword = sc.nextLine();
			List<Book> bookKeywordList = libraryService.searchByKeyword(bookKeyword);
			System.out.println("List of Books");
			bookKeywordList.stream().forEach(b->printBook(b));
			break;
		case 4:
			System.out.println("Enter Author Name of the book to search");
			String bookAuthorName = sc.nextLine();
			List<Book> bookAuthorNameList = libraryService.searchByAuthorName(bookAuthorName);
			// System.out.println("List of Books");
			bookAuthorNameList.stream().forEach(b->printBook(b));
			break;
		case 5:
			System.out.println("Employee Details");
			double paymentPending = libraryService.paymentpending(empId);
			ArrayList<TransactionEntity> empTransactions = libraryService.getTransactionsOfEmployee(empId);
			System.out.println("Payment pending is : " + paymentPending);
			
			if(empTransactions.size()>0)
			{
				System.out.println("Your books:");
				for(TransactionEntity t: empTransactions)
				{
					Book book=libraryService.getBookById(t.getBookId());
					printBook(book, t.getExpectedReturnDate());
				}
			}
			else {
				System.out.println("No books issued yet");
			}
			break;
		case 6:
			System.out.println("Enter BookId:");
			bookId = sc.nextInt();
			if (libraryService.canIssue(empId, bookId)) {
				libraryService.issue(empId, bookId);
				System.out.println("Employee has issued the book");
			} else {
				double payment = libraryService.paymentpending(empId);
				if (payment > 0)
					System.out.println("Employee need to Pay " + payment);
				else if (libraryService.getTotalBooksIssued(empId) >= 3)
					System.out.println("Employe issued with more than three books");
				else
					System.out.println("Book already Issued");

			}

			break;
		case 7:
			System.out.println("Enter book Id from below");
			ArrayList<TransactionEntity> empTransactions1 = libraryService.getTransactionsOfEmployee(empId);
			if(empTransactions1.size()>0)
			{
				System.out.println("Your books:");
				for(TransactionEntity t: empTransactions1)
				{
					Book book=libraryService.getBookById(t.getBookId());
					printBook(book, t.getExpectedReturnDate());
				}
			}
			else {
				System.out.println("No books issued yet");
				break;
			}
			bookId = sc.nextInt();
			double payment = libraryService.paymentpending(empId);
			if (payment > 0)
				System.out.println("Employee need to Pay " + payment);
			else {
				libraryService.returnBook(empId, bookId);
				System.out.println("Returned Successfully");
			}

			// List<Book> returnBookList=libraryService.searchByAuthorName(bookAuthorName);
			// returnBookList.stream().forEach(System.out::println);
			break;
		default:
			System.out.println("Invalid Input Details");
		}

	}

	public void printBook(Book book) {
		System.out.println("Book Id:"+book.getBookId()+"|| Book Name:"+book.getBookName()+"|| Author Name:"+book.getAuthorName()+"|| Book Type:"+book.getBookType());
	
	}
	public void printBook(Book book, Date date) {
		System.out.println("Book Id: "+book.getBookId()+"|| Book Name:"+book.getBookName()+"|| Author Name:"+book.getAuthorName()+"|| Book Type:"+book.getBookType()+"|| Expected Return Date:"+date);
		
	}

	@Override
	public void performChoicebyLibrarian(int choice1) {
		switch (choice1) {
		case 1:
			System.out.println("Enter new Employee Details:");
			System.out.println("Enter Employee Id");
			int empId = scanner.nextInt();
			System.out.println("Enter Employee Name");
			String name = scanner.nextLine();
			System.out.println("Enter Employee Phone Number");
			String phoneNumber = scanner.nextLine();
			libraryService.addEmployee(empId, name, phoneNumber);
			System.out.println("Employee Added Successfully");
			break;
		case 2:
			System.out.println("Enter Employee id to amek paymnet 0");
			int id = scanner.nextInt();
			libraryService.makePaymentPendingZero(id);
			System.out.println("Payment made zero");
		case -1:
			System.exit(0);
		}

	}
}
