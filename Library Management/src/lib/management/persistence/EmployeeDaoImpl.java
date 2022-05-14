package lib.management.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lib.management.entity.Employee;

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
			statement=connection.createStatement();
			String updatingEmployee = "update employee set noOfBooks = noOfBooks-1  where empId =" + empId;
			statement.executeUpdate(updatingEmployee);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public Employee getEmployeeDetails(int empid) {
		Statement statement = null;
		Employee emp=null;
		try {
			statement=connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * from employee where empId="+empid);
			while(resultSet.next()) {
				int empId=resultSet.getInt("empId");
				String empName=resultSet.getString("empName");
				double paymentPending=resultSet.getDouble("paymentPending");
				String phoneNumber=resultSet.getString("phoneNumber");
				int noOfBooksIssued=resultSet.getInt("noOfBooks");
				emp=new Employee(empId,empName,paymentPending,phoneNumber,noOfBooksIssued);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
		
	}

}
