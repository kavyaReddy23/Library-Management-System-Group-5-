package lib.management.persistence;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import java.sql.*;
import lib.management.entity.Book;

public class BookDaoImpl implements BookDao {
	Scanner scanner = new Scanner(System.in);

	@Override
	public List<Book> getBookByType(String Type) {
		List<Book> bookList = new ArrayList<>();
		Book book = new Book();
		Statement statement = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarymanagement", "root",
				"wiley");) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			statement = connection.createStatement();
            String booktype=Type;
			ResultSet resultSet = statement.executeQuery(
					"SELECT bookId,bookName,authorName,bookType FROM Books where bookType=booktype and issued=0 group by bookName");
			while (resultSet.next()) {
				int id = resultSet.getInt("bookId");
				String typeOfBook = resultSet.getString("bookType");
				String bookName = resultSet.getString("bookName");
				String authorName = resultSet.getString("authorName");
				Book books = new Book(id,typeOfBook, bookName, authorName);
				bookList.add(books);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bookList;
	}
}

	

