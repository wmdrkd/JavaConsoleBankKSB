package banking;

public abstract class Account {
	
	String account;
	String name;
	int balance;
	
	public Account(String account, String name, int balance) {
		this.account = account;
		this.name = name;
		this.balance = balance;
	}
	
	void showAccInfo() {
		System.out.println("계좌번호 > " + this.account);
		System.out.println("고객이름 > " + this.name);
		System.out.println("잔고 > " + this.balance);
	}

	@Override
	public int hashCode() {
		return account.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		Account acc = (Account)obj;
		return this.account.equals(acc.account);
	}
	
	
}
