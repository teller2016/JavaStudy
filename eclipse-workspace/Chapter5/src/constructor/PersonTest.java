package constructor;

public class PersonTest {

	public static void main(String[] args) {
		Person personKim = new Person();
		personKim.name = "김땡땡";	//디폴트 생성자로 클래스 생성 후
		personKim.weight = 85.5F;	//인스턴스 변수 값을 따로 초기화
		personKim.height = 180.0F;
		
		Person personLee = new Person("이몰리", 175, 75);	//인스턴스 변수 초기화 동시에 클래스 생성
	}

}
