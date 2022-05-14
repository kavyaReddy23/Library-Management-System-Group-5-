package lib.management.persistence;

import java.util.Optional;

import lib.management.entity.Employee;

public interface EmployeeDao {
	void issueBook(int empId);
	void returnBook(int empid);
	Employee getEmployeeDetails(int empId);
	void addEmployee(int empId,String empName,String phoneNumber);
	void makePaymentZero(int empId);
}
