package lib.management.presentation;

public interface LibraryManagementPresentation {
	
	void login();
	void showMenuforLibrarian();
	void showMenuForEmployee();
	void performChoiceByEmployeeID(int choice);
	 void performChoicebyLibrarian(int choice);
	
}
