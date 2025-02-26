package ch04;

public class Test2 {

	public static void main(String[] args) {
		// for ( 반복횟수가 고정되어 있을 때 일반적으로 사용 )
//		for(int i = 0; i < 10; i++) {
//			//
//			System.out.print(i);
//		}
//		
//		for (int i = 0, j = 2; i < 10 && j > 0; i++, j--) {
//			System.out.print(i);
//			System.out.print(j);
//			System.out.println();
//		}

		// nested
		for (int i = 0; i < 4; i++) { 
			for (int j = 0; j < 6; j++) {
				System.out.printf("%d %d  ", i, j); 
			}
			System.out.println();
		}
		
		// foreach 는 배열, Collection 수업에서 배움
	}

}
