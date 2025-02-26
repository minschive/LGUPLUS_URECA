package ch04;

public class Test {

	public static void main(String[] args) {
		// if else
		{
			int score = 75;
			
			if( score > 90 ) {
				
				if( score > 95 ) {
					
				} else {
					
				}
			} else if( score > 80 ) { // 80 < score <= 90
				
			} else if( score > 70 ) { // 70 <= score <= 80
				
			} else {
				
			}
			
			if( score > 90 ) {
				
			}
			if( score > 80 ) {
				
			}
		}
		
		// switch
		{
//			int score = 10;
//			
//			switch(score) {
//				case 5: System.out.println("A"); break;
//				case 10: System.out.println("B"); //break;
//				case 15: System.out.println("C"); //break;
//				default: System.out.println("D");
//			}
			
			char ch = 'B';
			
			switch(ch) {
				case 'A': System.out.println("A"); break;
				case 'B': System.out.println("B"); break;
				case 'C': System.out.println("C"); break;
//				case 66: System.out.println("66"); break; // Duplicate Case (66은 B의 아스키코드 -> err)
				default: System.out.println("D");
			}
			
			// since java 7 String 지원
//			String str = "22";
//			
//			switch(str) {
//				case "11": System.out.println("11"); break;
//				case "22": System.out.println("22"); break;
//				case "33": System.out.println("33"); break;
//				default: System.out.println("00");
//			}
			
			// since java 12 case 에 복수 개의 value 지정, lambda
//			String str = "33";
//			
//			switch(str) {
//				case "11", "22": System.out.println("11 22"); break;
//				// lambda 는 : 과 함께 사용할 수 없다.
////				case "33"-> {
////					System.out.println("33");
////				}
//				default: System.out.println("00");
//			}
			
			// lambda only
			String str = "44";
			
			switch(str) {
				case "11", "22"-> System.out.println("11 22");
				// lambda 는 : 과 함께 사용할 수 없다.
				case "33"-> {
					System.out.println("추가 작업");
					System.out.println("33");
				}
				default -> System.out.println("00");
			}
		}
		
	}

}
