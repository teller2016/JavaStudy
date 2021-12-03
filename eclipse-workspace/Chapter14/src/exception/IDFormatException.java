package exception;

public class IDFormatException extends Exception {
	public IDFormatException(String message) {	//생성자 매개변수로 예외 상황 메시지 받음
		super(message);
	}
}
