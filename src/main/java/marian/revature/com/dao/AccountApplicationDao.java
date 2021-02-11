package marian.revature.com.dao;

import java.util.List;

import marian.revature.com.models.AccountApplication;

public interface AccountApplicationDao {
	
	List<AccountApplication> getAllAccountsApplications(); 
	AccountApplication getAccountApplicationByUserIdAndType(String userId, String accountType); 
	boolean denyApplication(String userId, String accountType); 
	boolean approveApplication(String userId, String accountType); 
	String getStatusForAccountType(int userId, String accountType); 
	void applyForAccount(int userId, String accountType); 

}
