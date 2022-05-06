package lib.management.service;
import lib.management.entity.*;
import java.util.*;
public interface LibraryService {
 List<Book> searchByType(String type);
 List<Book> searchByKeyword(String keyword);
 List<Book> searchByName(String name);
 List<Book> searchByAuthorName(String authorName);
 boolean canIssue(String empId);
 double paymentpending(String empId);
 boolean issue(String empId,String bookId);
 boolean returnBook(String empId,String bookId);
 
}
