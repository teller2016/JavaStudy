package loopexample;

public class DoWhileExample {

	public static void main(String[] args) {
		int num = 1;
		int sum=0;
		
		do {			//���ǽ��� ���� �ƴϴ��� �ѹ� �����Ѵ�
			sum += num;
			num ++;
		}while(num<=10);
		
		System.out.println("1���� 10������ ���� "+sum);
	}

}
