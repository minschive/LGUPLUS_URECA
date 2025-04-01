package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

// Spring 입장 MyClass 가 사용한 annotation (미리 약속된) 을 파악
public class Test {

	public static void main(String[] args) throws Exception {
		
		// AboutMe
		Class<?> myClass = Class.forName("annotation.MyClass");
		
		Annotation[] annotations = myClass.getAnnotations();
		// AboutMe annotation 의 속성값을 확인/처리
		for (Annotation annotation : annotations) {
			if( annotation instanceof AboutMe ) {
				AboutMe aboutMe = (AboutMe) annotation;
				// AboutMe annotation 대응 코드
				System.out.println(aboutMe.love());
				System.out.println(aboutMe.hate());
			}
		}
		
		// Encrypt
		User user = new User("홍길동", "1234");
		System.out.println(user);
		
		// @Encrypt 를 사용한 필드가 있으면 필드값을 암호화 변경
		Field[] fields = user.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			if( field.isAnnotationPresent(Encrypt.class) ) {
				field.setAccessible(true); // private 도 가능
				field.set(user, field.get(user) + "5678");
			}
		}

		System.out.println(user);
	}

}
