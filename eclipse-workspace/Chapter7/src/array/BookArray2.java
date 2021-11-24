package array;

public class BookArray2 {

	public static void main(String[] args) {
		Book[] library = new Book[2];	//Book클래스형으로 객체 배열 생성
		
		library[0] = new Book("태백산맥", "조정래");
		library[1] = new Book("데미안", "헤르만 헤세");
		
		for(int i=0;i<library.length;i++) {
			library[i].showBookInfo();
			System.out.println(library[i]);
		}
			
	}

}
