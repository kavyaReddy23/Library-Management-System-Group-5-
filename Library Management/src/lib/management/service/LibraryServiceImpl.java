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

}
