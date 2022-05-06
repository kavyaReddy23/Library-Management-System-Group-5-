package lib.management.service;


import java.util.List;
import lib.management.entity.Book;
import lib.management.entity.Employee;
import lib.management.persistence.BookDao;
import lib.management.persistence.BookDaoImpl;
import lib.management.persistence.EmployeeDao;
import lib.management.persistence.EmployeeDaoImpl;
import lib.management.persistence.TransactionDao;
import lib.management.persistence.TransactionDaoImpl;

public class LibraryServiceImpl implements LibraryService {
	private BookDao bookDao=new BookDaoImpl();
	private EmployeeDao employeeDao=new EmployeeDaoImpl();
	private TransactionDao transactionDao=new TransactionDaoImpl();  
	@Override
	public List<Book> searchByType(String type) {
		return bookDao.getBookByType(type);
	}
	@Override
	public List<Book> searchByKeyword(String keyword) {
		return bookDao.getBookByKeyword(keyword);
	}
	@Override
	public List<Book> searchByName(String name) {
		return bookDao.getBookByName(name);
	}
	@Override
	public List<Book> searchByAuthorName(String authorName) {
		return bookDao.getBookByAuthorName(authorName);
	}
	@Override
	public boolean canIssue(int empId) {
		Employee emp=new Employee();
		if((int)paymentpending(empId)>0)return false;
		if(emp.getNoOfBooksIssued()>=3)return false;
		return true;
	}
	@Override
	public double paymentpending(int empId) {
		
		return 0;
	}
	@Override
	public boolean issue(int empId, int bookId) {
		bookDao.issueBook(bookId);
		return false;
	}
	@Override
	public boolean returnBook(int empId, int bookId) {
		bookDao.returnBook(bookId);
		return false;
	}
	
	
	
	//issue  1.(get emp details, if pending payment or no. of books greater than 3 then not allowed to issue.)
	//		 2.(check for the book employee already has... if any of them is pending payment, then request to pay payment then allowed to issue)
	
	//return 1. check if the payment is pending for the book being returned

}
