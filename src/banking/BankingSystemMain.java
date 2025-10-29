package banking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain implements ICustomDefine{

	public static Scanner sc = new Scanner(System.in);

	public static void menuShow() {
		
		System.out.println("-----Menu------");
		System.out.println("1. 계좌계설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 전체계좌정보출력");
		System.out.println("5. 계좌정보삭제");
		System.out.println("6. 프로그램종료");
	}
	
	public static void main(String[] args) {

		//AccountManager acM = new AccountManager(50);
		AccountManager acM = new AccountManager();
		
		acM.readAccInfo();
		
		while (true) {
			
			menuShow();
			
			try {
				int cNum = sc.nextInt();
				sc.nextLine();
				
				if(cNum < MAKE || cNum > EXIT) {
					throw new MenuSelectException();
				}
				
				switch (cNum) {
					case MAKE: 
					{
						acM.makeAccount();
						break;
					}
					case DEPOSIT: 
					{
						acM.depositMoney();
						break;
					}
					case WITHDRAW: 
					{
						acM.withdrawMoney();
						break;
					}
					case INQUIRE: 
					{
						acM.showAccInfo();
						break;
					}
					case DELETE: 
					{
						acM.deleteAccount();
						break;
					}
					case EXIT: 
					{
						acM.saveAccInfo();
						System.out.println("프로그램을 종료합니다.");
						return;
					}
					/*
					case AUTOSAVE: 
					{
						//acM.autoSave();
						break;
					}
					 */
				}
			}
			catch (InputMismatchException e) {
				System.out.println("숫자만 입력가능합니다.");
				sc.nextLine();
			}
			catch (MenuSelectException e) {
				System.out.println(e.getMessage());
				System.out.println("메뉴는 1~5사이의 정수를 입력하세요.");
			}
		}
	}
}
