package staticex;

public class Student1 {
	public static int serialNum = 1000;
	public int studentID;
	public String studentName;
	public int grade;
	public String address;
	
	public Student1() {
		serialNum++;			//�л��� �����ɶ� ���� ����
		studentID = serialNum;	//������ ���� �й� ������ �ο�
	}
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String name) {
		studentName = name;
	}
}
