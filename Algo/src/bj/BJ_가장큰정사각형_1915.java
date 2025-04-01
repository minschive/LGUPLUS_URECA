package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BJ_가장큰정사각형_1915 {
    // 입력받은 행렬을 저장할 2차원 배열 (패딩을 위해 크기를 1001×1001로 설정)
    static int[][] arr = new int[1001][1001];
    // 행렬의 크기를 저장할 변수
    static int n, m;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 가장 큰 정사각형의 한 변의 길이를 저장할 변수
        int sqrtAns = 0;
        
        // 행렬의 크기 입력 받기
        n = Integer.parseInt(st.nextToken());  // 행의 개수
        m = Integer.parseInt(st.nextToken());  // 열의 개수
        
        // 행렬 데이터 입력 받기
        for (int y = 1; y <= n; y++) {
            String line = br.readLine();
            for (int x = 1; x <= m; x++) {
                // 문자를 숫자로 변환하여 저장
                arr[y][x] = line.charAt(x-1) - '0';
            }
        }
        
        // 동적 프로그래밍을 이용한 최대 정사각형 찾기
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (arr[y][x] != 0) {  // 현재 위치가 1인 경우에만 처리
                    // 현재 위치에서 가능한 정사각형의 크기는
                    // 왼쪽(←), 위쪽(↑), 왼쪽 위 대각선(↖) 위치의 값 중 최솟값에 1을 더한 값
                    arr[y][x] = Math.min(arr[y-1][x], arr[y][x-1]);
                    arr[y][x] = Math.min(arr[y][x], arr[y-1][x-1]) + 1;
                    // 최대 정사각형의 크기 업데이트
                    sqrtAns = Math.max(arr[y][x], sqrtAns);
                }
            }
        }
        
        // 정사각형의 넓이 출력 (한 변의 길이의 제곱)
        System.out.println(sqrtAns * sqrtAns);
        br.close();
    }
}