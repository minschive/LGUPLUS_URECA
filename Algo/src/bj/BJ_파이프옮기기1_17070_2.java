package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dp
public class BJ_파이프옮기기1_17070_2 { // 강사님 풀이

	static int N;
	static int[][] map;
	static int[][][] memoi; // 특정 좌표로 오는 모든 방법 (대각선, 가로, 세로)
	// memoi[0][4][3] => 대각선으로 4, 3 으로 오는 경우의 수(합)
	// memoi[1][4][3] => 가로로 4, 3 으로 오는 경우의 수(합)
	// memoi[2][4][3] => 세로로 4, 3 으로 오는 경우의 수(합)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N + 1][N + 1]; // 0 dummy
		memoi = new int[3][N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 풀이 dp
		// 시작점
		memoi[1][1][2] = 1; // 가로, 시작점
		
		for(int y = 1; y <= N; y++) {
			for(int x = 2; x <= N; x++) {
				// 벽
				if(map[y][x] == 1) continue;
				
				// 대각선
				// 3 좌표가 0이어야 함
				if(y < N && x < N && map[y+1][x+1] == 0 && map[y][x+1] == 0 && map[y+1][x] == 0) {
					memoi[0][y+1][x+1] += ( memoi[0][y][x] + memoi[1][y][x] + memoi[2][y][x]);
				}
				
				// 가로
				if(x < N && map[y][x+1] == 0) {
					memoi[1][y][x+1] += ( memoi[0][y][x] + memoi[1][y][x]);
				}
				
				// 세로
				if(y < N && map[y+1][x] == 0) {
					memoi[2][y+1][x] += ( memoi[0][y][x] + memoi[2][y][x]);
				}
			}
		}

		// N, N 으로 오는 대각선, 가로, 세로로부터의 경우의 수의 합
		System.out.println(memoi[0][N][N] + memoi[1][N][N] + memoi[2][N][N]);
	}

}
