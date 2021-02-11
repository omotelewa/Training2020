package marian.revature.com.dao;

import marian.revature.com.models.AccountApplication;

public interface AccountTransactionsDao {
	
	void deposit(String accountType); 
	void withdraw(String accountType);
	float getAccountBalance(int userId, String accountType); 
	void transferMoney(String accountTypeFrom, float amountFrom, String accountTypeTo, float amountTo); 
	boolean createAccount(int userId); 
	AccountApplication getAccountApplicationByUserIdAndType(String userId, String accountType); 


}
