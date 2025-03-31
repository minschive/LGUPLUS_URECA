package proxy;

// Spring 과 약속된 @CheckNotNull 을 이용해서 
// Spring 에게 해당 메소드가 호출될 때 확인 프로세스 의뢰
public class MyIFImpl implements MyIF{
    @Override
    
    public void m(String param1, String param2) {
        System.out.println("m()");
        System.out.println(param1 + ", " + param2);
    }
    
    @Override
    public void m2(String param1, String param2) {
        System.out.println("m2()");
        System.out.println(param1 + ", " + param2);
    }
    
    @CheckNotNull(parameterNames= {"param1", "param2"})
    @Override
    public void m3(String param1, String param2) {
        System.out.println("m3()");
        System.out.println(param1 + ", " + param2);
    }
    
    @Override
    public void m4(String param1, String param2) {
        System.out.println("m4()");
        System.out.println(param1 + ", " + param2);
    }
}