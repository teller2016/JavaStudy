package collection;
import java.util.LinkedList;
public class LinkedListTest {

	public static void main(String[] args) {
		LinkedList<String> myList = new LinkedList<String>();
		
		myList.add("A");
		myList.add("B");
		myList.add("C");
		
		System.out.println(myList);		//����Ʈ ��ü ���
		
		myList.add(1, "D");	//��ũ�� ����Ʈ ù��° ��ġ�� �߰�
		System.out.println(myList);
		
		myList.addFirst("O"); //��ũ�� ����Ʈ �� �տ� �߰�
		System.out.println(myList);
		
		System.out.println(myList.removeLast());	//���� ����Ʈ�� �� �� ��� ���� �� �ش� ��� ���
		System.out.println(myList);
	}
}
