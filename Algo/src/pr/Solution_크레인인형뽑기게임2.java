package pr;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_크레인인형뽑기게임2 { // 강사님 풀이
	
	public static void main(String[] args) {
		int[][] board = {{0,0,0,0,0}, {0,0,1,0,3}, {0,2,5,0,1}, {4,2,4,4,2}, {3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		int answer = new Solution_크레인인형뽑기게임2().solution(board, moves);
		System.out.println(answer);
	}
	
	public int solution(int[][] board, int[] moves) {
		
		int[] col_top_idx = new int[board[0].length]; // 일차원 배열에 각 컬럼별 최상위 인형의 index 관리
		
		// 컬럼(열) 이동하면서
		for(int col = 0; col < col_top_idx.length; col++) {
			// 맨 꼭대기부터 시작 0이 아닌 첫 번째 index
			int top_idx = 0;
			while(top_idx < board.length - 1 && board[top_idx][col] == 0) top_idx++;
			col_top_idx[col] = top_idx;
		}
		
        Deque<Integer> bucket = new ArrayDeque<>(); // Stack -> ArrayDeque
        int answer = 0;
        
        for(int move : moves){ // 1 기반 index
        	
        	if(col_top_idx[move - 1] > board.length - 1) continue; // 해당 열에서 꺼낼 인형 없음
        	
        	// 꺼낼 인형이 있다.
        	int doll = board[col_top_idx[move-1]][move-1];
        	col_top_idx[move - 1]++; // 현재 최상 높이의 인형을 꺼내고 하나 밑으로 조정
        	
        	if(!bucket.isEmpty() && bucket.peek() == doll) { // 같은 인형
    			bucket.pop(); // 맨 꼭데기 인형 꺼낸다.
    			answer += 2;
    		} else {
    			bucket.push(doll);
    		}
    	}
        return answer;
    }
}
