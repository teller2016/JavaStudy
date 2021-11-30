package collection;
import java.util.LinkedList;
public class LinkedListTest {

	public static void main(String[] args) {
		LinkedList<String> myList = new LinkedList<String>();
		
		myList.add("A");
		myList.add("B");
		myList.add("C");
		
		System.out.println(myList);		//리스트 전체 출력
		
		myList.add(1, "D");	//링크드 리스트 첫번째 위치에 추가
		System.out.println(myList);
		
		myList.addFirst("O"); //링크드 리스트 맨 앞에 추가
		System.out.println(myList);
		
		System.out.println(myList.removeLast());	//연결 리스트의 맨 뒤 요소 삭제 후 해당 요소 출력
		System.out.println(myList);
	}
}
