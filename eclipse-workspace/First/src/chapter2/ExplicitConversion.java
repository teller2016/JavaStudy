package chapter2;

public class ExplicitConversion {

	public static void main(String[] args) {
		double dNum1 = 1.2;
		float fNum2 = 0.9F;
		
		int iNum3 = (int)dNum1 + (int)fNum2;
		int iNum4 = (int)(dNum1 + fNum2);
		System.out.println(iNum3);	//1 출력
		System.out.println(iNum4);	//2 출력
		
		//double a = 3.14;
		//int i = a;
		//System.out.println(i);
	}

}
