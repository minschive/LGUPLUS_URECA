package basic.array;

public class Array1 {
	public static void main(String[] args) {
		// #1. 주어진 배열의 원소들 중 제시된 규칙과 다른 항목이 몇 건 ?
		// 맨 앞부터 3개씩 묶어서 곱셈 결과가 맞으면 통과, 틀리면 wrongCnt 증가
		// 3,2,6 : 3 X 2 = 6 통과
		// 3,4,4 : 3 X 4 = 12 != 4 wrongCnt++
		{
			int[] intArray = {3,2,6, 3,4,4, 1,4,2, 2,3,6, 1,3,5, 1,5,1, 1,1,1, 2,4,2, 2,2,4};
			int wrongCnt = 0;
			
			// 구현
			int intLength = intArray.length - 2;
			for(int i = 0; i < intLength; i = i + 3) {
				if(intArray[i] * intArray[i+1] != intArray[i+2]) wrongCnt++;
			}
			
			System.out.println(wrongCnt);
		}
		
		// #2. 양 끝에서 출발해서 안쪽으로 이동하면서 각 대칭 항목이 다른 건 몇 건 ?
		// 대칭 항목이 다르면 wrongCnt++
		{
			char[] charArray = "XYZEBFFGQOVVPWGFFCEAYX".toCharArray();
			int wrongCnt = 0;
		
			// 구현
			// 배열 요소 개수가 짝수 개임을 가정하여
			int center = charArray.length / 2;
			for(int l = 0, r = charArray.length - 1; l < center; l++, r--) {
				if(charArray[l] != charArray[r]) {
					wrongCnt++;
				}
			}
			System.out.println(wrongCnt);
		}
		
		// #3. 문자열에 포함된 알파벳의 빈도수 출력, 0인 항목도 출력
		{
			String str = "abbcccddddeeeeeffffggghhiabbcccddddeeeeeffffggghhi";
			
			// 구현
			// 모든 알파벳 소문자가 몇 번 반복되었는지 출력
			// ex) a : 4
			// b : 2
			// ...
			
			// 빈도수 관리 배열
			int[] alphaCnt = new int[26]; // 해당 알파벳 - 'a', ex ) 'a'-'a' = 0, 'b'-'a' = 1, 초기값은 모두 0
			// 문자열을 char 배열로 변경 가능. String.charAt() 이용
			int strLen = str.length();
			
			for(int i = 0; i < strLen; i++) {
				alphaCnt[str.charAt(i) - 'a']++;
			}
			
			// 출력
			for(int i = 0; i < 26; i++) {
				System.out.println( (char)(i + 'a') + " : " + alphaCnt[i]);
			}
			
		}
	}

}
