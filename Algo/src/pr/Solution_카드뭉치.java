package pr;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution_카드뭉치 {

	public static void main(String[] args) {
		String[] cards1 = {"i", "drink", "water"};
		String[] cards2 = {"want", "to"};
		String[] goal = {"i", "want", "to", "drink", "water"};
		String answer = new Solution_카드뭉치().solution(cards1, cards2, goal);
		System.out.println(answer);

	}

	public String solution(String[] cards1, String[] cards2, String[] goal) {
        
		Queue<String> cardDeque1 = new ArrayDeque<>(Arrays.asList(cards1));
		Queue<String> cardDeque2 = new ArrayDeque<>(Arrays.asList(cards2));
		Queue<String> goalDeque = new ArrayDeque<>(Arrays.asList(goal)); // 완성할 기준
		
		while(!goalDeque.isEmpty()) {
			if(!cardDeque1.isEmpty() && cardDeque1.peek().equals(goalDeque.peek())) { // card1 에서 가능한지
				cardDeque1.poll();
				goalDeque.poll();
			} else if(!cardDeque2.isEmpty() && cardDeque2.peek().equals(goalDeque.peek())) { // card2 에서 가능한지
				cardDeque2.poll();
				goalDeque.poll();
			} else {
				break;
			}
		}
//		return goalDeque.isEmpty() ? "Yes" : "No";  
		
		if(goalDeque.isEmpty()) return "Yes";
		else  return "No";
    }
}
