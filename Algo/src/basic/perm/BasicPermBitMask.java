package basic.perm;

import java.util.Arrays;

public class BasicPermBitMask {
	
	// 기본 순열
	static int[] src = {1, 2, 3, 4, 5};
	static int[] tgt = new int[3]; // _ _ _ <- 현재 자리를 채우기 위해 src 전체를 고려하되, 현재 자리 이전 자리를 사용된 수를 제외
	
	// 사용 X
//	static boolean[] select = new boolean[src.length]; // ex) f t f t f
	
	public static void main(String[] args) {
		perm(0, 0); // 두 번째 파라미터로 bitmask 전달 0000 0000
	}
	
	// mask 이전 단계까지의 선택, 비선택이 표현되어 있음
	
	static void perm(int tgtIdx, int mask) { // 31 bit mask 표현
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
			if( (mask & 1 << i) != 0 ) continue; // ex) i = 3  0000 0001 -> 0000 1000
			// 0000 1000
			// 0000 1100
			// -----------
			// 0000 1000
			
			tgt[tgtIdx] = src[i]; // 선택
			
//			select[i] = true; // 선택됨을 표시
			perm(tgtIdx + 1, mask | 1 << i); // 다음 자리를 위한 재귀 호출
			// 원복이 필요없음 : 전역번수가 아니고, 파라미터로 복사해서 전달이 되기 때문에
		}
	}
}

// BitMask 연산
// 0000 0000 으로 시작

// & 연산
// mask : 0000 1010 (두번째, 네번째 사용 중 index 중 1, 3 선택된 상태)
// i = 2 : 1 << 1 : 0000 0001 => 0000 0100
// 선택 여부 비교 : mask & 1 << 2
//   0000 1010
// & 0000 0100 <= 2번째 빼고 모두 0
// 결과적으로 mask 의 2(i)번째 자리가 1이면 전체 연산 결과는 0이 아닌 어떤 수,
//  2(i)번째 자리가 0이면 전체 연산 결과는 0이 된다.


// | 연산
// mask 에 표현 선택(1), 비선택(0) 표현에 i 번째 자리를 선택 (1)로 바꾸는 처리
// mask : 0000 1010 (두번째, 네번째 사용 중 index 중 1, 3 선택된 상태)
// i = 2 : 1 << 1 : 0000 0001 => 0000 0100
// mask 에 선택 처리 : mask | 1 << 2
//   0000 1010
// | 0000 0100 <= 2번째 빼고 모두 0, i 번째 자리를 빼고 나머진 변경 X, i 자리만 1이므로 결과는
// mask 의 나머지 자리는 그대로 i 자리는 무조건 1로 변경


