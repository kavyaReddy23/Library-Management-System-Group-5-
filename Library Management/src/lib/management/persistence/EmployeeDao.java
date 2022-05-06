package lib.management.persistence;

import lib.management.entity.Employee;

public interface EmployeeDao {
	void issueBook(int empId);
	void returnBook(int empid);
	Employee getEmployeeDetails(int empId);
	
}
