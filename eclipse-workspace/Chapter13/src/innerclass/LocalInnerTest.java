package innerclass;

class Outer{
	
	int outNum = 100;
	static int sNum = 200;
	
	Runnable getRunnable(int i){
		int num = 100;
		
		class MyRunnable implements Runnable{
			int localNum = 10;
				
			@Override
			public void run() {
				//num = 200;   //���� ��. ���������� ����� �ٲ�
				//i = 100;     //���� ��. �Ű� ���� ���� ��������ó�� ����� �ٲ�
				System.out.println("i =" + i); 
				System.out.println("num = " +num);  
				System.out.println("localNum = " +localNum);
					
				System.out.println("outNum = " + outNum + "(�ܺ� Ŭ���� �ν��Ͻ� ����)");
				System.out.println("Outter.sNum = " + Outer.sNum + "(�ܺ� Ŭ���� ���� ����)");
				}
			}
		 return new MyRunnable();
	}
}

public class LocalInnerTest {

	public static void main(String[] args) {
		Outer out = new Outer();
		Runnable runner = out.getRunnable(10);
		runner.run();
	}

}
