package thisex;

class Person{
	String name;
	int age;
	
	Person(){
		this("�̸� ����", 1);
	}
	
	Person returnItSelf(){
        return this;
    }
	
	Person(String name, int age){
		this.name = name;
		this.age = age;
	}
}

public class CallAnotherConst {

	public static void main(String[] args) {
		Person noName = new Person();
		System.out.println(noName.name);
		System.out.println(noName.age);
		
		Person p = noName.returnItSelf();
		System.out.println(p);
		System.out.println(noName);

	}

}
