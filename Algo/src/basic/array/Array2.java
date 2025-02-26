package basic.array;

public class Array2 {
	// Circular Array
	public static void main(String[] args) {
		char[] input = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int len = input.length;
		
		// 전체 길이의 2배 순회하면서 출력
		{
			for(int i = 0; i < len * 2; i++) { // len * 2 외에도 더 큰 임의의 수도 사용 가능
				System.out.print(input[i % len] + " ");
			}
			System.out.println();
		}
		
		// Circular Array
		// 'A', 'B', 'C', 'D', 'E', 'F', 'G' 이 원을 그리고, 'A' 시작해서 특정 수 (total) 만큼 출력
		{
			int total = 20; // 전체 출력 횟수 -> 출력 20개 : A B C D E F G A B C D E F G A B C D E F
			
			// for
			for(int i = 0; i < total; i++) {
				System.out.print(input[i % len] + " ");
			}
			System.out.println();
			
			// while
			int i = 0;
			while(true) {
				if(i == total) break;
				System.out.print(input[i % len] + " ");
				i++;
			}
		}
	}

}
