package ch08;

public interface YourIF {
	int MAX = 10; // 상수
	void m2();
	
	// default method
	default void m3() {
		System.out.println(MAX);
	}
}
