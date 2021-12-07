package stream.others;

import java.io.File;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\ONEDRIVE\\OneDrive - 아주대학교\\Java Study\\eclipse-workspace\\Chapter15\\newFile.txt");// 경로에 File 클래스 생성. 실제 파일 생성된거 아님
		file.createNewFile();	//실제 파일 생성
		
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		System.out.println(file.getName());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getPath());
		System.out.println(file.canRead());
		System.out.println(file.canWrite());
		
		file.delete();	//파일 삭제
	}
}
