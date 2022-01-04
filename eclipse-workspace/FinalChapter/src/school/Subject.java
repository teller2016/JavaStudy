package school;

import java.util.ArrayList;
import utils.Define;		//프로그램 전반에서 사용하는 상수 클래스

public class Subject {
	private String subjectName;	//과목 이름
	private int subjectId;		//과목 고유 번호
	private int gradeType;		//학점 평가 정책
	
	private ArrayList<Student> studentList = new ArrayList<Student>();	//이 과목을 수강 신청한 학생 리스트, register()를 통해 학생 추가

	public Subject(String subjectName, int subjectId, int gradeType) {
		this.subjectName = subjectName;
		this.subjectId = subjectId;
		this.gradeType = gradeType;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getGradeType() {
		return gradeType;
	}

	public void setGradeType(int gradeType) {
		this.gradeType = gradeType;
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}
	
	public void register(Student student) {		//수강 신청 메서드
		studentList.add(student);
	}
}
