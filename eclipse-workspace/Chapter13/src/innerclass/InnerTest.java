package innerclass;

import innerclass.OutClass.InClass;

class OutClass {
	private int num = 10;
	private static int sNum = 20;
	
	static class InStaticClass{		//정적 내부 클래스
		int inNum = 100;			//정적 내부 클래스의 인스턴스 변수
		static int sInNum = 200;	//정적 내부 클래스의 정적 변수
		
		void inTest(){   //정적 클래스의 일반 메서드
			//num += 10;    // 외부 클래스의 인스턴스 변수는 사용할 수 없음.
			System.out.println("InStaticClass inNum = " + inNum + "(내부 클래스의 인스턴스 변수 사용)"); 
			System.out.println("InStaticClass sInNum = " + sInNum + "(내부 클래스의 스태틱 변수 사용)");
			System.out.println("OutClass sNum = " + sNum + "(외부 클래스의 스태틱 변수 사용)");
		}
		
		static void sTest(){  // 정적 클래스의 static 메서드
			//num += 10;   // 외부 클래스의 인스턴스 변수는 사용할 수 없음.
			//inNum += 10; // 내부 클래스의 인스턴스 변수는 사용할 수 없음
			
			System.out.println("OutClass sNum = " + sNum + "(외부 클래스의 스태틱 변수 사용)");
			System.out.println("InStaticClass sInNum = " + sInNum + "(내부 클래스의 스태틱 변수 사용)");
		}
	}
	
	private InClass inClass;
	
	public OutClass() {
		inClass = new InClass();
	}
	
	class InClass{		//인스턴스 내부 클래스
		int inNum = 100;
		//static int sInNum = 200;	//인스턴스 내부 클래스에 정적 변수 선언 불가능
		
		void inTest() {
			System.out.println("OutClass num = "+num+"(외부 클래스의 인스턴스 변수");
			System.out.println("OutClass snum = "+sNum+"(외부 클래스의 정적 변수");
		}
		
		//static void sTest(){} 	// 정적 메서드 또한 정의 불가능
	}
	public void usingClass() {
		inClass.inTest();
	}

}
public class InnerTest {

	public static void main(String[] args) {
		OutClass outClass = new OutClass();
		System.out.println("외부 클래스 이용하여 내부 클래스 기능 호출");
		outClass.usingClass();
		
		//외부 클래스 생성하지 않고 바로 정적 내부 클래스 생성
		OutClass.InStaticClass sInClass = new OutClass.InStaticClass();  
		System.out.println("정적 내부 클래스 일반 메서드 호출");
		sInClass.inTest();
		System.out.println();
				
		System.out.println("정적 내부 클래스의 스태틱 메소드 호출");
		OutClass.InStaticClass.sTest();
	}
}
