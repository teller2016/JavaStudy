package stream.inputstream;
import java.io.IOException;
public class SystemInTest1 {

	public static void main(String[] args) throws IOException {
		System.out.println("���ĺ� ���� ���� ������");
		
		int i;
		try {
			i = System.in.read(); //read �޼���� �� ����Ʈ ����
			System.out.println(i);
			System.out.println((char)i);
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
