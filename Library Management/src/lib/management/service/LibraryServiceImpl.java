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
	public boolean canIssue(int empId,int bookId) {
		Employee emp=employeeDao.getEmployeeDetails(empId);
		if(paymentpending(empId)>0.0)return false;
		if(emp.getNoOfBooksIssued()>=3)return false;
		if(bookDao.getBookById(bookId).isIssued())return false;
		return true;
	}
	public int getTotalBooksIssued(int empId)
	{
		Employee emp=employeeDao.getEmployeeDetails( empId);
		return emp.getNoOfBooksIssued();
				
	}
	@Override
	public double paymentpending(int empId) {
		ArrayList<TransactionEntity>transactions=transactionDao.getIssuedBooksForEmployee(empId);
		double payment=0;
		Employee emp=employeeDao.getEmployeeDetails(empId);
		if(transactions.size()==0)return 0;
		else if(emp.getPaymentPending()>0)return emp.getPaymentPending();
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
		employeeDao.issueBook(empId);
		bookDao.issueBook(bookId);
		transactionDao.issueBook(empId, bookId);
		return true;
	}
	@Override
	public boolean returnBook(int empId, int bookId) {
		employeeDao.returnBook(empId);
		bookDao.returnBook(bookId);
		transactionDao.returnBook(empId, bookId);
		return false;
	}
	
	public Book getBookById(int id) {
		
		return bookDao.getBookById(id);
		
	}
	public ArrayList<TransactionEntity>getTransactionsOfEmployee(int empId) {
		return transactionDao.getIssuedBooksForEmployee(empId);
	}
	@Override
	public ArrayList<Book> getBooksOfEmployee(int empId) {
		ArrayList<TransactionEntity>transactions=new ArrayList<TransactionEntity>();
		ArrayList<Book>books=new ArrayList<Book>();
		for(TransactionEntity t: transactions)
		{
			books.add(bookDao.getBookById(t.getBookId()));
		}
		return books;
	}
	
	@Override
	public boolean makePaymentPendingZero(int empId) {
		if(employeeDao.getEmployeeDetails(empId)==null) {
			return false;
		}else {
			employeeDao.makePaymentZero(empId);
			return true;
		}
	}
	@Override
	public void addEmployee(int empId, String empName, String phoneNumber) {
		employeeDao.addEmployee(empId, empName, phoneNumber);
		
	}
	
	
	
	// 1 add emmployee
	// 2 set payment 0 for employee
	// 3 delete the records in the transaction table for which isReturned is true.
	
	
	
	//issue  1.(get emp details, if pending payment or no. of books greater than 3 then not allowed to issue.)
	//		 2.(check for the book employee already has... if any of them is pending payment, then request to pay payment then allowed to issue)
	
	//return 1. check if the payment is pending for the book being returned

}
