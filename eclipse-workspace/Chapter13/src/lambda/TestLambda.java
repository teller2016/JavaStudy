package lambda;

interface PrintString{
	void showString(String str);
}

public class TestLambda {

	public static void main(String[] args) {

		PrintString lambdaStr = s->System.out.println(s);  //람다식을 변수에 대입
		lambdaStr.showString("hello lambda_1");
		
		showMyString(lambdaStr);                          //메서드 매개변수로 전달
		
		PrintString reStr = returnString();
		reStr.showString("hello ");
	}
	
	public static void showMyString(PrintString p) {	// 매개변수를 인터페이스형으로 받음
		p.showString("hello lambda_2");
	}
	
	public static PrintString returnString() {		// 람다식 반환
		return s->System.out.println(s+"world");
	}
}