package adapter;

public class LegacyFuncAdapterImpl implements LegacyFuncAdapter {

    // Legacy 코드를 활용하기 위한 코드
    private LegacyFunc legacyFunc;

    public LegacyFuncAdapterImpl(LegacyFunc legacyFunc) {
        this.legacyFunc = legacyFunc;
    }

    // 전달된 legacy 처리 결과에 추가적인 작업 수행
    public int convertLegacyDataToNewData(int legacyData) {
        return legacyData * 2;
    }

    @Override
    public int calc(int num) {
//        int legacyData = legacyFunc.calc(num);
//        return convertLegacyDataToNewData(legacyData);

        return convertLegacyDataToNewData(legacyFunc.calc(num));
    }

}
