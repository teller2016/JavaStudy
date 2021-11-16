package ifexample;

public class IfExample2 {

	public static void main(String[] args) {
		int age = 9;
		int charge;
		
		if(age<8) {
			charge = 1000;
			System.out.println("취학 전 아동");
		}
		else if(age < 14) {
			charge = 2000;
			System.out.println("초등학생");
		}
		else {
			charge = 3000;
			System.out.println("일반인");
		}
		System.out.println("입장료는 "+ charge + "입니다");
	}

}
