package ch15;

import java.util.HashSet;
import java.util.Set;

public class Test3 {

	public static void main(String[] args) {
		// Set
		// 순서 X
		// 중복 X
		Set<String> set = new HashSet<>();
		set.add("java");
		set.add("hello");
		set.add("java");
		set.add("world");
		set.add("world");
		
		for(String str : set) {
			System.out.println(str);
		}
		
		// Board
		// equals(), hashCode() 재정의한 상태
		// 만약 Board 클래스의 hashCode() 를 재정의하지 않으면 ?
//		Board [subject=제목2, content=내용2, writer=작성자2]
//		Board [subject=제목1, content=내용1, writer=작성자1]
//		Board [subject=제목4, content=내용4, writer=작성자4]
//		Board [subject=제목1, content=내용1, writer=작성자1]
//		Board [subject=제목2, content=내용2, writer=작성자2]
		
		Set<Board> boardSet = new HashSet<>();
		boardSet.add(new Board("제목1", "내용1", "작성자1"));
		boardSet.add(new Board("제목2", "내용2", "작성자2"));
		boardSet.add(new Board("제목2", "내용2", "작성자2"));
		boardSet.add(new Board("제목4", "내용4", "작성자4"));
		boardSet.add(new Board("제목1", "내용1", "작성자1"));
		
		for(Board board : boardSet) {
			System.out.println(board);
		}
	}

}
