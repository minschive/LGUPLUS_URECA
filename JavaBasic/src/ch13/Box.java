package ch13;

// 한 개의 타입이 아닌 여러 타입 (String, Integer, ...) 의 객체를 담고자 한다.
//public class Box {
//	// 아래 2개는 한 가지 타입만 담을 수 있다.
//	public Integer content;
//	public String content;
//	
//	// Object는 모든 타입을 담을 수 있지만, 실제 사용할 때 instanceof 로 객체가 어떤 타입인지 확인, 형 변환도 해야 한다.
//	public Object content;
//}


public class Box<T> {
	public T content;
}