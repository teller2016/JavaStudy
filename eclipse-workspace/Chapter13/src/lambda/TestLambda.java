package lambda;

interface PrintString{
	void showString(String str);
}

public class TestLambda {

	public static void main(String[] args) {

		PrintString lambdaStr = s->System.out.println(s);  //���ٽ��� ������ ����
		lambdaStr.showString("hello lambda_1");
		
		showMyString(lambdaStr);                          //�޼��� �Ű������� ����
		
		PrintString reStr = returnString();
		reStr.showString("hello ");
	}
	
	public static void showMyString(PrintString p) {	// �Ű������� �������̽������� ����
		p.showString("hello lambda_2");
	}
	
	public static PrintString returnString() {		// ���ٽ� ��ȯ
		return s->System.out.println(s+"world");
	}
}