package stream.serialization;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

class Dog implements Externalizable{
	String name;
	
	public Dog() {}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {	//Externalizable 인터페이스의 메서드 구현
		out.writeUTF(name);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name = in.readUTF();
	}

	@Override
	public String toString() {
		return name;
	}
}

public class ExternalizableTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Dog myDog = new Dog();
		myDog.name = "몰리";
		
		FileOutputStream fos = new FileOutputStream("external.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		try(fos; oos){
			oos.writeObject(myDog);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileInputStream fis = new FileInputStream("external.out");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Dog dog = (Dog)ois.readObject();
		System.out.println(dog);
	}

}
