package banking.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;

public class AccountManager extends MyConnection{
	
	String user;
	String pass;
	String query;
	String id;
	String account;
	String name;
	int balance;
	int rate;
	
	public AccountManager(String user, String pass) {
		super(user, pass);
	}

	@Override
	public void dbClose() {
		super.dbClose();
	}

	void makeAccount() {
		System.out.println("***신규계좌개설***");
		System.out.println("계좌번호 :");
		account = BankingSystemMain.sc.nextLine();
		System.out.println("고객이름 :");
		name = BankingSystemMain.sc.nextLine();
		System.out.println("잔고 :");
		balance = BankingSystemMain.sc.nextInt();
		System.out.println("기본이자%(정수형태로입력) :");
		rate = BankingSystemMain.sc.nextInt();
		
		try {
			query = "insert into banking values "
					+ " (seq_banking_idx.nextval, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, account);
			psmt.setString(2, name);
			psmt.setInt(3, balance);
			psmt.setDouble(4, rate * 0.01);
		}
		catch (SQLException e) {
			System.out.println("쿼리실행 중 예외발생");
			e.printStackTrace();
		}
		
		System.out.println("계좌계설이 완료되었습니다.");
		System.out.println();
	}
	
	
	void deleteAccount() {
		System.out.println("***계좌정보삭제***");
		System.out.println("삭제할 계좌번호를 입력하세요.");
		System.out.println("계좌번호 : ");
		account = BankingSystemMain.sc.nextLine();
		
		try {
			query = "delete from banking where account = ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, account);
		}
		catch (SQLException e) {
			System.out.println("쿼리실행 주 예외발생");
			e.printStackTrace();
		}
	}
	
	void depositMoney() {
		int deposit;
		
		try {
			System.out.println("***입 금***");
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
			
			query = "update banking "
					+ " set balance = ?"
					+ " where account = ?" ;
			psmt = con.prepareStatement(query);
			psmt.setInt(1, (int)Math.floor(balance + (balance * rate) + deposit));
			psmt.setString(2, account);
		
			System.out.println("입금이 완료되었습니다.");
			System.out.println();
		}
		catch (SQLException e) {
			System.out.println("쿼리실행 중 예외 발생");
			e.printStackTrace();
		}
		catch (InputMismatchException e) {
			System.out.println("숫자를 입력하세요.");
			BankingSystemMain.sc.nextLine();
		}
	}
	
	void withdrawMoney() {
		int withdraw;
		
		System.out.println("***출 금****");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.println("계좌번호 :");
		account = BankingSystemMain.sc.nextLine();
		System.out.println("출금액 :");
		withdraw = BankingSystemMain.sc.nextInt();
		BankingSystemMain.sc.nextLine();
		
		try {
			query = "update banking "
					+ " set balance = ?"
					+ " where account = ? and balacn >= ?" ;
			psmt = con.prepareStatement(query);
			psmt.setInt(1, balance - withdraw);
			psmt.setString(2, account);
			psmt.setInt(3, withdraw);
		}
		catch (SQLException e) {
			System.out.println("쿼리실행 중 예외 발생");
			e.printStackTrace();
		}
		
		System.out.println("출금이 완료되었습니다.");
		System.out.println();
		
	}
	
	void showAllAccInfo() {
		System.out.println("***전체계좌정보출력***");
		
		try {
			query = "select * from banking";
			psmt = con.prepareStatement(query);
		}
		catch (SQLException e) {
			System.out.println("쿼리실행 주 오류발생");
			e.printStackTrace();
		}
		
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}
	
	void showAccInfo() {
		System.out.println("***계좌정보출력***");
		System.out.println("정보를 출력할 계좌번호를 입력하세요 : ");
		account = BankingSystemMain.sc.nextLine();
		
		try {
			query = "select * from banking"
					+ " where account = ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, account);
		}
		catch (SQLException e) {
			System.out.println("쿼리실행 주 오류발생");
			e.printStackTrace();
		}
		
		System.out.println("선택한 계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}
	
}
