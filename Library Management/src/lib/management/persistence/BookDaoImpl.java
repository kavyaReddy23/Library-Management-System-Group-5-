package lib.management.persistence;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;
import lib.management.entity.Book;
import lib.management.entity.Employee;

public class BookDaoImpl implements BookDao {
	private Connection connection;

	public BookDaoImpl() {
		super();
		this.connection = DataConnection.getConnection();
	}

	@Override
	public List<Book> getBookByType(String Type) {
		List<Book> bookList = new ArrayList<>();
		Book book = new Book();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String booktype = Type;
			ResultSet resultSet = statement
					.executeQuery("SELECT bookId,bookName,authorName,bookType FROM Books where bookType='" + booktype
							+ "' and issued=false group by bookName");
			while (resultSet.next()) {
				int id = resultSet.getInt("bookId");
				String typeOfBook = resultSet.getString("bookType");
				String bookName = resultSet.getString("bookName");
				String authorName = resultSet.getString("authorName");
				Book books = new Book(id, typeOfBook, bookName, authorName);
				bookList.add(books);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	// Librarian
	// set employee pending payment zero
	// add new employee
	// delete transaction record for which books have been returned

	// transactionId(int) bookId(int) empId(int) issueDate(date)
	// expectedReturnDate(date) actualReturnDate(date) isReturned(boolean)

	@Override
	public List<Book> getBookByKeyword(String bookType) {
		List<Book> bookList = new ArrayList<Book>();
		Book book = new Book();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String key = bookType;
			ResultSet resultSet = statement
					.executeQuery("SELECT bookId,bookName,authorName,issued,lateFeePrice,bookType FROM Books where bookName like '%" + key
							+ "%' and issued=false group by bookName");
			while(resultSet.next())
			{
				int bookId=resultSet.getInt("bookId");
				 bookType=resultSet.getString("bookType");
				 String bookKeyword=resultSet.getString("bookName");
				String authorName=resultSet.getString("authorName");
				boolean issued=resultSet.getBoolean("issued");
				double lateFeePrice=resultSet.getDouble("lateFeePrice");
				book=new Book(bookId,bookType,bookKeyword,authorName,issued,lateFeePrice);
				bookList.add(book);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<Book> getBookByName(String bookType) {
		List<Book> bookList = new ArrayList<Book>();
		Book book = new Book();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String bookName = bookType;
			ResultSet resultSet = statement
					.executeQuery("SELECT distinct bookId,bookName,authorName,issued,lateFeePrice,bookType FROM Books where bookName='" + bookName
							+ "' and issued=false");
			while(resultSet.next())
			{
				int bookId=resultSet.getInt("bookId");
				 bookType=resultSet.getString("bookType");
				 bookName=resultSet.getString("bookName");
				String authorName=resultSet.getString("authorName");
				boolean issued=resultSet.getBoolean("issued");
				double lateFeePrice=resultSet.getDouble("lateFeePrice");
				book=new Book(bookId,bookType,bookName,authorName,issued,lateFeePrice);
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bookList.size());
		return bookList;
	}

	@Override
	public List<Book> getBookByAuthorName(String bookType) {
		List<Book> bookList = new ArrayList<Book>();
		Book book = new Book();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String authorName = bookType;
			ResultSet resultSet = statement
					.executeQuery("SELECT  bookId,bookName,authorName,issued,lateFeePrice,bookType FROM Books where authorName='"
							+ authorName + "' and issued=false group by bookName");
			while(resultSet.next())
			{
				int bookId=resultSet.getInt("bookId");
				 bookType=resultSet.getString("bookType");
				 String bookAuthorName=resultSet.getString("bookName");
				 authorName=resultSet.getString("authorName");
				boolean issued=resultSet.getBoolean("issued");
				double lateFeePrice=resultSet.getDouble("lateFeePrice");
				book=new Book(bookId,bookType,bookAuthorName,authorName,issued,lateFeePrice);
				bookList.add(book);
		}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public void issueBook(int bookId) {

		Statement statement = null;
		try {
			statement = connection.createStatement();
			//String updatingBookAvailableStatus = "update books set issued = true where bookId =" + bookId;
			int rows=statement.executeUpdate( "update books set issued = true where bookId =" + bookId);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// return book 1.set book is available in book table for that book id
	// 2. decrement book count in employee table for that employee
	// 3. change in transaction table isReturned to True

	@Override
	public void returnBook(int bookId) {
		Statement statement = null;

		try {
			statement = connection.createStatement();
			String updatingBook = "update books set issued = false where bookId =" + bookId;
			statement.executeUpdate(updatingBook);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public Book getBookById(int bookid) {
		Book book=null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet= statement.executeQuery("select * from books where bookId="+bookid);
			while(resultSet.next())
			{
				int bookId=resultSet.getInt("bookId");
				String bookType=resultSet.getString("bookType");
				String bookName=resultSet.getString("bookName");
				String authorName=resultSet.getString("authorName");
				boolean issued=resultSet.getBoolean("issued");
				double lateFeePrice=resultSet.getDouble("lateFeePrice");
				book=new Book(bookId,bookType,bookName,authorName,issued,lateFeePrice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
}
