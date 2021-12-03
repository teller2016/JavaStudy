package exception;

public class PasswordExceptionTest {
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws PasswordException {
		if(password.matches("[a-zA-Z]+"))
			throw new PasswordException("����� ���ڿ��θ� �̷���� ������");
		else if(password == null)
			throw new PasswordException("����� null�� �� �����ϴ�");
		else if(password.length() < 5)
			throw new PasswordException("����� 5�� �̻��ؾߵ�");
		this.password = password;
	}


	public static void main(String[] args) {
		PasswordExceptionTest test = new PasswordExceptionTest();
		
		String userPass = "asdf";
		try {
			test.setPassword(userPass);
		}catch(PasswordException e) {
			System.out.println(e.getMessage());
		}
	}

}
