package constructor;

public class PersonTest {

	public static void main(String[] args) {
		Person personKim = new Person();
		personKim.name = "�趯��";	//����Ʈ �����ڷ� Ŭ���� ���� ��
		personKim.weight = 85.5F;	//�ν��Ͻ� ���� ���� ���� �ʱ�ȭ
		personKim.height = 180.0F;
		
		Person personLee = new Person("�̸���", 175, 75);	//�ν��Ͻ� ���� �ʱ�ȭ ���ÿ� Ŭ���� ����
	}

}
