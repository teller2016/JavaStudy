package question;
import java.util.HashSet;

class Student{
	private int studentId;
	private String studentName;
	
	public Student(int studentId, String studentName) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
	}

	@Override
	public int hashCode() {
		return studentId;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student student = (Student)obj;
			if(this.studentId == student.studentId)
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public String toString() {
		return this.studentId +":"+this.studentName;
	}
	
}

public class StudentTest {
	public static void main(String[] args) {
		HashSet<Student> set = new HashSet<Student>();
		set.add(new Student(100, "ȫ�浿"));
		set.add(new Student(200, "����"));
		set.add(new Student(300, "�ٶ�"));
		set.add(new Student(400, "����"));
		set.add(new Student(100, "���"));
		
		System.out.println(set);
	}

}
