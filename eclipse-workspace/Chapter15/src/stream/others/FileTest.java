package stream.others;

import java.io.File;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\ONEDRIVE\\OneDrive - ���ִ��б�\\Java Study\\eclipse-workspace\\Chapter15\\newFile.txt");// ��ο� File Ŭ���� ����. ���� ���� �����Ȱ� �ƴ�
		file.createNewFile();	//���� ���� ����
		
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		System.out.println(file.getName());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getPath());
		System.out.println(file.canRead());
		System.out.println(file.canWrite());
		
		file.delete();	//���� ����
	}
}
