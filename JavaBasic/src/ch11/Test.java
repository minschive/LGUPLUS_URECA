package ch11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

// 자바는 기본적으로 돌다리도 두드려보고 건너라 개념
// 생성자나 메소드를 만들 때 발생할 수 있는 문제를 예외로 만들고, 그 상황에 대한 대응책도 미리 만들어 둔다.
public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		// Error vs Exception
//		{
////			m();
//			
//			m2();
//			
//			m3();
//			
//			m4(); // 대응책이 없다. -> err
//		}
		
		// 대응 코드
		{
			// #1. 내가 직접 처리 try-catch로 처리된 m3() 호출
			m3();
//			
//			try {
//				m4();
//			} catch(FileNotFoundException e) {
//				System.out.println("예외 처리");
//			} finally {
//				System.out.println("항상 수행");
//			}
			
			// #3. RuntimeException 처리
			// throws 가 없어도 자동으로 위로 toss
			// try-catch 적용 가능
			// but 그게 맞는 건가 ??? <= 결론 :  좋지 않다 !
			m2();
		}

	}
	
	// Exception in thread "main" java.lang.StackOverflowError
	static void m() {
		m(); // 재귀 호출
	}

	// Exception in thread "main" java.lang.NullPointerException
	// Compile Error 발생 X
	static void m2() {
		String str = null;
		// #3. RuntimeException 처리
		try {
			System.out.println(str.length());	
		} catch(NullPointerException e) {
			System.out.println("NullPointerException 발생");
		}
	}
	
	// Unhandled exception type FileNotFoundException
	// Compile Error 발생 O
//	static void m3() {
//		// throws FileNotFoundException 로 만들어진 메소드를 호출할 때 만약 파일이 없으면 어떻게 다룸 ?
//		// 대응책이 없으면 오류 발생 
//		FileInputStream fis = new FileInputStream("readme.md");
//	}
	
	// 대응코드를 포함한 코드
	static void m3() throws FileNotFoundException {
		// throws FileNotFoundException 로 만들어진 메소드를 호출할 때 만약 파일이 없으면 어떻게 다룸 ?
		// 대응책이 없으면 오류 발생
		// #1. 내가 직접 처리
//		try {
//			FileInputStream fis = new FileInputStream("readme.txt");
//			System.out.println("정상 수행");
//		} catch(FileNotFoundException e) {
//			System.out.println("파일이 없으면 이렇게 한다.");
//		} finally { 
//			System.out.println("항상 수행한다.");
//		}
		
		// #2. 회피 : 나를 호출한 곳으로 toss
		// 메소드 선언부에 throws 처리
		FileInputStream fis = new FileInputStream("readme.txt");
	}
	
	static void m4() throws FileNotFoundException {
		System.out.println("m4()");
	}
}
