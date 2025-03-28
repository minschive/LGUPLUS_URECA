package ch15;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		// List - ArrayList
		List<Board> list = new ArrayList<>();
		
		{
			list.add(new Board("제목1", "내용1", "작성자1"));
			list.add(new Board("제목2", "내용2", "작성자2"));
			list.add(new Board("제목3", "내용3", "작성자3"));
			list.add(new Board("제목4", "내용4", "작성자4"));
			
			System.out.println(list); // Board 의 toString() 재정의 -> 결과 : 순서 유지
			System.out.println(list.size()); // 크기 -> 4
			System.out.println(list.get(2)); // index 기반 객체 찾기
			
			// 중복이 허용됨
			Board board = new Board("제목5", "내용5", "작성자5"); // heap 에 1개 객체
			list.add(board); // 동일객체 참조1
			System.out.println(list.size()); // -> 5
			list.add(board); // 동일객체 참조2
			System.out.println(list.size()); // -> 6
			System.out.println(list); 
			
			// 삭제 (종류 : index, 객체)
			list.remove(1); // 제목2 항목 삭제
			System.out.println(list);
			
			list.remove(new Board("제목3", "내용3", "작성자3")); // 전달되는 객체의 equals() 로 처리
			System.out.println(list); // -> 삭제되지 않음 : equals() 재정의 하지 않아서
			
			list.remove(board); // board 참조 값(주소값)으로 찾는다. Object 의 equals() => == 비교 => 주소값 같은 것 찾음
			System.out.println(list);  
			
			// 만약 필드값 등 전달되는 객체의 내용으로 삭제하고 싶으면 equals(), hashcode() Overriding 해야 함
			// 재정의 후에 "제목3" 객체가 삭제됨을 확인
			
			
			// 전체 삭제 (초기화)
			list.clear();
			System.out.println(list.size());
			System.out.println(list);
		}
		
		// ------------------------------------------------------
		
		// 순회
		// equals(), hashCode() 재정의된 상태
		{
			list.add(new Board("제목1", "내용1", "작성자1"));
			list.add(new Board("제목2", "내용2", "작성자2"));
			list.add(new Board("제목3", "내용3", "작성자3"));
			list.add(new Board("제목4", "내용4", "작성자4"));
			list.add(new Board("제목4", "내용4", "작성자4"));
			
			// index 기반
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			
			// foreach
			for(Board board : list) {
				System.out.println(board);
			}
			
			// iterator
			Iterator<Board> itr = list.iterator();
			while(itr.hasNext()) {
				Board board = itr.next();
				System.out.println(board);
			}
			
			System.out.println();
			
			// Board 객체 중 new Board("제목4", "내용4", "작성자4") 와 동일한 객체 삭제
			Board board = new Board("제목4", "내용4", "작성자4");
			
//			for(int i = 0; i < list.size(); i++) {
//				if(list.get(i).equals(board)) list.remove(i); // i 기반 삭제 (i번째 요소가 삭제된 후 나머지 요소들이 앞으로 한 칸씩 이동)
//			}
//			System.out.println(list); // -> 앞에 있는 제목4가 삭제 
			
			// 인덱스 기반 삭제는 뒤쪽부터 삭제 (앞으로 당겨지는 요소가 아직 순회하지 않은 요소들이므로 건너뛰는 문제가 발생하지 않음.)
//			for(int i = list.size() -1; i >= 0; i--) {
//				if(list.get(i).equals(board)) list.remove(i); // i 기반 삭제
//			}
//			System.out.println(list);
			
			// iterator (리스트 크기가 변경되더라도 안전하게 삭제 가능)
			Iterator<Board> itr2 = list.iterator();
			while(itr2.hasNext()) {
				Board board2 = itr2.next();
				if(board2.equals(board)) itr2.remove();
			}
			System.out.println(list);
		}
		
	}

}
