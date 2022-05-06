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
					.executeQuery("SELECT bookId,bookName,authorName,bookType FROM Books where bookName like '%" + key
							+ "%' and issued=false group by bookName");
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
					.executeQuery("SELECT bookId,bookName,authorName,bookType FROM Books where bookName='" + bookName
							+ "' and issued=false group by bookName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					.executeQuery("SELECT bookId,bookName,authorName,bookType FROM Books where authorName='"
							+ authorName + "' and issued=false group by bookName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	// issue book
	// 1.(create a new row in transaction table, set isAvailable in book table for
	// that particular book id as false,
	// increment book count in employee db for that particular emp id)
	@Override
	public void issueBook(int bookId) {

		Statement statement = null;
		try {
			statement = connection.createStatement();
			String updatingBookAvailableStatus = "update books set issued = true where bookId =" + bookId;
			statement.executeUpdate(updatingBookAvailableStatus);

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
}
