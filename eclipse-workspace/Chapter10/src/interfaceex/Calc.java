package interfaceex;

public interface Calc {
	double PI = 3.14;
	int ERROR = -999999999;
	
	int add(int num1, int num2);
	int substract(int num1, int num2);
	int times(int num1, int num2);
	int divide(int num1, int num2);
	
	default void description() {
		System.out.println("���� ���⸦ ����մϴ�");
		myMethod();		//����Ʈ �޼��忡�� private �޼��� ȣ��
	}
	
	static int total(int[] arr) {
		int total = 0;
		
		for(int i : arr)
			total += i;
		myStaticMethod();		//���� �޼��忡�� private static �޼��� ȣ��
		return total;
	}
	
	private void myMethod() {	//private �޼���
		System.out.println("private �޼����Դϴ�");
	}
	
	private static void myStaticMethod() {	//private static �޼���
		System.out.println("private static �޼����Դϴ�");
	}
}
