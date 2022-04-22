package lib.management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
	private int empId;
	private String empName;
	private double paymentPending;
	private String phoneNumber;
	private int noOfBooksIssued;
}
