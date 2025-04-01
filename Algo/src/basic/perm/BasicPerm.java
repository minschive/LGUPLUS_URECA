package basic.perm;

import java.util.Arrays;

public class BasicPerm {
	
	// 기본 순열
	static int[] src = {1, 2, 3, 4, 5};
	static int[] tgt = new int[3]; // _ _ _ <- 현재 자리를 채우기 위해 src 전체를 고려하되, 현재 자리 이전 자리를 사용된 수를 제외
	
	// 현재 자리 이전 자리에 사용된 수
	static boolean[] select = new boolean[src.length]; // ex) f t f t f
	
	public static void main(String[] args) {
		perm(0); // tgt 의 맨 앞자리부터 시작
	}
	
	static void perm(int tgtIdx) { // tgtIdx => _ _ _
		// 기저 조건
		// 이 조건 이전의 재귀호출로 이미 tgt 배열이 완성
		if(tgtIdx == tgt.length) {
			System.out.println(Arrays.toString(tgt));
			return;
		}
		
		// 현재 파라미터로 넘어온 tgtIdx 자리에 src 로부터 채울 수를 선택, 고려해야함
		// src 전체를 대상으로 하되, select 배열에 사용 중인 것은 제외
		for(int i = 0; i < src.length; i++) {
			// 앞자리에 사용된 수라면 skip
			if(select[i]) continue;
			
			tgt[tgtIdx] = src[i]; // 선택
			
			select[i] = true; // 선택됨을 표시
			perm(tgtIdx + 1); // 다음 자리를 위한 재귀 호출
			select[i] = false; // 선택 원복
		}
	}
}
