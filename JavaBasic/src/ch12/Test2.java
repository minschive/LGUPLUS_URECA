package ch12;

import java.lang.annotation.*;
import java.lang.reflect.Method;

public class Test2 {
    
    @Retention(RetentionPolicy.RUNTIME) 
    @Target(ElementType.METHOD)
    public @interface Marker {}

    @Marker
    public void myMethod() {
        System.out.println("Hello, Marker Annotation!");
    }

    public static void main(String[] args) {
        Test obj = new Test();

        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Marker.class)) {
                System.out.println(method.getName() + " 메소드에 @Marker 어노테이션 적용");
            }
        }
    }
}

