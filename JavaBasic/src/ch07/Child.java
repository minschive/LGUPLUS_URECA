package ch07;

public class Child extends Parent {
	public Child() {
//		super(0); // 없으면 컴파일러가 넣어준다.
		
		super(1);
	}
	
	// 부모와 중복된 필드
//	int n = 20;
	
	public void m() {
		System.out.println(n);
		System.out.println(this.n); // 현재 클래스에 n 이 없어도 this. 을 사용하면 
		System.out.println(super.n);
	}
}
