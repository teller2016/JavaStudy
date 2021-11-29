package classex;

public class NewInstanceTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Person person1 = new Person();
		System.out.println(person1);
		
		Class pClass = Class.forName("classex.Person");
		Person person2 = (Person)pClass.newInstance(); 		//Class 클래스의 newInstance()메서드로 생성하기
		System.out.println(person2);
	}

}
