package basic.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 한 정점에서부터 그래프의 다른 정점으로 가는 최소 비용
// 정점(Vertex) 중심 풀이
// 한 정점으로부터 다른 모든 정점으로 가는 최소 비용을 처리하는 배열 int[] cost
//   이 배열을 초기값으로 충분히 큰 값으로 설정, 이후 알고리즘을 진행하면서 시작 정점으로부터 비용을 갱신 진행
//   알고리즘 처리가 종료되면 cost[] 의 값이 최소 비용
//   시작정점 0, 5 개의 정점이 있으면 cost : { 0, 4, 6, 2, 7}
//   0 -> 1: 4, 0 -> 4 : 7
// 

//정점(들)에서 다른 모든 연결된 정점 중 최소 비용의 정점 찾는 방법 ? <= PriorityQueue 활용
// PriorityQueue 에서 꺼낸 정점으로부터 갈 수 있는 다른 정점들을 통해 cost 배열이 갱신될 수 있으면 갱신하고 갱신된 정점을 다시 PQ 에 넣는다.
//정점을 선택해 가는 과정에서 중복된 정점을 선택할 수 있다. -> visit[] 활용

public class Dijkstra {

	static int V, start, end; 
	static int[][] matrix; // 인접 행렬
//	static boolean[] visit; // visit <- 비용 비교를 통해서 visit 처리
	static PriorityQueue<Vertex> pqueue = new PriorityQueue<>( (v1, v2) -> v1.c - v2.c ); // 비용 기준
	
	static int[] cost; // 다익스트라의 핵심 자료 구조 (각 index 가 정점을 표현, 계속 최소비용으로 갱신되면서 정답을 보관)
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		V = Integer.parseInt(br.readLine());
		start = 0;
		end = V - 1;
		
		matrix = new int[V][V];
		cost = new int[V];
		
		for(int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < V; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 풀이
		// cost 배열 초기화 (충분히 큰 값)
		Arrays.fill(cost, INF);
		
		// 시작 정점(start) 부터 시작
		cost[start] = 0;
		pqueue.offer(new Vertex(start, 0));
		
		while(!pqueue.isEmpty()) {
			Vertex vertex = pqueue.poll(); // vertex = 교재 392p 하단 그림의 A를 꺼낸 상태에 대입
			
			// visit 체크 (별도의 visit[] 이용해서 풀이 가능)
			// cost[] 갱신여부 비용비교를 통해서 처리
			// 이미 방문 그리고 비용이 더 비싸기 떄문에 고려할 필요 X
			if(cost[vertex.v] < vertex.c) continue;
			
			// 시작정점(start) ~~> vertex.v 로부터 cost 의 비용이 갱신될 수 있다.
			for(int i = 0; i < V; i++) { // i 는 E에서부터 갈 수 있는 정점
				// 꺼낸 정점으로부터 갈 수 있는 정점을 고려
				if(matrix[vertex.v][i] == 0) continue;
				
				// i = 교재 392p 하단 그림의 E 에 해당 (B, C, E 를 고려할 때)
				// start 에서 vertex.v 를 경유해서 i로 가는 비용이 원 비용(cost[i]) 보다 낮으면 cost[i] 비용을 경신하고 다시 경쟁하도록 pqueue 에 담는다.
				if(cost[i] > vertex.c + matrix[vertex.v][i]) { 
					cost[i] = vertex.c + matrix[vertex.v][i]; 
					pqueue.offer(new Vertex(i, cost[i])); // start 에서 i 정점으로 가는 최소 비용을 이용해서 객체 생성
				}
			}
		}
		
		System.out.println(cost[end]);
		System.out.println(Arrays.toString(cost));
	}
	
	static class Vertex {
		int v, c; // v : 시작정점에서부터 갈 수 있는 정점, c : 시작정점에서부터 v 정점까지의 최소 비용
		Vertex(int v, int c){
			this.v = v;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Vertex [v=" + v + ", c=" + c + "]";
		}
	
	}

}

/*
5
0 6 2 5 9
6 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0
==> 8
4
0 94 53 16 
79 0 24 18 
91 80 0 98 
26 51 92 0
==> 16
7
0   2   8   5   9  15  20
2   0   5   4   7  10  16
8   5   0   7   6   4  10
5   4   7   0  15   8   9
9   7   6  15   0  11  13
15 10   4   8  11   0   6
20 16  10   9  13   6   0
==> 14
 */