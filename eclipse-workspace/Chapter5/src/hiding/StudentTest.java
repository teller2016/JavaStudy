package hiding;

public class StudentTest {

	public static void main(String[] args) {
		Student studentLee = new Student();
		//studentLee.studentName = "�̻��";		//���� �߻�
		studentLee.setStudentName("�̻��");	//public �޼��带 ���� private ������ ���� ����
        
		System.out.println(studentLee.getStudentName());
	}

}
