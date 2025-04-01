package bj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 5 번째 집 칠하는 최소 비용
//  5 번째 집을 R 칠할 때 <= 4번째 집이 G 일 때 비용과 4번째 집이 B 일 때 비용 중 최소 비용 선택
//  5 번째 집을 G 칠할 때 <= 4번째 집이 R 일 때 비용과 4번째 집이 B 일 때 비용 중 최소 비용 선택
//  5 번째 집을 B 칠할 때 <= 4번째 집이 R 일 때 비용과 4번째 집이 G 일 때 비용 중 최소 비용 선택
public class BJ_RGB거리_1149 {
    static int N;
    static int[][] memoi; // i 번째 선택이 3가지, 각각 최소 비용
    static int[][] cost;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        cost = new int[N + 1][3]; // R,G,B
        
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken()); // R
            cost[i][1] = Integer.parseInt(st.nextToken()); // G
            cost[i][2] = Integer.parseInt(st.nextToken());
        }
        
        // 풀이
        memoi = new int[N + 1][3];
        // 맨 앞 집에 R, G, B 각 초기화
        memoi[1][0] = cost[1][0]; // 첫 번째 집을 R 로 칠한 경우
        memoi[1][1] = cost[1][1]; // 첫 번째 집을 G 로 칠한 경우
        memoi[1][2] = cost[1][2]; // 첫 번째 집을 B 로 칠한 경우
        
        // 두 번째 집부터 dp 풀어 간다.
        for (int i = 2; i <= N; i++) {
            memoi[i][0] = Math.min(memoi[i-1][1], memoi[i-1][2]) + cost[i][0];
            memoi[i][1] = Math.min(memoi[i-1][0], memoi[i-1][2]) + cost[i][1];
            memoi[i][2] = Math.min(memoi[i-1][0], memoi[i-1][1]) + cost[i][2];
        }
        
        System.out.println( Math.min(  Math.min(memoi[N][0], memoi[N][1]), memoi[N][2]  ) );
    }
}
