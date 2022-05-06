package lib.management.persistence;

public interface TransactionDao {
	void issueBook(int empId, int bookId);
	void returnBook(int empid,int bookId);
}
