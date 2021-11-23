package staticex;

public class StudentTest5 {

	public static void main(String[] args) {
		System.out.println(Student2.getSerialNum());
		
		Student3 studentJung = new Student3("정몰리");
		System.out.println(studentJung.cardNum);
		System.out.println(studentJung.studentNum);
		
		Student3 studentHa = new Student3("하보리");
		System.out.println(studentHa.cardNum);
	}

}
