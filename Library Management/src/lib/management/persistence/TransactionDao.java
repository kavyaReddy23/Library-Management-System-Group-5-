package lib.management.persistence;

import java.util.ArrayList;


import lib.management.entity.TransactionEntity;

public interface TransactionDao {
	void issueBook(int empId, int bookId);
	void returnBook(int empid,int bookId);

	ArrayList<TransactionEntity> getTransactionDetailsForEmployee(int empid);
	void updatePayment(int transactionId);

}
