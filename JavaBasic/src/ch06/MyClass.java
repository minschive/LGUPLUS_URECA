package ch06;

public class MyClass {
	// 1. 생성자가 없을 때
	// final int num; // The blank final field num may not have been initialized
	// 컴파일러가 생성자가 없으므로 기본 생성자를 추가해 줌. 생성자, 선언 시 모두 값을 지정하지 않기 때문에 오류 발생
	
//	final int num = 20;
	
	// 2. 생성자를 통한 초기화
	final int num;
	
	public MyClass(int num) {
		this.num = num;
	}
}

// method 에 final <= 재정의 X
// class 에 final <= 상속 X