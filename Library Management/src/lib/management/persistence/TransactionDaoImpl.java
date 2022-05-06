package lib.management.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TransactionDaoImpl implements TransactionDao {

	private Connection connection;

	public TransactionDaoImpl() {
		super();
		this.connection = DataConnection.getConnection();
	}

	@Override
	public void issueBook(int empId, int bookId) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date issueDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		Date expectedReturnDate = calendar.getTime();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String insertInTransaction = "insert into transaction (bookId,empId,issueDate,expectedReturnDate,actualReturnDate,isReturned) values("
					+ bookId + "," + empId + "," + formatter.format(issueDate) + ","
					+ formatter.format(expectedReturnDate) + "," + "null" + "," + "false)";
			statement.executeUpdate(insertInTransaction);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void returnBook(int empid, int bookId) {
		Statement statement = null;

		try {
			statement = connection.createStatement();
			String updatingTransactionTable = "update transaction set isReturned = True";
			statement.executeUpdate(updatingTransactionTable);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
