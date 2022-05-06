package lib.management.persistence;

public interface EmployeeDao {
	void issueBook(int empId);
	void returnBook(int empid);
}
