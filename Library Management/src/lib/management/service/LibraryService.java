package lib.management.service;
import lib.management.entity.*;
import java.util.*;
public interface LibraryService {
 List<Book> searchByType(String type);
 List<Book> searchByKeyword(String keyword);
 List<Book> searchByName(String name);
 List<Book> searchByAuthorName(String authorName);
 boolean canIssue(int empId);
 double paymentpending(int empId);
 boolean issue(int empId,int bookId);
 boolean returnBook(int empId,int bookId);
 
}
