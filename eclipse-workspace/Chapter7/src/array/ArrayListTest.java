package array;
import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {
		ArrayList<Book> library = new ArrayList<Book>();
		
		library.add(new Book("책1","몰리"));
		library.add(new Book("책2","보리"));
		
		for(int i=0;i<library.size();i++) {
			Book book = library.get(i);
			book.showBookInfo();
			//libray[i].showBookInfo(); //인덱스 사용 불가능
		}
		System.out.println("=== 향상된 for문 ===");
		for(Book book : library)
			book.showBookInfo();
	}

}
