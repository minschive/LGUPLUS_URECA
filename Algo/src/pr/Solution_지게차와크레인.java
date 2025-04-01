package pr;

import java.util.*;

public class Solution_지게차와크레인 {

	// 창고의 세로(R)와 가로(C) 크기를 저장하는 변수
	   static int R;
	   static int C;
	   // 창고의 컨테이너 배치 상태를 저장하는 2차원 배열
	   static char[][] charArray;
	   // 상하좌우 이동을 위한 방향 배열 (상,좌,하,우 순서)
	   static int[] dr = {-1, 0, 1, 0};
	   static int[] dc = {0, -1, 0, 1};
	   
	   public int solution(String[] storage, String[] requests) {
	       // 창고의 크기 초기화
	       R = storage.length;
	       C = storage[0].length();
	       // 창고 상태 배열 초기화
	       charArray = new char[R][];
	       for (int i = 0; i < R; i++) {
	           charArray[i] = storage[i].toCharArray();
	       }
	       
	       // 각 출고 요청을 순서대로 처리
	       for (String str : requests) {
	           char type = str.charAt(0); // 요청된 컨테이너 타입
	           if (str.length() == 1) { // 길이가 1이면 지게차 사용
	               forklift(type);
	           } else { // 길이가 2이면 크레인 사용
	               crane(type);
	           }
	       }
	       return counts(); // 남은 컨테이너 개수 반환
	   }
	   
	   // 크레인 작업: 해당 타입의 모든 컨테이너를 제거
	   static void crane(char type) {
	       for (int i = 0; i < R; i++) {
	           for (int j = 0; j < C; j++) {
	               if (charArray[i][j] == type) {
	                   charArray[i][j] = ' '; // 해당 타입의 컨테이너를 빈 공간으로 변경
	               }
	           }
	       }
	   }
	   
	   // 지게차 작업: 외부에서 접근 가능한 컨테이너만 제거
	   static void forklift(char type) {
	       Queue<int[]> queue = new LinkedList<>(); // BFS를 위한 큐
	       boolean[][] visited = new boolean[R][C]; // 방문 체크 배열
	       
	       // 가장자리에 있는 접근 가능한 공간들을 큐에 추가
	       for (int i = 0; i < R; i++) {
	           for (int j = 0; j < C; j++) {
	               if (i == 0 || i == R - 1 || j == 0 || j == C - 1) { // 가장자리 확인
	                   if (charArray[i][j] == type || charArray[i][j] == ' ') { // 타겟 컨테이너나 빈 공간인 경우
	                       queue.offer(new int[] {i, j, charArray[i][j]}); // 위치와 해당 위치의 값을 저장
	                       visited[i][j] = true;
	                       charArray[i][j] = ' '; // 빈 공간으로 변경
	                   }
	               }
	           }
	       }
	       
	       // BFS로 접근 가능한 모든 공간 탐색
	       while (!queue.isEmpty()) {
	           int[] cur = queue.poll();
	           if (cur[2] != type) { // 현재 위치가 타겟 컨테이너가 아닌 경우(빈 공간인 경우)
	               // 상하좌우 탐색
	               for (int i = 0; i < 4; i++) {
	                   int nr = cur[0] + dr[i];
	                   int nc = cur[1] + dc[i];
	                   // 유효한 위치이고, (타겟 컨테이너이거나 빈 공간이며), 방문하지 않은 경우
	                   if (nr >= 0 && nr < R && nc >= 0 && nc < C 
	                       && (charArray[nr][nc] == type || charArray[nr][nc] == ' ') 
	                       && !visited[nr][nc]) {
	                       queue.offer(new int[] {nr, nc, charArray[nr][nc]});
	                       charArray[nr][nc] = ' '; // 빈 공간으로 변경
	                       visited[nr][nc] = true;
	                   }
	               }
	           }
	       }
	   }
	   
	   // 창고에 남아있는 컨테이너 개수를 세는 메소드
	   static int counts() {
	       int count = 0;
	       for (int i = 0; i < R; i++) {
	           for (int j = 0; j < C; j++) {
	               if (charArray[i][j] != ' ') count++; // 빈 공간이 아닌 경우 카운트
	           }
	       }
	       return count;
	   }

}
