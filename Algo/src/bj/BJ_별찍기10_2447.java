package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_별찍기10_2447 { // 보조강사님 풀이
	
	static char[][] board;
	
	static void makeBlank(int xStart, int yStart, int totalSize) {
		// 기저조건
		if(totalSize == 1) {
			return;
		}
		
		int size = totalSize / 3;
		
		// Devide (문제를 작은 단위로 분할)
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				if(x == 1 && y == 1) continue; // 재귀적으로 돌지 않음
				makeBlank(xStart + x * size, yStart + y * size, size);
			}
		}
		
		// Conquer (정복 : 문제를 푼다.)
		for(int x = xStart + size; x < xStart + size * 2; x++) { // 0 + 9 ~~ 18
			for(int y = yStart + size; y < yStart + size * 2; y++) { // 0 + 9 ~~ 18
				board[x][y] = ' ';
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		board = new char[n][n];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(board[i], '*');
		}
		
		makeBlank(0, 0, n);
		
		// 출력
		for(int i = 0; i < n; i++) {
			System.out.println(board[i]);
		}
		
		br.close();
	}

}