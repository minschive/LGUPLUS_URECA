package ch16.ex4;

public class Test {

	// Method Reference <- Lambda 간단 표현식
	public static void main(String[] args) {
		Person person = new Person();
		
		// Computer.staticMethod 를 호출 1
		person.action( (x, y) -> {
			return Computer.staticMethod(x, y);
		});
		
		// Computer.staticMethod 를 호출 2 (더 간단)
		person.action( (x, y) -> Computer.staticMethod(x, y) );
		
		// Method Reference (static 일 경우)
		// 파라미터와 그 사용이 명확할 때
		person.action( Computer::staticMethod );
		
		// ----------------------------------------------
		
		// Computer 객체의 instanceMethod 를 호출할 경우 1
		Computer computer = new Computer();
		person.action( (x,y) -> {
			return computer.instanceMethod(x, y);
		} );
		
		// Computer 객체의 instanceMethod 를 호출할 경우 2
		person.action( (x,y) -> computer.instanceMethod(x, y) );
		
		// Method Reference (non-static 일 경우)
		// 파라미터와 그 사용이 명확할 때
		person.action( computer::instanceMethod );
	}

}
