package ch15;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test4 {

	public static void main(String[] args) {
		// Map (key, value)
		// key 의 중복 허용 X : 이전 value 를 덮어 쓴다.
		Map<String, Integer> map = new HashMap<>();
		map.put("aaa", 50);
		map.put("aaa", 60);
		map.put("aaa", 70);
		map.put("bbb", 50);
		map.put("ccc", 50);
		map.put("ddd", 50);

		System.out.println(map.size()); // -> 4
		System.out.println(map.get("aaa")); // key 로 value 를 -> 70
		System.out.println(map.containsKey("aaa")); // key 를 포함하는지 -> true
		
		// keyset 을 이용한 순회 (map.keySet() → map 의 모든 키(key)만 모아서 Set 으로 반환)
		Set<String> keySet = map.keySet(); // 순서 X
		Iterator<String> itr = keySet.iterator();
		while(itr.hasNext()) {
			String k = itr.next(); // 다음 key 가져오기
			Integer v = map.get(k); // 키에 해당하는 값 가져오기
			System.out.println(k + " " + v);
		}
	}
}
