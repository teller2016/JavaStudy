package exception;

public class AutoCloseObj implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println("���ҽ��� close() �Ǿ����ϴ�");
	}

}
