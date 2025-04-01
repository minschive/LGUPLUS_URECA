package basic.recursive;

public class Test {

	public static void main(String[] args) {
//		m1();
//		m1_param(0);
		m2();
//		m3();
//		m4(5);
		
	}
	
	///// 변수
	static int m1_i = 0;
	static void m1() {
		// local 변수 <- 재귀호출마다 스택에 새로 만들어지고 초기화 된다.
//		int i = 0;
//		System.out.println("m1() " + i++);
		
		// static 변수 <- 재귀호출마다 공유
		System.out.println("m1() " + m1_i++);
		m1();
	}
	
	static void m1_param(int i) {
		// 파라미터 변수 <- 재귀호출마다 스택에 새로 만들어지고 이전 호출에서 전달된 값을 복사
		System.out.println("m1_param() " + i++);
		m1_param(i);
	}
	
	///// 기저 조건
	static int m2_cnt = 5; // 5번만 수행해라
	static void m2() {
		System.out.println("앞 m2() " + m2_cnt);
		// 기저 조건
		if(m2_cnt > 0) {
			m2_cnt--;
			m2(); // 이 부분 때문에 아래 뒤 m2()를 출력을 미룸 
		}
		System.out.println("뒤 m2() " + m2_cnt ); // 조건에 안 맞는 경우 이 부분 호출 진행 ( 스택 제거 )
		// 6번씩 싫행된 이유 ? 0인 상태에서 m2() 호출 -> "앞 m2()" 출력
		// 뒤 m2()도 6번 출력되는 이유 ? 앞 m2()로 인해 함수가 6번 호출되었으므로 뒤도 6번 호출되어야 한다.
		// 스택 안에서 앞 m2가 6번 반복 된거고, 그게 풀리고 나서 뒤 6번 반복
	}
	
	// 5번씩 호출, static 변수 활용
	static int m3_cnt = 5; // 5번만 수행해라
	static void m3() {
		// 기저 조건
		if(m3_cnt == 0) return;
				
		System.out.println("앞 m3() " + m3_cnt);
		
		m3_cnt--;
		m3();
		m3_cnt++; // 이 코드가 없으면 static 변수 m3_cnt 가 0으로 감소한 후 계속 0으로 유지 -> 원복 필요
		
		System.out.println("뒤 m3() " + m3_cnt);
	}
	
	// 파라미터 변수 활용
	static void m4(int m4_cnt) {
		// 기저 조건
		if(m4_cnt == 0) return;
				
		System.out.println("앞 m4() " + m4_cnt);
		
//		m4_cnt--;
//		m4(m4_cnt);
//		m4_cnt++;
		
		m4(m4_cnt - 1); // 호출 시 전달받은 m4_cnt 는 변화 X -> 로컬변수일 경우 이 코드가 best
		
//		m4(m4_cnt--); // err -> 5가 전달되어 stackOverflow
		
//		m4(--m4_cnt); // m4 호출 시 전달받은 m4_cnt 가 1 감소
//		m4_cnt++; // 그리하여 원복 필요
		
		System.out.println("뒤 m4() " + m4_cnt);
	}

}
