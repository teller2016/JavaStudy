package exception;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class ThrowsException {
	
	public Class loadClass(String fileName, String className) throws FileNotFoundException, ClassNotFoundException{
		// 두 예외를 메서드가 호출될 때 처리하도록 미룸
		FileInputStream fis = new FileInputStream(fileName);	//FileNotFoundException 발생 가능
		Class c = Class.forName(className);	//ClassNotFoundException 발생 가능
		return c;
	}
	
	public static void main(String[] args) {
		ThrowsException test = new ThrowsException();
		try {
			test.loadClass("a.txt", "java.lang.String");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(Exception e) {		//Exception 클래스로 그 외 예외 상황 처리
			e.printStackTrace();
		}
	}

}
