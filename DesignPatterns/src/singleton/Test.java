package singleton;

public class Test {

   public static void main(String[] args) {
      // logger 객체가 단 한개만 만들어져야 한다.
      // 아래의 코드는 별개의 객체가 만들어 진다.
//      Logger logger1 = new Logger();
//      Logger logger2 = new Logger();
      
      Logger logger1 = Logger.getInstance();
      logger1.log("my message");
      
      Logger logger2 = Logger.getInstance();
      logger2.log("my message 2");
      
      System.out.println(logger1 == logger2);

   }

}