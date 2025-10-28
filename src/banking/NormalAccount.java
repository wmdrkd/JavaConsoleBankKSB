package banking;

import java.math.BigDecimal;

public class NormalAccount extends Account{

	double interest;
	int interestPer;
	
	public NormalAccount(String account, String name, int balance, int interest) {
		super(account, name, balance);
		//2를 소수점을 왼쪽으로 2칸이동한 것을 더블타입으로 변환
		this.interest = new BigDecimal(interest).movePointLeft(2).doubleValue();
		this.interestPer =interest;
	}

	@Override
	void showAccInfo() {
		System.out.println("-------------");
		super.showAccInfo();
		System.out.println("기본이자 > " + interestPer + "%");
		System.out.println("-------------");
	}
}
