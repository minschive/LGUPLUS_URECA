package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 재귀함수
public class BJ_Z_1074_2 { // 강사님 풀이
	
	static int N, r, c, ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		// 풀이
		N = (int)Math.pow(2, N); // 2의 제곱 수이므로 계속 반으로 나눌 수 있다.
		
		z(0, 0);
		
		System.out.println(ans);
	}

	static void z(int y, int x) { // 시작점 (최초 원점)
		
		// 기저조건
		if(N == 1) return;
		
		N /= 2;
		
		// r, c 가 4개의 영역 중 어디에 있는 지 판단
		if(r < y+N && c < x+N) { // 왼쪽 위
			z(y, x);
		} else if(r < y+N && c >= x+N) { // 오른쪽 위
			ans += N * N * 1;
			z(y, x+N);
		} else if(r >= y+N && c < x+N) { // 왼쪽 아래
			ans += N * N * 2;
			z(y+N, x);
		} else {
			ans += N * N * 3;
			z(y+N, x+N);
		}
	}
}
