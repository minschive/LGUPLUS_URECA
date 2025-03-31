package proxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// interface 앞에 @ 붙여서 annotation 생성
// 어디에 사용할 것인가? TYPE, METHOD.. api 문서 확인
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckNotNull {
    // 속성이 복수개..
    String[] parameterNames();
}
// @CheckNotNull