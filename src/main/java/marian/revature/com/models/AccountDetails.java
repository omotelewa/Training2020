package marian.revature.com.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountDetails {
	
	private String accountType;
	private int userId;
	private int accountId;
	private float accountBalance; 
	private String accountDate;
	
	
	public String getAccountType() {
		return accountType;
	}
	
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public void setAccountCreationDate(){
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
		this.accountDate = formatter.format(date);
	}
	
	public void setAccountCreationDate(String dateStr){
		this.accountDate = dateStr;
	}
	
	public String getAccountCreationDate(){
		return this.accountDate;
	}
	
	public void setBalance(float bal){
		this.accountBalance = bal;
	}
	
	public float getAccountBalance(){
		return this.accountBalance;
	}


	@Override
	public String toString() {
		return "AccountDetails [accountType=" + accountType + ", userId=" + userId + ", accountId=" + accountId
				+ ", accountDate=" + accountDate + "]";
	}
	
	public static void resultSetMapper(AccountDetails ad, ResultSet rs) throws SQLException{
		int user_id = rs.getInt("user_id");
		int account_id = rs.getInt("account_id");
		float balance = rs.getFloat("account_balance");
		String accountType = rs.getString("account_type");
		String accountCreationDate = rs.getString("account_creation_date");
	
		ad.setAccountId(account_id);
		ad.setUserId(user_id);
		ad.setBalance(balance);
		ad.setAccountType(accountType);
		ad.setAccountCreationDate(accountCreationDate);
		
	}
	

	
}
