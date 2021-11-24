package array;
import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {
		ArrayList<Book> library = new ArrayList<Book>();
		
		library.add(new Book("å1","����"));
		library.add(new Book("å2","����"));
		
		for(int i=0;i<library.size();i++) {
			Book book = library.get(i);
			book.showBookInfo();
			//libray[i].showBookInfo(); //�ε��� ��� �Ұ���
		}
		System.out.println("=== ���� for�� ===");
		for(Book book : library)
			book.showBookInfo();
	}

}
