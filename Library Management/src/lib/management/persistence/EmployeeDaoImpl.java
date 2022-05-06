package lib.management.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDaoImpl implements EmployeeDao {
	private Connection connection;

	public EmployeeDaoImpl() {
		super();
		this.connection = DataConnection.getConnection();
	}

	@Override
	public void issueBook(int empId) {
		Statement statement = null;
		try {
			statement = connection.createStatement();

			String updatingTheEmployeeTable = "update employee set noOfBooks = noOfBooks+1  where empId =" + empId;
			statement.executeUpdate(updatingTheEmployeeTable);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void returnBook(int empId) {
		Statement statement = null;

		try {

			String updatingEmployee = "update employee set noOfBooks = noOfBooks-1  where empId =" + empId;
			statement.executeUpdate(updatingEmployee);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
