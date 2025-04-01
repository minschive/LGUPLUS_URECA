package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 이전 상태에 놓여진 Queen 이 계속 이후 상태에 영향을 미친다.
// bfs 로 ?? Queue 에 담을 때 담는 시점의 rowColumns[] 을 가지고 있어야 한다. 꺼낸 시점에서 이전 상태의 Queen 이 놓여진 상태를 알 수 있다.

public class BJ_NQueen_9663 {
	
	static int N, ans;
	static int[] rowColumns; // rowColums[2] = 4 <= 2번 row 에 현재 놓여진 Queen column 값이 4
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		rowColumns = new int[N]; // N 개 row 에 대해
		
		dfs(0); // 0번째 row 시작
		
		System.out.println(ans);
	}
	
	static void dfs(int row) {
		// 기저조건
		// 이전단계에서 마지막 행까지 다 놓았으면
		if(row == N) {
			ans++;
			return;
		}
		
		// 현재 row 행에 Queen 을 놓아본다.(옆으로 column 에 한자리씩 놓아본다.)
		for(int i = 0; i < N; i++) {
			rowColumns[row] = i; // 한 행에는 한 개의 Queen 만 놓고있다.
			// 유망 함수를 호출해서 가능하면 다음 row 로 계속 이어간다.
			if(check(row)) {
				dfs(row + 1);
			}
		}
	}
	
	// 유망 함수
	// 현재 row 에 rowColumns 에 저장된 이전 row 의 Queen 자리를 포함해서 가능한지 따져본다.
	static boolean check(int row) {
		// 시작 row 부터 현재 row 까지
		for(int i = 0; i < row; i++) {
			// 세로로 겹치는지
			if(rowColumns[i] == rowColumns[row]) return false;
			
			// 대각선으로 겹치는지
			// 행의 변화량 == 열의 변화량
			else if(row - i == Math.abs(rowColumns[i] - rowColumns[row])) return false;
		}
		
		return true;
	}

}
