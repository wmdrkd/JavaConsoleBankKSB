package banking;

public class SpecialAccount extends NormalAccount{

	int dCnt;
	
	public SpecialAccount(String account, String name, int balance, int interestPer) {
		super(account, name, balance, interestPer);
		dCnt = 0;
	}

	@Override
	void showAccInfo() {
		super.showAccInfo();
		System.out.println("입금한 횟수 : " + dCnt);
	}

	@Override
	Account makAccount() {
		System.out.println("계좌번호 :");
		account = BankingSystemMain.sc.nextLine();
		System.out.println("고객이름 :");
		name = BankingSystemMain.sc.nextLine();
		System.out.println("잔고 :");
		balance = BankingSystemMain.sc.nextInt();
		System.out.println("기본이자%(정수형태로입력) :");
		interest = BankingSystemMain.sc.nextInt();
		BankingSystemMain.sc.nextLine();
		
		return new SpecialAccount(account, name, balance, interestPer);
	}

	@Override
	void depositMoney(int money) {
		if(++dCnt % 2 == 0) {
			this.balance += (money + Math.floor(this.balance * this.interest) + 500);
		}
		else {
			this.balance += (money + Math.floor(this.balance * this.interest));
		}
	}
	
	
}
