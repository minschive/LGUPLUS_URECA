package basic.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ArraySortTest {

	public static void main(String[] args) {
		// 배열 정렬
		
		// int
//		int[] intArray = {3, 5, 2, 7, 9, 4};
//		
//		System.out.println(Arrays.toString(intArray));
//		Arrays.sort(intArray);
//		System.out.println(Arrays.toString(intArray));
//
//		// String
//		String[] strArray = {"Hello", "ABC", "World", "UPlus"};
//		
//		System.out.println(Arrays.toString(strArray));
//		Arrays.sort(strArray);
//		System.out.println(Arrays.toString(strArray));
//		
//		Arrays.sort(strArray, Collections.reverseOrder()); // 반대 정렬
//		System.out.println(Arrays.toString(strArray));
		
		// 사용자 정의 클래스
		Item[] itemArray = { new Item(5, "66"), new Item(5, "77"), new Item(2, "44"), new Item(8, "11") };
		
		// #1. 클래스에 정의
//		Arrays.sort(itemArray);
//		System.out.println(Arrays.toString(itemArray));
		
		// #2. Comparator 인터페이스 이용 - anonymous 객체
		// 정렬 대칭 클래스에 Comparable 인터페이스 구현하지 않아도, 정렬 시점에 정렬 기준을 구현한 객체 전달
//		Arrays.sort(itemArray, new Comparator<Item>() {
//
//			@Override
//			public int compare(Item o1, Item o2) {
//				return o1.itemId - o2.itemId; // natural ordering
//			}
//		});
//		System.out.println(Arrays.toString(itemArray));
		
		// #3. Comparator 인터페이스 이용 - lambda
//		Arrays.sort(itemArray, (o1, o2) -> o1.itemId - o2.itemId);
		Arrays.sort(itemArray, (o1, o2) -> o1.itemId == o2.itemId ? o1.itemNm.compareTo(o2.itemNm) : o1.itemId - o2.itemId);
		System.out.println(Arrays.toString(itemArray));
	}
	
	// 객체들을 정렬할 때 기준이 필요하다. 이 기준은 여러 방법 존재
	// #1. 클래스에 정의
//	static class Item implements Comparable<Item> {
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
////			return this.itemId - o.itemId; // natural ordering - itemID 기준 오름차순
////			return o.itemId - this.itemId; // natural ordering - itemID 기준 내림차순
////			return this.itemNm.compareTo(o.itemNm); // itemNm 기준 오름차순
//			
//			// itemId 우선 비교하는데 2개가 같은 값이면 itemNm으로 비교
//			return this.itemId == o.itemId ? this.itemNm.compareTo(o.itemNm) : this.itemId - o.itemId;
//		}
	
	// #2. Comparator 인터페이스 이용
	static class Item {
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
