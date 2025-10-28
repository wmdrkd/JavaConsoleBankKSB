package banking;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;

public class AccountManager{
	//private Account[] acc;
	//private int numOfAccount;
	private HashSet<Account> accSet;

	/*
	public AccountManager(int n) {
		this.acc = new Account[n];
		numOfAccount = 0;
	}
	*/
	public AccountManager() {
		accSet = new HashSet<Account>();
	}
	
	void makeAccount() {
		String account;
		String name;
		int balance;
		int accChoice;
		int interest;
		String credit;
		
		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		accChoice = BankingSystemMain.sc.nextInt();
		BankingSystemMain.sc.nextLine();
		
		if(accChoice == 1) {
			System.out.println("계좌번호 :");
			account = BankingSystemMain.sc.nextLine();
			System.out.println("고객이름 :");
			name = BankingSystemMain.sc.nextLine();
			System.out.println("잔고 :");
			balance = BankingSystemMain.sc.nextInt();
			System.out.println("기본이자%(정수형태로입력) :");
			interest = BankingSystemMain.sc.nextInt();
			BankingSystemMain.sc.nextLine();
			
			//acc[numOfAccount++] = new NormalAccount(account, name, balance, interest);
			NormalAccount newAcc = new NormalAccount(account, name, balance, interest);
			if(!accSet.add(newAcc)) {
				System.out.println("중복계좌발견됨.");
				System.out.println("덮어쓸까요?(Y or N)");
				String cStr = BankingSystemMain.sc.nextLine();
				
				if(cStr.toUpperCase().equals("Y")) {
					Iterator<Account> itr = accSet.iterator();
					while(itr.hasNext()) {
						Account acc = itr.next();
						if(acc.account.equals(newAcc.account)) {
							itr.remove();
							break;
						}
					}
					accSet.add(newAcc);
					System.out.println("새로운 정보로 갱신되었습니다.");
					System.out.println();
					return;
				}
				else {
					System.out.println("기존정보를 유지합니다.");
					return;
				}
			}
			System.out.println("계좌계설이 완료되었습니다.");
			System.out.println();
		}
		else if(accChoice == 2) {
			System.out.println("계좌번호 :");
			account = BankingSystemMain.sc.nextLine();
			System.out.println("고객이름 :");
			name = BankingSystemMain.sc.nextLine();
			System.out.println("잔고 :");
			balance = BankingSystemMain.sc.nextInt();
			System.out.println("기본이자%(정수형태로입력) :");
			interest = BankingSystemMain.sc.nextInt();
			BankingSystemMain.sc.nextLine();
			System.out.println("신용등급(A,B,C등급) :");
			credit = BankingSystemMain.sc.nextLine();
			
			/*
			acc[numOfAccount++] = new HighCreditAccount(account, name, balance, interest
					, credit);
			 */
			accSet.add(new HighCreditAccount(account, name, balance, interest, credit));
			System.out.println("계좌계설이 완료되었습니다.");
			System.out.println();
		}
		
	}
	
	void deleteAccount() {
		System.out.println("***계좌정보삭제***");
		System.out.println("삭제할 계좌번호를 입력하세요.");
		System.out.println("계좌번호 : ");
		String accNum = BankingSystemMain.sc.nextLine();
		
		boolean accF = false;
		Iterator<Account> itr = accSet.iterator();
		while(itr.hasNext()) {
			Account rAcc = itr.next();
			if(rAcc.account.equals(accNum)) {
				itr.remove();
				System.out.println("계좌를 삭제하였습니다.");
				return;
			}
		}
		System.out.println("일치하는 계좌가 없습니다.");
		System.out.println();
	}
	
	void depositMoney() {
		String account;
		int deposit;
		
		try {
			System.out.println("***입 금****");
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			System.out.println("계좌번호 :");
			account = BankingSystemMain.sc.nextLine();
			System.out.println("입금액 :");
			deposit = BankingSystemMain.sc.nextInt();
			BankingSystemMain.sc.nextLine();
			
			if(deposit <= 0) {
				System.out.println("음수는 입금할 수 없습니다.");
				return;
			}
			if(deposit % 500 != 0) {
				System.out.println("500원 단위로 입금 가능합니다.");
				return;
			}
			
			/*
			for(int i = 0; i < numOfAccount; i++) {
				if(acc[i].account.equals(account)) {
					if(acc[i] instanceof NormalAccount) {
						NormalAccount nAcc = (NormalAccount)acc[i];
						nAcc.balance += (deposit + Math.floor(nAcc.balance * nAcc.interest));
					}
					else if(acc[i] instanceof HighCreditAccount) {
						HighCreditAccount hAcc = (HighCreditAccount)acc[i];
						hAcc.balance += (deposit + Math.floor(hAcc.balance * hAcc.interest)
						+ Math.floor(hAcc.balance * hAcc.creditInt));
					}
				}
			}
			*/
			for(Account acc : accSet) {
				if(acc instanceof NormalAccount) {
					NormalAccount nAcc = (NormalAccount)acc;
					nAcc.balance += (deposit + Math.floor(nAcc.balance * nAcc.interest));
				}
				else if(acc instanceof HighCreditAccount) {
					HighCreditAccount hAcc = (HighCreditAccount)acc;
					hAcc.balance += (deposit + Math.floor(hAcc.balance * hAcc.interest)
					+ Math.floor(hAcc.balance * hAcc.creditInt));
				}
			}
			System.out.println("입금이 완료되었습니다.");
			System.out.println();
		}
		catch (InputMismatchException e) {
			System.out.println("숫자를 입력하세요.");
			BankingSystemMain.sc.nextLine();
		}
		
	}
	
	void withdrawMoney() {
		String account;
		int withdraw;
		
		System.out.println("***출 금****");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.println("계좌번호 :");
		account = BankingSystemMain.sc.nextLine();
		System.out.println("출금액 :");
		withdraw = BankingSystemMain.sc.nextInt();
		BankingSystemMain.sc.nextLine();
		
		if(withdraw < 0) {
			System.out.println("음수는 출금할 수 없습니다.");
			return;
		}
		
		if(withdraw % 1000 != 0) {
			System.out.println("1000원 단위로 출금할 수 있습니다.");
			return;
		}
		
		/*
		for(int i = 0; i < numOfAccount; i++) {
			if(acc[i].account.equals(account)) {
				if(acc[i].balance < withdraw) {
					System.out.println("잔고가 부족합니다. 금액전체를 출금할까요? (Y or N)");
					String choChar = BankingSystemMain.sc.nextLine();
					
					if(choChar.toUpperCase().equals("Y")) {
						acc[i].balance -= acc[i].balance;
					}
					else {
						System.out.println("출금이 취소 되었습니다.");
						return;
					}
				}
				else {
					acc[i].balance -= withdraw;
				}
				System.out.println("출금이 완료되었습니다.");
				System.out.println();
			}
		}
		*/
		
		for(Account acc : accSet) {
			if(acc.balance < withdraw) {
				System.out.println("잔고가 부족합니다. 금액전체를 출금할까요? (Y or N)");
				String choChar = BankingSystemMain.sc.nextLine();
				
				if(choChar.toUpperCase().equals("Y")) {
					acc.balance -= acc.balance;
				}
				else {
					System.out.println("출금이 취소 되었습니다.");
					return;
				}
			}
			else {
				acc.balance -= withdraw;
			}
			System.out.println("출금이 완료되었습니다.");
			System.out.println();
		}
	}
	
	void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for(Account acc : accSet) {
			acc.showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}
}
