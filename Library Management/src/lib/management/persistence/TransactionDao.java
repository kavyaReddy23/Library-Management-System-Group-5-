package lib.management.persistence;

import java.util.List;

import lib.management.entity.TransactionEntity;

public interface TransactionDao {
	void issueBook(int empId, int bookId);
	void returnBook(int empid,int bookId);
	List<TransactionEntity> getIssuedBooksForEmployee(int empid);
}
