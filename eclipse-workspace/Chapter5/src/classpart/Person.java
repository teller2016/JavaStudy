package classpart;

public class Person {
	String name;
	int height;
	double weight;
	char gender;
	boolean married;
	
	public void getName() {
		System.out.println(name);
	}
	
	public static void main(String[] arg) {
		Person person1 = new Person();
		person1.name = "¸ô¸®";
		System.out.println(person1.name);
	}
}
