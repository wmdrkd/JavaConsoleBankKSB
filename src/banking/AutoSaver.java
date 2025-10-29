package banking;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class AutoSaver extends Thread{
	
	AccountManager am = new AccountManager();

	@Override
	public void run() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("src/banking/AutoSaveAccount.txt"));
			
			HashSet<Account> accountSet = am.getAccSet();
			
			for(Account acc : accountSet) {
				out.writeObject(acc);
				System.out.println("계좌정보가 텍스트로 자동저장되었습니다.");
			}
			
			
			out.close();
		}
		catch (Exception e) {
			System.out.println("자동저장 실행 중 오류발생.");
			e.printStackTrace();
		}
	}
	
	
}

