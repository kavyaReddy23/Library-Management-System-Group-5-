package lib.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
		public Book(int id, String typeOfBook, String bookName, String authorName) {
			
		this.bookId=id;
		this.bookType=typeOfBook;
		this.bookName=bookName;
		this.authorName=authorName;
	}
		private int bookId;
		private String bookType;
		private String bookName;
		private String authorName;
		private boolean issued;
		private double lateFeePrice;
}
