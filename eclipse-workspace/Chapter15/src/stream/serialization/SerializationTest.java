package stream.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Person implements Serializable{
	private static final long serialVersionUID = -1503252402544036183L;		//버전 관리를 위한 정보
	String name;
	String job;
	
	public Person() {}

	public Person(String name, String job) {
		this.name = name;
		this.job = job;
	}

	@Override
	public String toString() {
		return name +", "+job;
	}
	
	
}

public class SerializationTest {

	public static void main(String[] args) throws ClassNotFoundException {
		Person personAhn = new Person("안재용", "대표이사");
		Person personKim = new Person("김철수", "상무이사");
		
		try(FileOutputStream fos = new FileOutputStream("serial.out");
				ObjectOutputStream oos = new ObjectOutputStream(fos)
				){
			oos.writeObject(personAhn);	// person 인스턴스를 파일에 씀 (직렬화)
			oos.writeObject(personKim);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(FileInputStream fis = new FileInputStream("serial.out");
				ObjectInputStream ois = new ObjectInputStream(fis)
				){
			Person p1 = (Person)ois.readObject(); // person 인스턴스를 파일에 읽어들임 (역직렬화)
			Person p2 = (Person)ois.readObject();
			
			System.out.println(p1);
			System.out.println(p2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
