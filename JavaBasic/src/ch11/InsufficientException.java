package ch11;

// 사용자 정의 예외
// extends RuntimeException 일 경우 컴파일 오류 발생 X
public class InsufficientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionID = -1L;
	
//	public InsufficientException() {} // 생략 가능
	
	public InsufficientException(String message) {
		super(message); // 반드시 호출이 되어야 함
	}
}
