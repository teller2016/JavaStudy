package innerclass;

import innerclass.OutClass.InClass;

class OutClass {
	private int num = 10;
	private static int sNum = 20;
	
	static class InStaticClass{		//���� ���� Ŭ����
		int inNum = 100;			//���� ���� Ŭ������ �ν��Ͻ� ����
		static int sInNum = 200;	//���� ���� Ŭ������ ���� ����
		
		void inTest(){   //���� Ŭ������ �Ϲ� �޼���
			//num += 10;    // �ܺ� Ŭ������ �ν��Ͻ� ������ ����� �� ����.
			System.out.println("InStaticClass inNum = " + inNum + "(���� Ŭ������ �ν��Ͻ� ���� ���)"); 
			System.out.println("InStaticClass sInNum = " + sInNum + "(���� Ŭ������ ����ƽ ���� ���)");
			System.out.println("OutClass sNum = " + sNum + "(�ܺ� Ŭ������ ����ƽ ���� ���)");
		}
		
		static void sTest(){  // ���� Ŭ������ static �޼���
			//num += 10;   // �ܺ� Ŭ������ �ν��Ͻ� ������ ����� �� ����.
			//inNum += 10; // ���� Ŭ������ �ν��Ͻ� ������ ����� �� ����
			
			System.out.println("OutClass sNum = " + sNum + "(�ܺ� Ŭ������ ����ƽ ���� ���)");
			System.out.println("InStaticClass sInNum = " + sInNum + "(���� Ŭ������ ����ƽ ���� ���)");
		}
	}
	
	private InClass inClass;
	
	public OutClass() {
		inClass = new InClass();
	}
	
	class InClass{		//�ν��Ͻ� ���� Ŭ����
		int inNum = 100;
		//static int sInNum = 200;	//�ν��Ͻ� ���� Ŭ������ ���� ���� ���� �Ұ���
		
		void inTest() {
			System.out.println("OutClass num = "+num+"(�ܺ� Ŭ������ �ν��Ͻ� ����");
			System.out.println("OutClass snum = "+sNum+"(�ܺ� Ŭ������ ���� ����");
		}
		
		//static void sTest(){} 	// ���� �޼��� ���� ���� �Ұ���
	}
	public void usingClass() {
		inClass.inTest();
	}

}
public class InnerTest {

	public static void main(String[] args) {
		OutClass outClass = new OutClass();
		System.out.println("�ܺ� Ŭ���� �̿��Ͽ� ���� Ŭ���� ��� ȣ��");
		outClass.usingClass();
		
		//�ܺ� Ŭ���� �������� �ʰ� �ٷ� ���� ���� Ŭ���� ����
		OutClass.InStaticClass sInClass = new OutClass.InStaticClass();  
		System.out.println("���� ���� Ŭ���� �Ϲ� �޼��� ȣ��");
		sInClass.inTest();
		System.out.println();
				
		System.out.println("���� ���� Ŭ������ ����ƽ �޼ҵ� ȣ��");
		OutClass.InStaticClass.sTest();
	}
}
