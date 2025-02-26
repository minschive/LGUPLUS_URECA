package basic.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionSortTest {

	public static void main(String[] args) {
		// ArrayList 를 이용한 정렬
		// Integer, String 모두 이미 정렬된다.
		List<Item> list = new ArrayList<>();
		list.add(new Item(3, "66"));
		list.add(new Item(2, "77"));
		list.add(new Item(5, "33"));
		list.add(new Item(8, "44"));
		
		System.out.println(list);
		
		// #1. Comparable
//		Collections.sort(list); // implements Comparable 한 객체가 아닌 경우 컴파일 에러 발생
//		System.out.println(list);
		
		// #2. Comparator 인터페이스 구현 - lambda
		Collections.sort(list, (o1, o2) -> o1.itemId - o2.itemId);
		System.out.println(list);

	}
	
	// #1. Comparable
//	static class Item implements Comparable<Item>{
//		int itemId;
//		String itemNm;
//		
//		Item(int itemId, String itemNm){
//			this.itemId = itemId;
//			this.itemNm = itemNm;
//		}
//	
//		@Override
//		public String toString() {
//			return "Item [itemId=" + itemId + ", itemNm=" + itemNm + "]";
//		}
//	
//		@Override
//		public int compareTo(Item o) { // 정렬 기준 제공
//			return this.itemId - o.itemId; // natural ordering
//	//		return o.itemId - this.itemId; // natural ordering
//	//		return this.itemNm.compareTo(o.itemNm);
//			// itemId 우선 비교하는 데 같은 값이면 itemNm 비교
////			return this.itemId == o.itemId ? this.itemNm.compareTo(o.itemNm) : this.itemId - o.itemId;
//		}
//	
//	}	

	static class Item{
		int itemId;
		String itemNm;
		
		Item(int itemId, String itemNm){
			this.itemId = itemId;
			this.itemNm = itemNm;
		}

		@Override
		public String toString() {
			return "Item [itemId=" + itemId + ", itemNm=" + itemNm + "]";
		}
	}
}
