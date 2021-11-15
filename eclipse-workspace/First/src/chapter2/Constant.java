package chapter2;

public class Constant {
	public static void main(String [] arg) {
		final int MAX_NUM = 100;
		final int MIN_NUM;
		
		MIN_NUM = 0;	//사용하기 전에 초기화는 가능, 초기화 안하면 오류 발생
		
		System.out.println(MAX_NUM);
		System.out.println(MIN_NUM);
		
		//MAX_NU = 1000;	// 오류, 상수값은 변경 불가능
	}
}
