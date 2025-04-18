package ch16.ex1;

public class Test {

	// 매개변수가 없는 람다식
	public static void main(String[] args) {
		Person person = new Person(); // 일하는 사람을 만들고
		
		// 그 사람에게 할 일을 부여
		// 일의 종류마다 구현 클래스 및 개체를 만들지 않고
		// 일 각각을 람다식으로 표현하여 전달
		person.action(() -> {
			System.out.println("출근!");
			System.out.println("코딩!");
			System.out.println("칼퇴!");
		});
		
		person.action(() -> {
			System.out.println("칼퇴!");
		});
		
		// 실행은 한 개일 경우 () 생략 가능
		person.action( () -> System.out.println("칼퇴!") );

	}

}
