package lib.management.service;


import java.util.List;
import lib.management.entity.Book;
import lib.management.persistence.BookDao;
import lib.management.persistence.BookDaoImpl;

public class LibraryServiceImpl implements LibraryService {
	private BookDao bookDao=new BookDaoImpl();
	@Override
	public List<Book> searchByType(String type) {
	
		return bookDao.getBookByType(type);
	}
	
	
	
	//issue  1.(get emp details, if pending payment or no. of books greater than 3 then not allowed to issue.)
	//		 2.(check for the book employee already has... if any of them is pending payment, then request to pay payment then allowed to issue)
	
	//return 1. check if the payment is pending for the book being returned

}
