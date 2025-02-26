package ch12;

public class StringPerformanceTest {
    public static void main(String[] args) {
        int loopCount = 100_000; // 반복 횟수

        // 1. String 테스트
        long startTime = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < loopCount; i++) {
            str += "a"; // 새로운 객체가 매번 생성됨 (비효율적)
        }
        long endTime = System.currentTimeMillis();
        System.out.println("String time: " + (endTime - startTime) + " ms");

        // 2. StringBuffer 테스트
        startTime = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < loopCount; i++) {
            stringBuffer.append("a"); // 같은 객체에서 추가 (효율적)
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer time: " + (endTime - startTime) + " ms");

        // 3. StringBuilder 테스트
        startTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < loopCount; i++) {
            stringBuilder.append("a"); // 같은 객체에서 추가 (가장 빠름)
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder time: " + (endTime - startTime) + " ms");
    }
}
