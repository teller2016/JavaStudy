package chapter5;

public class Student {
	int studentID;
	String studentName;
	private String privateName = "private";
	
	public String getStudentName() {
		return studentName;
	}
	
	public Student(int studentID, String studentName) {
		this.studentID = studentID;
		this.studentName = studentName;
	}
	
	public Student() {	}

	public static void main(String[] args) {
		Student student = new Student();
		student.studentName = "Molly";
		System.out.println(student.privateName);
		System.out.println(student.getStudentName());
		func();
	}
	
	static void func() {
		System.out.println("나는 함수");
	}

}
