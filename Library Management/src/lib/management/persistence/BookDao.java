package lib.management.persistence;

import lib.management.entity.*;

import java.util.List;

public interface BookDao {
List<Book> getBookByType(String bookType);

}
