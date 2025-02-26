package pr;

public class Solution_비밀코드해독 {

	public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;  // 가능한 비밀코드 조합의 수를 저장할 변수
        
        // 1부터 n까지의 숫자 중에서 5개를 선택하여 비밀코드 조합 생성
        // 비밀코드는 오름차순으로 정렬되어야 하므로, 각 숫자는 이전 숫자보다 커야 함
        // a는 첫 번째 숫자, b는 두 번째 숫자, ... , e는 다섯 번째 숫자
        for (int a = 1; a <= n - 4; a++) {
            for (int b = a + 1; b <= n - 3; b++) {
                for (int c = b + 1; c <= n - 2; c++) {
                    for (int d = c + 1; d <= n - 1; d++) {
                        for (int e = d + 1; e <= n; e++) {
                            // 현재 생성된 비밀코드 조합
                            int[] secret = {a, b, c, d, e};
                            boolean isValid = true;  // 현재 비밀코드가 모든 시도의 조건을 만족하는지 검사
                            
                            // 각 시도(q)마다 검증
                            for (int i = 0; i < q.length; i++) {
                                int count = 0;  // 현재 시도에서 맞춘 숫자의 개수
                                
                                // 비밀코드의 각 숫자와
                                for (int j = 0; j < 5; j++) {
                                    // 시도한 숫자들을 비교
                                    for (int k = 0; k < 5; k++) {
                                        if (secret[j] == q[i][k]) {
                                            count++;  // 일치하는 숫자 발견 시 카운트 증가
                                            break;    // 한 숫자는 한 번만 카운트 (중복 방지)
                                        }
                                    }
                                }
                                
                                // 현재 시도에서 맞춘 개수가 시스템 응답(ans)과 다르면
                                if (count != ans[i]) {
                                    isValid = false;  // 현재 비밀코드는 조건 불만족
                                    break;           // 남은 시도 검증 불필요
                                }
                            }
                            
                            // 모든 시도의 조건을 만족하는 비밀코드인 경우
                            if (isValid) {
                                answer++;  // 가능한 비밀코드 조합 개수 증가
                            }
                        }
                    }
                }
            }
        }
        return answer;  // 찾은 모든 가능한 비밀코드 조합의 개수 반환
    }

}
