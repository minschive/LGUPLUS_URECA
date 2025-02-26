package basic.stackqueue;

import java.util.PriorityQueue;

// 우선순위 Queue
// 내부적으로 Heap 자료 구조
// 넣은 순서대로 나오는 것이 아니라, 생성하면서 함께 제공하는 또는 Queue 에 담기는 클래스의 정렬 조건을 우선적으로 고려해서 나온다.

public class TestPQ {

	public static void main(String[] args) {
		// Integer
		{
//			PriorityQueue<Integer> pqueue = new PriorityQueue<>();
//			pqueue.offer(3);
//			pqueue.offer(2);
//			pqueue.offer(7);
//			pqueue.offer(5);
//			pqueue.offer(9);
//			
//			// 작은 수부터 출력
//			while(!pqueue.isEmpty()) {
//				System.out.println(pqueue.poll());
//			}
//			
//			// foreach : 입력 순서도 아니고, 우선순위가 적용 X
////			for(Integer num : pqueue) {
////				System.out.println(num);
////			}
		}
		
		// 사용자 정의 클래스
		{
			// 아래와 같이 정렬 조건을 주지 않을 경우는 Edge 클래스가 Comparable 인터페이스를 구현해야 한다.
//			PriorityQueue<Edge> pqueue = new PriorityQueue<>();
//			pqueue.offer(new Edge(2, 5, 4));
//			pqueue.offer(new Edge(1, 6, 3));
//			pqueue.offer(new Edge(7, 5, 5));
//			pqueue.offer(new Edge(3, 9, 1));
//			
//			while(!pqueue.isEmpty()) {
//				System.out.println(pqueue.poll());
//			}
		}
		
		// 정렬 조건을 Priority Queue 를 만들면서 전달
		PriorityQueue<Edge> pqueue = new PriorityQueue<>( (e1, e2) -> e1.c - e2.c);
		pqueue.offer(new Edge(2, 5, 4));
		pqueue.offer(new Edge(1, 6, 3));
		pqueue.offer(new Edge(7, 5, 5));
		pqueue.offer(new Edge(3, 9, 1));
		
		while(!pqueue.isEmpty()) {
			System.out.println(pqueue.poll());
		}
	}
	
	// implements Comparable 도 가능
	static class Edge {
		int v1, v2, c;
		
		Edge(int v1, int v2, int c) {
			this.v1= v1;
			this.v2 = v2;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Edge [v1=" + v1 + ", v2=" + v2 + ", c=" + c + "]"; // 개행문자 추가
		}
	}

}
