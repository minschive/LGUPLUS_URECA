package bj;

import java.io.*;
import java.util.*;

public class BJ_탑_2493 { // 보조강사님 풀이
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // StringBuilder sb = new StringBuilder();
        
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 결과를 저장할 배열
        /*
         * 어떤 탑이 신호를 수신했는지 저장하는 배열
         */
        int[] result = new int[N];
        
        // 탑의 인덱스, 높이를 저장할 배열
        Stack<int[]> stack = new Stack<>();
        
        for (int i = 0; i < N; i++) {
        	int height = Integer.parseInt(st.nextToken());
        	
        	// 스택의 top에 있는 탑의 높이가 지금 들어온 탑의 높이보다 낮으면 제거
        	while (!stack.isEmpty() && stack.peek()[1] < height) {
        		stack.pop();
        	}
        	
        	// 스택이 비어있으면 수신할 탑이 없기 때문에 0
        	if (stack.isEmpty()) {
        		result[i] = 0;
        		// 그게 아니라면 스택의 top에 있는 탑이 신호를 수신
        	} else {
        		result[i] = stack.peek()[0] + 1;
        	}
        	// 현재 들어온 탑을 스택에 저장
        	stack.push(new int[] {i, height});
        }
        
        for(int i = 0; i < N; i++) {
        	bw.write(result[i] + " ");
        }
        

        bw.flush();
        bw.close();
        br.close();
    }
}
