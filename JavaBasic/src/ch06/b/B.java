package ch06.b;

import ch06.a.A;

// 상속 관계에서 protected 에 접근 가능하다는 것은 같은 객체 내에서만 허용 가능
public class B extends A {
	
//	A aa = new A();
	
	public void m() {
		A a = new A();
//		int x = a.n4; // 접근 X (이유 : 다른 객체)
	}
	
	public void m2() {
		int x = super.n4;
	}
}
