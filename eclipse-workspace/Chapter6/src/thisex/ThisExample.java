package thisex;

class BirthDay{
	int day;
	int month;
	int year;
	
	public void setYear(int year) {
		this.year = year;			//bDay.year = year;�� ����
	}
	public void printThis() {
		System.out.println(this);	//System.out.println(bDay);�� ����
	}
}

public class ThisExample {

	public static void main(String[] args) {
		BirthDay bDay = new BirthDay();
		bDay.setYear(2000);
		System.out.println(bDay);
		bDay.printThis();
	}

}
