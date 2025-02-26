package basic.delta;

import java.util.Arrays;

// 특정 위치에서 4방 탐색을 진행하는 경우, if-else if -else if - ... <= 코드의 길이가 길어지고, 가독성이 떨어지고 나누어서 생각하고
//  => 실수하기 쉽다.
// 한 칸 이동
public class DeltaTest1 {
	
	static char[][] map = new char[5][5];
	
	public static void main(String[] args) {
		char ch = 'A';
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[i][j] = ch++;
			}
		}

		// 출력
		for (int i = 0; i < 5; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
		// 4방 탐색 - 상-하-좌-우
//		int y = 2;
//		int x = 2;
//		
//		System.out.println(map[y][x]);
		
		print4x(2, 2);
		System.out.println();
		print4x(0, 0);
		
	}
	
	// delta 는 이동방향에 대해서 y의 변화량, x의 변화량을 미리 배열에 저장하고 이를 활용해서 새로운 이동(상,하,좌,우) 좌표를 구하는 방식
	//                  상  하   좌 우
	//				     0  1   2  3 <= d
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };
	
	static void print4x(int y, int x) {
		
		for (int d = 0; d < 4; d++) {
			
			// y, x 에서 현재 d 방향으로 이동한 새로운 좌표 계산
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			// 새로운 좌표 ny, nx 는 배열의 범위를 벗어날 수 있다. 이에 대한 장치 필요
			if( ny < 0 || nx < 0 || ny >= 5 || nx >= 5 ) continue; // 범위를 벗어난 좌표는 무시

			System.out.println(map[ny][nx]);
		}
	}

	// 문제 : 상하좌우로 이동할 수 있다. 순서를 상하좌우로 지킬 필요 X
	// 문제 : 맨 위부터 탐색 시계 방향으로... 상-우-하-좌
	// 문제 : 대각선, 8방, 장기의 말 이동 ....문제가 제시한 이동방향, 규칙에 대해서 dy, dx 변화량과 순서를 고려해서 처리
}

