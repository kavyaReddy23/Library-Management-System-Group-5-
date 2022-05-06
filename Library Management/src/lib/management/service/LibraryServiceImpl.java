package lib.management.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lib.management.entity.Book;
import lib.management.entity.Employee;
import lib.management.entity.TransactionEntity;
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
		Employee emp=employeeDao.getEmployeeDetails(empId);
		if((int)paymentpending(empId)>0)return false;
		if(emp.getNoOfBooksIssued()>=3)return false;
		return true;
	}
	@Override
	public double paymentpending(int empId) {
		ArrayList<TransactionEntity>transactions=transactionDao.getIssuedBooksForEmployee(empId);
		double payment=0;
		if(transactions.size()==0)return 0;
		else
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			Date todayDate = new Date();
			for(TransactionEntity each: transactions)
			{
				if(each.getExpectedReturnDate().before(todayDate)) {//todays date is greater than expected date
					long millis = Math.abs(todayDate.getTime() - each.getExpectedReturnDate().getTime());
					long extraDays = TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS);
					Book book=bookDao.getBookById(each.getBookId());
					payment=payment+(book.getLateFeePrice()*extraDays);
				}
			}
		}
		return payment;
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
