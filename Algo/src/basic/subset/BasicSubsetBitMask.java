package basic.subset;

public class BasicSubsetBitMask {

	// 기본 부분집합
	// 부분집합의 경우의 수는 모든 가능한 조합의 수의 합
	
	static int[] src = {1, 2, 3, 4, 5};
	// tgt 배열 X <= 정해진 수의 선택을 하는 것이 아니라, 가능한 모든 경우를 선택
	// 조합은 src, tgt 맨 앞부터 선택/비선택을 해 가면서 tgt 를 채우면 끝이지만
	// 부분집합은 src 의 맨 앞부터 선택/비선택을 끝까지 계속 한다.
	
//	static boolean[] select = new boolean[src.length]; // 선택, 비선택 상태를 저장하는 자료 구조
	
	public static void main(String[] args) {
		subset(0, 0); // 맨 앞자리부터
	}
	
	static void subset(int srcIdx, int mask) {
		// 기저조건
		if( srcIdx == src.length ) {
			// 부분집합 경우가 완성
			printSubset(mask);
			return;
		}
		
		// 현 srcIdx 를 선택, 비선택
		subset(srcIdx + 1, mask | 1 << srcIdx); // 선택
		subset(srcIdx + 1, mask); // 비선택
	}
	
	// 부분집합이 완성되면 select 배열을 기준으로 src 의 선택된 수 출력
	static void printSubset(int mask) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < src.length; i++) {
			if( (mask & 1 << i) != 0 ) sb.append(src[i]).append(" ");
		}
		sb.append(" -> " + mask + "[" + Integer.toBinaryString(mask) +"]\n");
		System.out.println(sb);
	}
}