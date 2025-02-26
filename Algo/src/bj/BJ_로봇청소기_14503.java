package bj;

import java.io.*;
import java.util.*;

public class BJ_로봇청소기_14503 {
    // 방향 벡터 (상, 우, 하, 좌 순서)
    static int[] dx = {0, 1, 0, -1};  // x축 이동
    static int[] dy = {-1, 0, 1, 0};  // y축 이동
    
    static int N, M;                   // 방의 크기 (N x M)
    static int[][] map;                // 방의 상태를 저장하는 배열 (0: 빈 칸, 1: 벽)
    static int[][] clean;              // 청소 여부를 저장하는 배열
    static int r, c, dir;              // 로봇의 현재 위치(r,c)와 방향(dir)
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 방의 크기 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        clean = new int[N][M];
        
        // 로봇의 초기 위치와 방향 입력
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        
        // 방의 상태 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                clean[i][j] = map[i][j];
            }
        }
        
        System.out.println(simulation());
        br.close();
    }
    
    // 로봇 청소기 시뮬레이션 실행
    static int simulation() {
        int ans = 0;  // 청소한 칸의 개수
        
        while (true) {
            // 1. 현재 위치를 청소
            if (clean[r][c] == 0) {
                clean[r][c] = 1;
                ans++;
            }
            
            // 2. 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            if (checkUnclean()) {
                // 반시계 방향으로 90도씩 회전하며 탐색
                for (int i = 0; i < 4; i++) {
                    dir = (dir + 3) % 4;  // 반시계 방향 90도 회전 (dir + 3) % 4
                    int nr = r + dy[dir % 4];
                    int nc = c + dx[dir % 4];
                    
                    // 청소되지 않은 빈 칸을 발견하면 전진
                    if (map[nr][nc] == 0 && clean[nr][nc] == 0) {
                        r = nr;
                        c = nc;
                        break;
                    }
                }
            }
            // 3. 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            else {
                // 후진이 가능한지 확인 (dir + 2) % 4
                int nr = r + dy[(dir + 2) % 4];
                int nc = c + dx[(dir + 2) % 4];
                
                // 후진이 가능하면 후진
                if (map[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                }
                // 후진이 불가능하면 작동 중지
                else {
                    break;
                }
            }
        }
        return ans;
    }
    
    // 주변 4칸 중 청소되지 않은 빈 칸이 있는지 확인하는 함수
    static boolean checkUnclean() {
        for (int i = 0; i < 4; i++) {
            int nr = r + dy[i];
            int nc = c + dx[i];
            if (map[nr][nc] == 0 && clean[nr][nc] == 0) return true;
        }
        return false;
    }
}