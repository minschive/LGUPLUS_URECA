package basic.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UnionFind {
	
	static int v, e;
	static int[] parent; // 각 원소(정점 번호)별 집합 관계를 표현하는 1차원 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		// 그래프 정점(1 ~ 6) 간 집합 표현
		// 1차원 배열 생성 
		parent = new int[v + 1]; // 0 dummy
		
		// #2. 1차원 배열 초기화
		// 각 원소별 자기 자신이 대표원소가 되도록 (모든 정점이 각각 서로소인 집합)
		makeSet();
		
		// #3. 주어진 테케에 맞게 union() 진행
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// union
			union(x, y);
		}
		
		// 인덱스
		for(int i = 1; i <= v; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		// parent 배열 값
		for(int i = 1; i <= v; i++) {
			System.out.print(parent[i] + " ");
		}
		System.out.println();
		
		// 대표원소
		for(int i = 1; i <= v; i++) {
			System.out.print(findSet(i) + " ");
		}
		System.out.println();
	}
	
	static void makeSet() {
		for(int i = 1; i <= v; i++) {
			parent[i] = i; // 모두가 대표 원소
		}
	}
	
	// 전달된 x 원소의 대표원소 찾아서 return
//	static int findSet(int x) {
//		if(parent[x] == x) return x; // 대표원소이면 x 리턴
//		return findSet(parent[x]); // 변화없이 그냥 리턴
//	}
	
	// 전달된 x 원소의 대표원소 찾아서 return
	static int findSet(int x) {
		if(parent[x] == x) return x; // 대표원소이면 x 리턴
		return parent[x] = findSet(parent[x]); // 맨 꼭데기 대표원소의 index 값이 리턴되어 들어오는데 이 중간 과정의 x 의 부모 parent[x] 에 넣는다.
	}
	
	// 전달된 두 원소 x, y 에 대해 x 가 속한 집합과 y 가 속한 집합을 하나의 집합으로 합친다.
	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		// px == py 이면 이미 같은 집합
		// 그렇지 않은 경우 규칙을 부여할 수 있다. 아래 코드는 작은 값을 부모로.
		if(px < py) parent[py] = px;
		else parent[px] = py;
	}

}
// 트리 : 노드(Node), 간선(Edge)
// 그래프 : 정점(Vertex), 간선(Edge)
/*
6 4 <- 정점(v), 간선(e)
1 4 <- 나머지는 합치는 연산
2 3
2 4
5 6
*/
