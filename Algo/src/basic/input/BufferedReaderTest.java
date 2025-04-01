package basic.input;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BufferedReaderTest {

	public static void main(String[] args) throws Exception {
		// BufferedReader ( InputStreamReader() )
		// 처리 코드는 Scanner 에 의해 다소 복잡하지만, 매우 빠른 성능을 보장
		
		// 1 2 3 4 5
//		{
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			String str = br.readLine(); // "1 2 3 4 5"
//			
//			int[] input = new int[5];
//			StringTokenizer st = new StringTokenizer(str);
//			
//			for(int i = 0; i < 5; i++) {
//				input[i] = Integer.parseInt(st.nextToken());
//			}
//			
//			System.out.println(Arrays.toString(input));
//		}
		
		// 1 A 3 B 5
//		{
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			String str = br.readLine(); // "1 A 3 B 5"
//			
//			char[] input = new char[5];
//			StringTokenizer st = new StringTokenizer(str);
//			
//			for(int i = 0; i < 5; i++) {
//				input[i] = st.nextToken().charAt(0);
//			}
//			
//			System.out.println(Arrays.toString(input));
//		}
		
		// ABCDE
//		{
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			String str = br.readLine(); // "ABCDE"
//			
//			char[] input = str.toCharArray();
//			
//			System.out.println(Arrays.toString(input));
//		}
		
/*
 5
1 2 3 4 5
*/
		
//		{
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			int N = Integer.parseInt(br.readLine());
//			int[] input = new int[N];
//			
//			String str = br.readLine();
//			StringTokenizer st = new StringTokenizer(str);
//			
//			for(int i = 0; i < N; i++) {
//				input[i] = Integer.parseInt(st.nextToken());
//			}
//			System.out.println(N);
//			System.out.println(Arrays.toString(input));
//		}
		
/*
5
ABCDE
*/
		
//		{
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			int N = Integer.parseInt(br.readLine());
//			char[] input = br.readLine().toCharArray();
//
//			System.out.println(N);
//			System.out.println(Arrays.toString(input));
//		}
		
/*      
5
1 2 3 4 5
6 7 8 9 0
1 2 3 4 5
6 7 8 9 0
1 2 3 4 5
*/
		// 첫 라인에서 읽은 N 만큼 가로 세로가 N 인 2차원 배열
//		{
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			int N = Integer.parseInt(br.readLine());
//			
//			int[][] input = new int[N][N];
//			
//			for(int i = 0; i < N; i++) {
//				String str = br.readLine();
//				StringTokenizer st = new StringTokenizer(str);
//				for (int j = 0; j < N; j++) {
//					input[i][j] = Integer.parseInt(st.nextToken());
//				}
//			}
//			
//			System.out.println(N);
//			
//			// 1 개씩 확인
//			for(int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(input[i][j] + " ");
//				}
//				System.out.println();
//			}
//			
//			// 라인 단위로 확인
//			for(int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(input[i]));
//			}
//		}
		
/*      
3 5
1 2 3 4 5
6 7 8 9 0
1 2 3 4 5
*/				
		// 첫 줄에 가로, 세로 각각 주어질 때
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] input = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				st = new StringTokenizer(str);
				for (int j = 0; j < M; j++) {
					input[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.print(N);
			System.out.print(" ");
			System.out.println(M);
			
			// 1 개씩 확인
			for(int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					System.out.print(input[i][j] + " ");
				}
				System.out.println();
			}
			
			// 라인 단위로 확인
			for(int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(input[i]));
			}
		}
		
		// 출력에 대한 자료 구조
		// 입력 자료 >>>> 출력 자료
		// 출력 자료구조가 간단한 정수 정도면 별도의 자료 구조가 필요 X
		// 출력이 문자열이고 크다 + 계속 증가한다.
		// - BufferedWriter : 코드가 길다.
		// - StringBuilder : 코드가 짧다.
	}

}
