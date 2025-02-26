package ch06.a;

public class Test {

	public static void main(String[] args) {
		// 같은 패키지에서 default 에 대한 접근
		A a = new A(); // import 필요 X -> 같은 패키지이므로
		int x = a.n3;

	}

}
