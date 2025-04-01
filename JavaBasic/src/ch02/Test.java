package ch02;

public class Test {

	public static void main(String[] args) {
		
		// Primitive type 형 변환
		byte b1 = 10;
		int i1 = b1; // 큰 타입 int <- 작은 타입 byte (자동 형변환)
		System.out.println(b1); // byte value 10
		System.out.println(i1); // int value 10
//		byte b2 = i1; // 작은 타입 byte <- 큰 타입 int (자동 형변환 X)
		
		byte b2 = (byte) i1;
		System.out.println(b2); //작은 타입 byte <- 큰 타입 int (강제 형변환)
		
		byte b3 = (byte) 10000; // 작은 타입 byte <- 큰 타입 int (강제 형변환 중 값 손실)
		System.out.println(b3);
		
		// 산술 연산과정에서의 int 자동 형변환
		byte b4 = 10;
		byte b5 = 20;
//		byte b6 = b4 + b5; // b4, b5가 덧셈 전에 int로 형변환 후 연산 수행 -> 결과도 int ( 컴파일 오류 )
		int i6 = b4 + b5;
		byte b6 = (byte) (b4 + b5);
		
		System.out.println(i6);
		System.out.println(b6);
		
		// String <-> int
		String intStr = "999";
		int intVal = Integer.parseInt(intStr);
		System.out.println(intVal);
		
		String intStr2 = String.valueOf(intVal + 1);
		System.out.println(intStr2);
		
		// LocMem
		LocMem locMem1 = new LocMem();
		locMem1.num = 10;
		locMem1.name = "AAA";
		
		LocMem locMem2 = new LocMem();
		locMem2.num = 20;
		locMem2.name = "BBB";
		
		System.out.println(locMem1.sum(100, 200));
		System.out.println(locMem1.sum(300, 400));
	}

}
