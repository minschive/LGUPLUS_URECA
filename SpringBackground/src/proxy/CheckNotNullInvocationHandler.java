package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CheckNotNullInvocationHandler implements InvocationHandler{
    private Object target; // myIF
    
    public CheckNotNullInvocationHandler(Object target) {
        this.target = target;
    }
    
    // proxy 를 통해서 MyIF 에 있는 메소드를 호출할 때 마다, invoke() 대신 호출된다.
    // args[] proxy 를 통해서 호출되는 메소드의 파라미터 값들
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // proxy 가 할 일이 있으면 하고나서 필요에 따라 실제 메소드를 호출하고 
        // 특별한 할 일이 없으면 그냥 bypass (실제 메소드를 호출)
        Method targetMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        
        // 할 일? @CheckNotNull 관련 처리
        if( targetMethod.isAnnotationPresent(CheckNotNull.class) ) {
            System.out.println("CheckNotNull method call!!");
            return handleCheckNotNull(targetMethod, args);
        }
        return method.invoke(target, args);
    }
    
    private Object handleCheckNotNull(Method method, Object[] args ) throws Throwable{
        
        CheckNotNull annotation = method.getAnnotation(CheckNotNull.class);
        String[] parameterNames = annotation.parameterNames();
        
        for (int i = 0; i < args.length; i++) {
            if( args[i] == null ) {
                throw new IllegalArgumentException("Parameter " + parameterNames[i] + " is null (should be notnull)");
            }
        }
        
        return method.invoke(target, args);
    }
}