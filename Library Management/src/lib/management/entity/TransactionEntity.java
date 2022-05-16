package lib.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEntity {
	private int transactionId;
	private int bookId;
	private int empId;
	private java.sql.Date issueDate;
	private java.sql.Date expectedReturnDate;
	private java.sql.Date actualReturnDate;
	private boolean paid;
	private boolean isReturned;
	
}
