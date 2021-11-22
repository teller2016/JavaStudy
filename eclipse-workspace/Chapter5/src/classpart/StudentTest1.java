package classpart;

public class StudentTest1 {

	public static void main(String[] args) {
		Student student1 = new Student();	//첫번쨰 학생 생성
		student1.studentName = "안연수";
		System.out.println(student1.studentName);
		
		Student student2 = new Student();	//두번째 학생 생성
		student2.studentName = "안승연";
		System.out.println(student2.studentName);
	}

}
