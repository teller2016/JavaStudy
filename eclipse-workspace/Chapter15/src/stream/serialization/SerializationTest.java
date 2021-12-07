package stream.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Person implements Serializable{
	private static final long serialVersionUID = -1503252402544036183L;		//���� ������ ���� ����
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
		Person personAhn = new Person("�����", "��ǥ�̻�");
		Person personKim = new Person("��ö��", "���̻�");
		
		try(FileOutputStream fos = new FileOutputStream("serial.out");
				ObjectOutputStream oos = new ObjectOutputStream(fos)
				){
			oos.writeObject(personAhn);	// person �ν��Ͻ��� ���Ͽ� �� (����ȭ)
			oos.writeObject(personKim);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(FileInputStream fis = new FileInputStream("serial.out");
				ObjectInputStream ois = new ObjectInputStream(fis)
				){
			Person p1 = (Person)ois.readObject(); // person �ν��Ͻ��� ���Ͽ� �о���� (������ȭ)
			Person p2 = (Person)ois.readObject();
			
			System.out.println(p1);
			System.out.println(p2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
