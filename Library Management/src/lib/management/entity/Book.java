package lib.management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
		private int bookId;
		private String bookType;
		private String bookName;
		private String authorName;
		private boolean issued;
		private double lateFeePrice;
}
