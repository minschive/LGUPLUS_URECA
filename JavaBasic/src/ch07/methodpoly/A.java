package ch07.methodpoly;

public class A {
	void a() { System.out.println("A - a()"); }
}

class B extends A { // 메소드 개수 : 3개
	void a(int i) { System.out.println("B - a(int i)"); }
	void b() { System.out.println("B - b()"); }
}

class C extends B { // 메소드 개수 : 4개
	void a() { System.out.println("C - a()"); } // 부모 메소드 재정의
	void c() { System.out.println("C - c()"); }
}

class D extends C { // 메소드 개수 : 5개
	void a(int i) { System.out.println("D - a(int i)"); } // 부모 메소드 재정의
	void b() { System.out.println("D - b()"); } // 부모 메소드 재정의
	void d() { System.out.println("D - d()"); }
}
