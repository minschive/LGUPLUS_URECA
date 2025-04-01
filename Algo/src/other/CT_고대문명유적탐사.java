package other;

import java.io.*;
import java.util.*;

public class CT_고대문명유적탐사 {
    // 격자판의 크기 (5x5)
    static final int SIZE = 5;
    // 현재 격자판의 상태를 저장하는 배열
    static int[][] map = new int[SIZE][SIZE];
    // 회전 및 제거 연산을 시뮬레이션하기 위한 임시 배열
    static int[][] copyMap = new int[SIZE][SIZE];

    // 8방향 이동을 위한 방향 배열 (회전 연산에 사용)
    static int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};  // 열 방향 이동
    static int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};  // 행 방향 이동
    
    // 상하좌우 4방향 이동을 위한 방향 배열 (BFS 탐색에 사용)
    static int[] dx = {0, 1, 0, -1};  // 열 방향 이동
    static int[] dy = {-1, 0, 1, 0};  // 행 방향 이동

    // 제거된 숫자를 채우기 위한 새로운 숫자들을 저장하는 큐
    static Queue<Integer> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());  // 시뮬레이션 반복 횟수
        int m = Integer.parseInt(st.nextToken());  // 채울 숫자의 개수
        StringBuilder sb = new StringBuilder();

        q = new LinkedList<>();

        // 초기 격자판 상태 입력
        for (int r = 0; r < SIZE; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < SIZE; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 제거된 칸을 채울 숫자들 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            q.offer(Integer.parseInt(st.nextToken()));
        }

        // k번의 시뮬레이션 실행
        for (int i = 0; i < k; i++) {
            RotateInfo best = null;
            // 모든 가능한 회전 중심점과 각도에 대해 시뮬레이션
            for (int r = 1; r <= 3; r++) {
                for (int c = 1; c <= 3; c++) {
                    for (int angle = 1; angle <= 3; angle++) {
                        RotateInfo candidate = process(r, c, angle);
                        // 최적의 회전 정보 갱신 (점수가 높은 순, 각도/열/행이 작은 순)
                        if (best == null || candidate.compareTo(best) < 0) {
                            best = candidate;
                        }
                    }
                }
            }
            
            int sum = best.score;
            if (sum == 0) break;  // 제거할 수 있는 그룹이 없으면 종료
            
            // 최적의 회전을 실행하고 연쇄 제거 진행
            process(best.row, best.column, best.angle);
            while (true) {
                fill();  // 빈 칸 채우기
                int curScore = bfs();  // 그룹 찾아서 제거
                if (curScore == 0) break;  // 더 이상 제거할 그룹이 없으면 종료
                sum += curScore;
            }
            sb.append(sum).append(" ");
            updateMap();  // 시뮬레이션 결과를 실제 맵에 적용
        }
        System.out.println(sb.toString());
    }

    /**
     * 임시 맵(copyMap)의 상태를 실제 맵(map)에 복사
     */
    static void updateMap() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                map[r][c] = copyMap[r][c];
            }
        }
    }

    /**
     * 주어진 위치와 각도로 회전 후 제거 점수를 계산
     * @param r 회전 중심점의 행 좌표
     * @param c 회전 중심점의 열 좌표
     * @param angle 회전 각도 (1: 90도, 2: 180도, 3: 270도)
     * @return 회전 정보와 획득 점수를 포함한 RotateInfo 객체
     */
    static RotateInfo process(int r, int c, int angle) {
        rotate(r, c, angle);
        int score = bfs();
        return new RotateInfo(score, angle, c, r);
    }

    /**
     * 지정된 중심점을 기준으로 8방향 영역을 회전
     * @param r 회전 중심점의 행 좌표
     * @param c 회전 중심점의 열 좌표
     * @param angle 회전 각도 (1: 90도, 2: 180도, 3: 270도)
     */
    static void rotate(int r, int c, int angle) {
        // 현재 맵 상태를 임시 맵에 복사
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        // 8방향의 칸들을 angle에 따라 회전
        for (int i = 0; i < 8; i++) {
            int tnr = r + dr[i];
            int tnc = c + dc[i];
            // 회전 로직 -> 현재 내 좌표에 와야 하는 요소의 좌표 계산
            // 90도 = 중심점을 기준으로 반시계방향 두칸 전의 요소를 가져온다
            // 180도 = 중심점을 기준으로 반시계방향 네칸 전의 요소를 가져온다
            // 270도 = 중심점을 기준으로 반시계방향 여섯칸 전의 요소를 가져온다
            // dr, dc 배열이 이미 반시계방향으로 지정되어 있으므로
            // 8칸을 돌면서 angle (1 = 90도, 2 = 180도, 3 = 270도) 에 2를 곱한 값을 현재 i에서 더해주면
            // 좌표를 알 수 있다. 이 때 8을 넘어가면 안되기 때문에 모듈러 연산 사용
            // 모듈러 연산을 통해서 반복되는 원형 리스트 처리 (중요한 테크닉)
            int nr = r + dr[(i + angle * 2) % 8];
            int nc = c + dc[(i + angle * 2) % 8];
            copyMap[tnr][tnc] = map[nr][nc];
        }
    }

    /**
     * BFS로 같은 숫자가 3개 이상 연속된 그룹을 찾아 제거
     * @return 제거된 칸의 개수 (점수)
     */
    static int bfs() {
    	// 방문 여부를 체크할 배열
        boolean[][] visit = new boolean[SIZE][SIZE];
        int score = 0;
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
            	// bfs 진행을 위한 큐 (좌표 저장)
                Queue<int[]> queue = new LinkedList<>();
                // 같은 값을 발견하면 좌표를 그룹에 넣어주고, 후처리한다. 이 문제의 중요한 테크닉중 하나
                List<int[]> group = new ArrayList<>();
                if (!visit[y][x]) {
                    queue.offer(new int[] { y, x });
                    group.add(new int[] { y, x });
                    visit[y][x] = true;
                }
                // BFS로 인접한 같은 숫자 찾기
                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    int r = current[0];
                    int c = current[1];
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dy[i];
                        int nc = c + dx[i];
                        // 격자판 범위 체크
                        if (nr < 0 || nr >= SIZE || nc < 0 || nc >= SIZE) continue;
                        // 같은 숫자인지 체크
                        if (copyMap[nr][nc] != copyMap[r][c]) continue;
                        // 방문 여부 체크
                        if (visit[nr][nc]) continue;
                        visit[nr][nc] = true;
                        queue.add(new int[] { nr, nc });
                        group.add(new int[] { nr, nc });
                    }
                }
                // 그룹 크기가 3 이상이면 제거 처리, 동시에 점수를 계산해서 반환 (제거된 숫자 개수가 점수이므로)
                if (group.size() >= 3) {
                    for (int[] axis : group) {
                        copyMap[axis[0]][axis[1]] = 0;
                        score++;
                    }
                }
            }
        }
        return score;
    }

    /**
     * 제거된 칸(0)을 새로운 숫자로 채움
     * 왼쪽에서 오른쪽, 아래쪽부터 위로 올라가며 채움
     * 채워지는 순서에 맞게 반복문을 적절하게 구성해야함
     */
    static void fill() {
        for (int c = 0; c < SIZE; c++) {
            for (int r = SIZE - 1; r >= 0; r--) {
                if (copyMap[r][c] == 0) {
                    copyMap[r][c] = q.poll();
                }
            }
        }
    }
}

/**
 * 회전 정보를 저장하고 비교하기 위한 클래스
 * 점수가 높은 순, 각도가 작은 순, 열이 작은 순, 행이 작은 순으로 정렬
 */
class RotateInfo implements Comparable<RotateInfo> {
    int score;   // 획득한 점수
    int angle;   // 회전 각도
    int column;  // 회전 중심점의 열 좌표
    int row;     // 회전 중심점의 행 좌표

    public RotateInfo(int score, int angle, int column, int row) {
        this.score = score;
        this.angle = angle;
        this.column = column;
        this.row = row;
    }

    @Override
    public int compareTo(RotateInfo o) {
        int diff;
        // 점수 내림차순
        if ((diff = o.score - this.score) != 0) return diff;
        // 각도 오름차순
        if ((diff = this.angle - o.angle) != 0) return diff;
        // 열 좌표 오름차순
        if ((diff = this.column - o.column) != 0) return diff;
        // 행 좌표 오름차순
        return this.row - o.row;
    }
}
