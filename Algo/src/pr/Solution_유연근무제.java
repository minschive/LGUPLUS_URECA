package pr;

import java.util.*;
import java.io.*;

public class Solution_유연근무제 {
	
	public static void main(String[] args) {
		int[] schedules = {700, 800, 1100};
		int[][] timelogs = {{710, 2359, 1050, 700, 650, 631, 659},
				            {800, 801, 805, 800, 759, 810, 809}, 
				            {1105, 1001, 1002, 600, 1059, 1001, 1100}};
		int startday = 5;
		int answer = new Solution_유연근무제().solution(schedules, timelogs, startday);
		System.out.println(answer);

	}
	
	public int solution(int[] schedules, int[][] timelogs, int startday) {
        int employee = schedules.length;  // 전체 직원 수
        int answer = 0;  // 규정을 준수한 직원 수를 카운트
        
        // 각 직원별로 근무시간 체크
        for (int e = 0; e < employee; e++) {
            boolean check = true;  // 해당 직원의 규정 준수 여부
            int limit = convertTime(schedules[e] + 10);  // 기준 퇴근시간 + 10분을 분 단위로 변환
            
            // 7일간의 퇴근 시간을 확인
            for (int day = 0; day < 7; day++) {
                // 실제 요일 계산 (startday 기준으로 조정)
                int realDay = (startday + day - 1) % 7;
                
                // 평일(월~금)이고 퇴근 시간이 제한 시간을 초과한 경우
                if (realDay < 5 && convertTime(timelogs[e][day]) > limit) {
                    check = false;  // 규정 위반
                    break;
                }
            }
            // 규정을 준수한 경우 카운트 증가
            if (check) answer++;
        }
        return answer;
    }
    
    static int convertTime(int time) {
        int hour = time / 100;    // 시간 추출
        int minute = time % 100;  // 분 추출
        return hour * 60 + minute;  // 전체 시간을 분 단위로 변환
    }
    
}
