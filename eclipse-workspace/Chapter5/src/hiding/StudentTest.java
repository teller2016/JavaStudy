package hiding;

public class StudentTest {

	public static void main(String[] args) {
		Student studentLee = new Student();
		//studentLee.studentName = "이상원";		//오류 발생
		studentLee.setStudentName("이상원");	//public 메서드를 통해 private 변수에 접근 가능
        
		System.out.println(studentLee.getStudentName());
	}

}
