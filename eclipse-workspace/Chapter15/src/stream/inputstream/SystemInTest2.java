package stream.inputstream;
import java.io.IOException;
public class SystemInTest2 {

	public static void main(String[] args) throws IOException {
		System.out.println("���ĺ� ������ ���� ���� ������");
		
		int i;
		try {
			while((i = System.in.read()) != '\n') {
				System.out.print((char)i);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
