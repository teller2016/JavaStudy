package classpart;

public class Student {	//클래스 이름
	int studentID;		//멤버 변수들
	String studentName;
	int grade;
	String address;
	
	public void showStudentInfo() {
		System.out.println(studentName + ", " + address);
	}
}
