package singleton;

public class Company {
	private static Company instance = new Company();	//�����ϰ� ������ �ν��Ͻ�
	private Company() {	}
	
	public static Company getInstance() {
		if(instance == null) {
			instance = new Company();
		}
		return instance;
	}
}
