package stream.decorator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyTest {

	public static void main(String[] args) {
		long millisecond = 0;
		try(
				FileInputStream fis = new FileInputStream("a.zip");
				FileOutputStream fos = new FileOutputStream("copy.zip")
				){
			millisecond = System.currentTimeMillis();	//���� ���縦 �����ϱ��� �ð�
			int i;
			while((i = fis.read()) != -1)
				fos.write(i);
			
			millisecond = System.currentTimeMillis() - millisecond;	//������ �����ϴµ� �ɸ��� �ð�
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("���� �����ϴµ� �ɸ� �ð�: "+millisecond);
	}

}
