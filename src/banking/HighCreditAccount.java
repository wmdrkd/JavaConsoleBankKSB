package banking;

import java.math.BigDecimal;

public class HighCreditAccount extends Account implements ICustomDefine{

	double interest;
	int interestPer;
	double creditInt;
	String credit;
	
	public HighCreditAccount(String account, String name, int balance, int interest
			, String credit) {
		super(account, name, balance);
		this.interest = new BigDecimal(interest).movePointLeft(2).doubleValue();
		this.interestPer = interest;
		this.credit = credit.toUpperCase();
		if(credit.toUpperCase().equals("A")) creditInt = A;
		else if(credit.toUpperCase().equals("B")) creditInt = B;
		else if(credit.toUpperCase().equals("C")) creditInt = C;
	}

	@Override
	void showAccInfo() {
		System.out.println("-------------");
		super.showAccInfo();
		System.out.println("기본이자 > " + interestPer + "%");
		System.out.println("신용등급 > " + credit);
		System.out.println("-------------");
	}
}
