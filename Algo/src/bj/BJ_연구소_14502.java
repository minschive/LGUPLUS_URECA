package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 기본 알고리즘은 조합 ( 0 중에 3개를 조합으로 선택해서 벽을 세운다 )
// 벽을 3개 세우면 virus 들을 한꺼번에 bfs 로 퍼뜨린다.
// 남아 있는 안전영역 계산 max 비교
public class BJ_연구소_14502 {
    static int N, M, max;
    static int[][] map, copyMap; // map : 처음 데이터 상태 유지, copyMap 에 복사해서 벽, 바이러스 작업 수행 안전영역 계산 
    
    // 벽을 세우는 좌표는 0 에서 3 개를 조합
    static List<Node> zero = new ArrayList<>(); // 0 인 좌표들
    static List<Node> virus = new ArrayList<>(); // 2 인 좌표들
    
    static int zeroSize;
    static Node[] wall = new Node[3]; // zero 에서 선택한 조합 ( 벽 위치 3 개 )
    
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = {  0, 0,-1, 1 };
    
    static Queue<Node> queue = new ArrayDeque<>();
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        copyMap = new int[N][M];
        
        max = Integer.MIN_VALUE;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if( n == 0 ) zero.add(new Node(i, j)); // 0
                else if( n == 2 ) virus.add(new Node(i, j)); // 바이러스
            }
        }
        
        zeroSize = zero.size();
        // 조합
        comb(0, 0);
        
        System.out.println(max);
    }
    // 조합 zero 에 있는 0 중 맨 앞에서부터 고려
    static void comb(int srcIdx, int tgtIdx) {
        // 기저조건
        if( tgtIdx == 3 ) {
            // 조합완성
            check();
            return;
        }
        
        if( srcIdx == zeroSize ) return;
        
        // 벽 세운다.
        wall[tgtIdx] = zero.get(srcIdx); // 선택
        
        comb(srcIdx + 1, tgtIdx + 1); // 선택 유지
        comb(srcIdx + 1, tgtIdx); // 비선택
    }
    
    // 바이러스 퍼뜨리고 안전영역 계산 max 갱신
    static void check() {
        // map => copyMap 복사, 초기화 필요 X
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j]; // 벽을 세우기 전 상태
            }
        }
        
        // comb 에서 완성된 벽의 위치 3개 wall 의 위치를 copyMap 추가
        for (int i = 0; i < 3; i++) {
            int y = wall[i].y;
            int x = wall[i].x;
            copyMap[y][x] = 1; // 벽을 세운다.
        }
        
        
        // virus 퍼지는 처리 : bfs
        queue.addAll(virus); // 처음 시작전에 모든 바이러스를 queue 담는다.
        
        while(! queue.isEmpty()) {
            Node n = queue.poll();
            
            for (int d = 0; d < 4; d++) {
                int ny = n.y + dy[d];
                int nx = n.x + dx[d];
                if( ny < 0 || nx < 0 || ny >= N || nx >= M ) continue; // visit 0 찾으면 갈수 있으면 가서 2로 그 자리를 변경
                if( copyMap[ny][nx] == 0 ) { // 빈칸이면 바이러스로 변경하고 그 위차를 queue 에 추가
                    copyMap[ny][nx] = 2;
                    queue.offer(new Node(ny, nx));
                }
            }
        }
        
        // 안전영역 계산 max 갱신
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if( copyMap[i][j] == 0 ) sum++; // 0 추가
            }
        }
        
        max = Math.max(max, sum);
    }
    
    static class Node{
        int y, x;
        Node(int y, int x){
            this.y = y; this.x = x;
        }
    }
}
