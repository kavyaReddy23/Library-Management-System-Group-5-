package lib.management.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import lib.management.entity.Book;
import lib.management.entity.TransactionEntity;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

public class TransactionDaoImpl implements TransactionDao {

	private Connection connection;

	public TransactionDaoImpl() {
		super();
		this.connection = DataConnection.getConnection();
	}

	@Override
	public void issueBook(int empId, int bookId) {
		Date issueDate = new Date();
		java.sql.Date issueDateSQL=new java.sql.Date(issueDate.getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		Date expectedReturnDate = calendar.getTime();
		java.sql.Date expectedReturnDateSQL=new java.sql.Date(expectedReturnDate.getTime());
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String insertInTransaction = "insert into transaction (bookId,empId,issueDate,expectedReturnDate,actualReturnDate,isPaid,isReturned) values("
					+ bookId + "," + empId + ", '" + issueDateSQL + "' , '"
					+ expectedReturnDateSQL + "' ," + "null" +","+"false"+ "," + "false)";
			
			
		statement.executeUpdate(insertInTransaction);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	@Override
	public ArrayList<TransactionEntity> getTransactionDetailsForEmployee(int empid){
		Statement statement = null;
		ArrayList<TransactionEntity>transactionList=new ArrayList<TransactionEntity>();
		try {
			statement = connection.createStatement();
			ResultSet resultSet=  statement.executeQuery("select * from transaction where empId="+empid+" and isReturned = false;");
			while (resultSet.next()) {
				int transactionId=resultSet.getInt("transactionId");
				int bookId=resultSet.getInt("bookId");
				int empId=resultSet.getInt("empId");
				java.sql.Date issueDate=resultSet.getDate("issueDate");
				java.sql.Date expectedReturnDate=resultSet.getDate("expectedReturnDate");
				java.sql.Date actualReturnDate=resultSet.getDate("actualReturnDate");
				boolean isPaid=resultSet.getBoolean("isPaid");
				boolean isReturned=resultSet.getBoolean("isReturned");
				TransactionEntity t=new TransactionEntity(transactionId,bookId,empId,issueDate,expectedReturnDate,actualReturnDate,isPaid,isReturned);
				transactionList.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return transactionList;
		
		
	}

	@Override
	public void returnBook(int empid, int bookId) {
		Statement statement = null;
		Date actualReturnDate = new Date();
		java.sql.Date actualReturnDateSQL=new java.sql.Date(actualReturnDate.getTime());
		try {
			statement = connection.createStatement();
			String updatingTransactionTable = "update transaction set isReturned = true, actualReturnDate = '"+actualReturnDateSQL+"' where empId="+empid+" and bookId="+bookId;
			statement.executeUpdate(updatingTransactionTable);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void updatePayment(int transactionId) {
		
		Statement statement=null;
		try {
			statement=connection.createStatement();
			String updatePayment="update transaction set isPaid = true where transactionId = "+transactionId;
			statement.executeUpdate(updatePayment);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

}
