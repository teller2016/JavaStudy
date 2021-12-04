package stream.inputstream;
import java.io.IOException;
public class SystemInTest1 {

	public static void main(String[] args) throws IOException {
		System.out.println("알파벳 쓰고 엔터 눌러라");
		
		int i;
		try {
			i = System.in.read(); //read 메서드로 한 바이트 읽음
			System.out.println(i);
			System.out.println((char)i);
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
