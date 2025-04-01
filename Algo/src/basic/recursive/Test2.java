package basic.recursive;

public class Test2 {

	// factorial 계산을 재귀호출을 이용해서 처리, 결과값 처리 다양하게
	public static void main(String[] args) {
		factorial(5);
		
		int result = factorial2(5);
		System.out.println(result);

		factorial3(5, 1); // 두번째 파라미터 값에 누적 곱으로 처리
	}
	
	// #1. 결과값을 static 변수
	static int result = 1;
	static void factorial(int n) {
		// 기저 조건
		if(n == 1) {
			System.out.println(result); // 원하는 결과가 완성된 시점
			return;
		}
		
		// 계산
		result = result * n;
		
		// 재귀호출
		factorial(n - 1);
	}
	
	// #2. 결과값을 재귀호출의 return 값으로
	// 바닥부터 계산되어서 bottom up 으로 처리
	static int factorial2(int n) {
		// 기저 조건
		if(n == 1) return 1;
		
		// n == 3 일 때,
		// 2 x 1 의 결과에 자신 3을 곱해서 4에게 리턴
		return n * factorial2(n-1);
	}
	
	// #3. 결과값을 재귀호출의 parameter 값으로
	static void factorial3(int n, int result) {
		// 기저 조건
		if(n == 1) {
			// result 에 이전 계산 값이 존재
			System.out.println(result);
			return;
		}
		
		factorial3(n-1, result * n); // result * n : 이전 계산값 * 자기자신
	}

}
