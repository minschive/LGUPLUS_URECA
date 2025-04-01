package pr;

public class Solution_택배상자꺼내기 {
    public int solution(int n, int w, int num) {
        // 목표 상자가 있는 열의 위치를 계산
        int targetCol = getCol(num, w);
        int answer = 0;
        
        // 목표 상자부터 마지막 상자까지 순회하면서
        // 같은 열에 있는 상자의 개수를 카운트 (위에 있는 상자들을 먼저 꺼내야 하므로)
        for (int i = num; i <= n; i++) {
            if(getCol(i, w) == targetCol) answer++;
        }
        return answer;
    }
    
    private int getCol(int num, int w) {
        // 상자가 몇 번째 행에 있는지 계산 (0부터 시작)
        int row = (num - 1) / w;
        // 해당 행에서의 위치 계산 (0부터 시작)
        int pos = (num - 1) % w;
        
        // 짝수 행: 왼쪽 → 오른쪽 순서로 배치
        // 홀수 행: 오른쪽 → 왼쪽 순서로 배치 (위치 반전 필요)
        return row % 2 == 0 ? pos : (w - 1 - pos);
    }
}

