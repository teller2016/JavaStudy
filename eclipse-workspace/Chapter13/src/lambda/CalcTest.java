package lambda;

public class CalcTest {

	public static void main(String[] args) {
		Calc cal = (x,y) -> x+y;
		System.out.println(cal.add(2,4));
	}

}
