package constructor;

public class Person {
	String name;
	float height;
	float weight;
	
	public Person() {}	//디폴트 생성자
	
	public Person(String pname) {	//이름만 받는 생성자
		name = pname;
	}
	
	public Person(String pname, float pheight, float pweight) {
		name = pname;
		height = pheight;
		weight = pweight;
	}
}
