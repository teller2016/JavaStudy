package school;

public class Score {
	int studentId;
	Subject subject;
	int point;
	
	public Score(int studentId, Subject subject, int point) {
		super();
		this.studentId = studentId;
		this.subject = subject;
		this.point = point;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "ÇÐ¹ø: " + studentId + ", " + subject.getSubjectName() + ": " + point;
	}
	
}
