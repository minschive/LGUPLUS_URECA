package singleton;

import java.time.LocalDateTime;

// Singleton
public class Logger {
   
   // #3. static 필드로 Logger 변수 필요 - static 메소드에 접근해야 하기 때문에
//   private static Logger logger = new Logger(); // eager loading
   private static Logger logger; // lazy loading
   
   // #1. 생성자를 private 으로 만들어야 함
   private Logger() {}
   
   // #2. 자신 객체를 생성, 전달하는 메소드를 제공
   // 외부에서 Logger 객체 생성 없이 호출 가능
   public static Logger getInstance() {
//      return logger; // eager loading
      
      if(logger == null) {
         logger = new Logger();
      }
      return logger; // lazy loading
   }
   public void log(String message) {
        LocalDateTime ldt = LocalDateTime.now();
        String date = ldt.getYear() + "/" + ldt.getMonthValue() + "/" + ldt.getDayOfMonth();
        String time = ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond();
        
        System.out.println("[" + date + " " + time + "] " + message);
    }
}