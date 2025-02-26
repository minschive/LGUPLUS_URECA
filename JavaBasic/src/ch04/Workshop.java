package ch04;

import java.util.Scanner;

public class Workshop {

	public static void main(String[] args) {
		boolean run = true;
		int balance = 0;
		Scanner sc = new Scanner(System.in);
		
		while(run) {
			System.out.println("-------------------------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
			System.out.println("-------------------------------");
			System.out.println("선택> ");
			
			int number = sc.nextInt();
			int money = 0;
			
			switch(number) {
			case 1:
				System.out.println("예금액> ");
				money = sc.nextInt();
				balance += money;
				break;
			
			case 2:
				System.out.println("출금액> ");
				money = sc.nextInt();
				balance -= money;
				break;
				
			case 3:
				System.out.println("잔고> " + balance);
				break;
			
			case 4:
				run = false;
				break;
			}
		}
		
		sc.close();
	}

}
