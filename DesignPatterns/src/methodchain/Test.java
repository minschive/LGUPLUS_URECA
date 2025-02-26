package methodchain;

public class Test {

   public static void main(String[] args) {
      // StringBuilder append()
//      StringBuilder sb = new StringBuilder();
//      sb.append("abc");
//      sb.append("def");
//      
//      sb.append("ghi").append("jki"); // . 이어서 . 이어서 ...
//      System.out.println(sb);
      
      // Calculator - pattern 적용 전
//      Calculator calc = new Calculator();
//      
//      // 3 + 5
//      calc.setFirst(3);
//      calc.setSecond(5);
//      calc.add();
//      
//      // 3 - 1
//      calc.setSecond(1);
//      calc.sub();
      
      // Calculator - pattern 적용 후
      Calculator calc = new Calculator();
      calc.setFirst(3).setSecond(5).add();
      // pattern 적용 전이면 setFirst(3)의 리턴이 void 이므로 ".setSecond(5)" 에서 err

   }

}
