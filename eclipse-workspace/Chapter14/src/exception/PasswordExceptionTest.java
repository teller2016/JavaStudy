package exception;

public class PasswordExceptionTest {
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws PasswordException {
		if(password.matches("[a-zA-Z]+"))
			throw new PasswordException("비번은 문자열로만 이루어질 수없다");
		else if(password == null)
			throw new PasswordException("비번은 null일 수 없습니다");
		else if(password.length() < 5)
			throw new PasswordException("비번은 5자 이상해야됨");
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
