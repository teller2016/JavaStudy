package loopexample;

public class DoWhileExample {

	public static void main(String[] args) {
		int num = 1;
		int sum=0;
		
		do {			//조건식이 참이 아니더라도 한번 실행한다
			sum += num;
			num ++;
		}while(num<=10);
		
		System.out.println("1부터 10까지의 합은 "+sum);
	}

}
