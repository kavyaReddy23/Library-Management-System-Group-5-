package lib.management.persistence;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import java.sql.*;
import lib.management.entity.Book;
import lib.management.entity.Employee;

public class BookDaoImpl implements BookDao {
	private Connection connection;
	public BookDaoImpl() {
		super();
		this.connection=DataConnection.getConnection();
	}




	
	
	@Override
	public List<Book> getBookByType(String Type) {
		List<Book> bookList = new ArrayList<>();
		Book book = new Book();
		Statement statement = null;
			try {
				statement = connection.createStatement();
				 String booktype=Type;
				ResultSet resultSet = statement.executeQuery(
							"SELECT bookId,bookName,authorName,bookType FROM Books where bookType='"+booktype+"' and issued=0 group by bookName");
				while (resultSet.next()) {
					int id = resultSet.getInt("bookId");
					String typeOfBook = resultSet.getString("bookType");
					String bookName = resultSet.getString("bookName");
					String authorName = resultSet.getString("authorName");
					Book books = new Book(id,typeOfBook, bookName, authorName);
					bookList.add(books);
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		//get book by name/keyword
				
		
		
		
		
		//Librarian
		//set employee pending payment zero 
		//add new employee
		//delete transaction record for which books have been returned
		
		
		// transactionId(int) bookId(int) empId(int) issueDate(date) expectedReturnDate(date) actualReturnDate(date) isReturned(boolean)
		
		
		return bookList;
	}

	@Override
	public List<Book> getBookByKeyword(String bookType) {
		
		return null;
	}

	@Override
	public List<Book> getBookByName(String bookType) {
		
		return null;
	}

	@Override
	public List<Book> getBookByAuthorName(String bookType) {
		
		return null;
	}	
	
	//issue book
	//			 1.(create a new row in transaction table, set isAvailable in book table for that particular book id as false, 
	//				increment book count in employee db for that particular emp id)
	@Override
	public void issueBook(String empId, String bookId) {
		
		
	}


	//return book 1.set book is available in book table for that book id
	//			  2. decrement book count in employee table for that employee
			
	@Override
	public void returnBook(String empid, String bookId) {
		
	}
}

	

