package classpart;

public class Student {	//Ŭ���� �̸�
	int studentID;		//��� ������
	String studentName;
	int grade;
	String address;
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String name) {
		studentName = name;
	}
	
	public void showStudentInfo() {
		System.out.println(studentName + ", " + address);
	}
	
	public static void main(String[] arg) {
		Student studentAhn = new Student();
		studentAhn.studentName = "�ȿ���";
		
		System.out.println(studentAhn.studentName);
		System.out.println(studentAhn.getStudentName());
	}
}
