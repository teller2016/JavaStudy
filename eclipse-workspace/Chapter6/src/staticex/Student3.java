package staticex;

public class Student3 {
	private static int serialNum = 1000;
	public String studentName;
	public int cardNum;
	public int studentNum;
	Student3(String studentName){
		this.studentName = studentName;
		this.studentNum = serialNum;
		this.cardNum = this.studentNum + 100;
		serialNum++;
	}
	
	public int getStudentNum() {
		return studentNum;
	}
	
}
