package ch16.ex2;

public class Test {

	// 매개변수가 있는 람다식
	public static void main(String[] args) {
		Person person = new Person(); // 일하는 사람을 만들고
		
		// 그 사람에게 할 일을 부여, 말하도록 한다.
		person.action((name, job) -> {
			System.out.println(name + "이 " + job + "을 수행합니다.");
		});
		
		person.action((name, job) -> {
			System.out.println(name + "이 " + job + "을 수행하지 않습니다.");
		});
		
		// 파라미터가 한 개인 경우 () 생략 가능
		person.action2( (content) -> System.out.println(content + " 라고 말했다.") );
		
		person.action2( content -> System.out.println(content + " 라고 말하지 않았다.") );


	}

}
