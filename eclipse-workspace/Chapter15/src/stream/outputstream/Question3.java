package stream.outputstream;

import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Question3 {

	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream("a.txt", true);
		OutputStreamWriter sw = new OutputStreamWriter(fos);
		try(fos;sw){
			sw.write("자바 공부 재밌어요");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
