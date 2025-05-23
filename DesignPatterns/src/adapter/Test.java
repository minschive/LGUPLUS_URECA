package adapter;

// input -> LegacyFunc -> result
// input -> LegacyFunc -> LegacyFuncAdapter -> result
public class Test {
    public static void main(String[] args) {
        // LegacyFunc
        LegacyFunc legacyFunc = new LegacyFuncImpl();
        System.out.println(legacyFunc.calc(10));

        // 새로운 function 을 위 LegacyFunc 를 받아서 변형하는 Adapter 객체
        LegacyFuncAdapter legacyFuncAdapter = new LegacyFuncAdapterImpl(legacyFunc);
        System.out.println(legacyFuncAdapter.calc(10));

    }
}
