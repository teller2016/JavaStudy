package array;

public class BookArray2 {

	public static void main(String[] args) {
		Book[] library = new Book[2];	//BookŬ���������� ��ü �迭 ����
		
		library[0] = new Book("�¹���", "������");
		library[1] = new Book("���̾�", "�츣�� �켼");
		
		for(int i=0;i<library.length;i++) {
			library[i].showBookInfo();
			System.out.println(library[i]);
		}
			
	}

}
