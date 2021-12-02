package innerclass;

class Outer2{
	
	Runnable getRunnable(int i){
		int num = 100;
		
		//MyRunnable Ŭ���� �̸� ���� Ŭ���� �ٷ� ����
		return new Runnable() {		//�͸� ���� Ŭ����. Runnable �������̽� ����
				
		@Override
		public void run() {
			//num = 200;   //���� ��
			//i = 10;      //���� ��
			System.out.println(i);
			System.out.println(num);
			}
		};	//Ŭ���� ���� ; ��
	}
	
	//�������̽��� �߻� Ŭ������ ������ �����ϰ� Ŭ������ ������ �����ϴ� ���
	Runnable runner = new Runnable() {	//�͸� ���� Ŭ������ ������ ����
		@Override
		public void run() {
			System.out.println("Runnable �� ������ �͸� Ŭ���� ����");
		}
	};//Ŭ���� ���� ; ��
}

public class AnonymousInnerTest {

	public static void main(String[] args) {
		Outer2 out = new Outer2();
		Runnable runnerble = out.getRunnable(10);
		runnerble.run();
		out.runner.run();
	}

}
