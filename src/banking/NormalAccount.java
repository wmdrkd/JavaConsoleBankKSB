package banking;

import java.io.Serializable;
import java.math.BigDecimal;

public class NormalAccount extends Account implements Serializable{

	double interest;
	int interestPer;
	
	public NormalAccount(String account, String name, int balance, int interestPer) {
		super(account, name, balance);
		//2를 소수점을 왼쪽으로 2칸이동한 것을 더블타입으로 변환
		this.interest = new BigDecimal(interestPer).movePointLeft(2).doubleValue();
		this.interestPer = interestPer;
	}

	@Override
	void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자 > " + interestPer + "%");
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
		
		return new NormalAccount(account, name, balance, interestPer);
	}

	@Override
	void depositMoney(int money) {
		this.balance += (money + Math.floor(this.balance * this.interest));
	}
	
	
}
