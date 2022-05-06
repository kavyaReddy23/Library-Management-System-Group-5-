package lib.management.persistence;

import lib.management.entity.*;

import java.util.List;

public interface BookDao {
List<Book> getBookByType(String bookType);
List<Book> getBookByKeyword(String bookType);
List<Book> getBookByName(String bookType);
List<Book> getBookByAuthorName(String bookType);
void issueBook( int bookId);
void returnBook(int bookId);

}
