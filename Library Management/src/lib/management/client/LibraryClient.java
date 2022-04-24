package lib.management.client;
import java.util.*;

import lib.management.presentation.LibraryManagementPresentation;
import lib.management.presentation.LibraryManagementPresentationImpl;

public class LibraryClient {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		LibraryManagementPresentation libraryManagementPresentation = new LibraryManagementPresentationImpl();
		while (true) {
			libraryManagementPresentation.login();
		}
	}

}
