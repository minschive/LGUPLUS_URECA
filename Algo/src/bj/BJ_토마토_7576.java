package bj;

import java.io.*;
import java.util.*;

public class BJ_토마토_7576 { // 강사님 풀이
	
    // 방향 벡터 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int m; 
    static int n; 
    static int[][] matrix;
    static Queue<int[]> queue;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // M: 가로(열), N: 세로(행)
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        // 토마토 정보를 담을 2차원 배열
        matrix = new int[n][m];

        // BFS를 위한 큐(Queue) 자료구조
        queue = new LinkedList<>();

        // 토마토 입력 받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                // 익은 토마토(1)는 큐에 추가한다
                // 이 1이라는 값을, 익은 토마토이면서 동시에 날짜를 나타낸다고 생각하는 것이 키포인트!
                if (matrix[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        BFS();
        // 결과 계산
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 만약 익지 않은 토마토(0)가 남아있다면 -1 출력 후 종료
                if (matrix[i][j] == 0) {
                    System.out.println("-1");
                    return;
                }
                // 최대 날짜 갱신
                result = Math.max(result, matrix[i][j]);
            }
        }
        // 정답 출력 (처음 토마토의 값이 1이었기 때문에 결과에서 -1을 해주어야 경과한 날짜가 나온다!)
        System.out.println(result - 1);
        br.close();
    }
    
    static void BFS() {
        while (!queue.isEmpty()) {
            // 현재 익은 토마토 위치
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어나지 않고, 아직 익지 않은 토마토(0)인 경우
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && matrix[nx][ny] == 0) {
                    // 익은 날짜를 기록 (이전 토마토의 날짜 값 + 1)
                    matrix[nx][ny] = matrix[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}