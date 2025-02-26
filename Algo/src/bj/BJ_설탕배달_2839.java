package bj;

import java.util.Scanner;

// 그리디
// 3 보다는 5 를 많이 사용한다.
// 주어진 수 N 에 위 원리 적용 ?
// 5로 나누어 떨어지는 수 (5의 배수를 만들면 된다.)
// N 이 5의 배수가 될 때까지 3을 사용한다.
// 최대한 5를 많이 사용
public class BJ_설탕배달_2839 { // 강사님 풀이

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int count = 0; // 봉지 수
		
		while(true) {
			if(N < 0) { // 3 과 5 로 만들지 못하는 경우
				System.out.println("-1");
				break;
			}
			
			// N 이 5로 나누어 떨어지는지
			if(N % 5 == 0) {
				System.out.println(N / 5 + count);
				break;
			}
			
			// 3 하나 사용
			N -= 3;
			count++;
		}
	}

}
