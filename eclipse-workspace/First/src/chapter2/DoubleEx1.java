package chapter2;

public class DoubleEx1 {

	public static void main(String[] args) {
		double dnum = 3.14;
		float fnum = 3.14F; // float의 경우 식별자 F,f를 붙여야 된다
		
		System.out.println(dnum);
		System.out.println(fnum);
		
		double num = 1;
		for(int i=0; i<10000; i++){
		    num = num + 0.1;
		}
		System.out.println(num);	//1001.0000000000159 결과가 나온다
	}
}
