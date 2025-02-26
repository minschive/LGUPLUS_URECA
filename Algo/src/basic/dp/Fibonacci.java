package basic.dp;

import java.util.Iterator;

public class Fibonacci {

	public static void main(String[] args) {
		// #1. 단순 재귀 호출
//		System.out.println( fibo_rc(50) ); // 오래  걸린다.
		
		
		// #2. 메모이제이션 ( memoization )
		System.out.println( fibo_rc_memoi(50) );
		
		
		// #3. dp ( 점화식 + 메모이제이션 )
		System.out.println( fibo_dp(50) );
	}

	static long fibo_rc(int n) {
		if( n == 1 || n == 2 ) return 1;
		return fibo_rc(n-1) + fibo_rc(n-2);
	}
	
	static long[] memoi_rc = new long[51]; // 0 으로 초기화
	
	static long fibo_rc_memoi(int n) {
		if( n == 1 || n == 2 ) return 1;
		// 이미 n 에 대해 사전에 수행되고 기록된 결과가 존재하는 지 확인
		if( memoi_rc[n] > 0 ) return memoi_rc[n]; // 이전 수행되고, 기록된 결과를 재활용
		else return memoi_rc[n] = fibo_rc_memoi(n-1) + fibo_rc_memoi(n-2); // n 대해 최초 실행, 기록
	}
	
	
	static long[] memoi_dp = new long[51]; // 0 으로 초기화
	
	// 점화식을 세우고 전 -> 후 점화식을 단계별로 적용(계산)한 결과를 메모이제이션 자료구조에 저장,활용
	static long fibo_dp(int n) {
		
		if( n == 1 || n == 2 ) return 1;
		
		memoi_dp[1] = 1;
		memoi_dp[2] = 1;
		
		// 반복문으로 점화식을 원하는 답에 맞게 계산
		for (int i = 3; i <= n; i++) {
			memoi_dp[i] = memoi_dp[i-1] + memoi_dp[i-2];
		}
		
		return memoi_dp[n];
	}
}

// 기본적인 완탐 문제에서 시간초과가 발생하는 경우
// 더 효율적인 코드로 개선
//   1. 가지치기 ( 갈 필요가 없는 반복 탐색 ?? )
//   2. 메모이제이션 ( 반복적으로 수행되는 탐색 ?? )