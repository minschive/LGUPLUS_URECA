package ch18.ex5;

import java.io.Serializable;

// Serializable 는 추상 메소드가 없는 단순 Marking 용도
public class MyClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int n;
	String str;
	
	// transient
	transient String ssn;
	
	// serialVersionUID 가 동일하면 추가된 필드가 있어도 역직렬화가 가능하다.
	int n2;
	
	// serialVersionUID 가 직렬화 시점의 값과 다르면 역직렬화 불가능
}
