package banking;

import java.io.Serializable;
import java.math.BigDecimal;

public class HighCreditAccount extends Account implements ICustomDefine, Serializable{

	double interest;
	int interestPer;
	double creditInt;
	String credit;
	
	public HighCreditAccount(String account, String name, int balance, int interestPer
			, String credit) {
		super(account, name, balance);
		this.interest = new BigDecimal(interestPer).movePointLeft(2).doubleValue();
		this.interestPer = interestPer;
		this.credit = credit.toUpperCase();
		if(credit.toUpperCase().equals("A")) creditInt = A;
		else if(credit.toUpperCase().equals("B")) creditInt = B;
		else if(credit.toUpperCase().equals("C")) creditInt = C;
	}

	@Override
	void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자 > " + interestPer + "%");
		System.out.println("신용등급 > " + credit);
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
		System.out.println("신용등급(A,B,C등급) :");
		credit = BankingSystemMain.sc.nextLine();
		
		return new HighCreditAccount(account, name, balance, interestPer, credit);
	}

	@Override
	void depositMoney(int money) {
		this.balance += (money + Math.floor(this.balance * this.interest)
		+ Math.floor(this.balance * this.creditInt));
	}
	
	
}
