package marian.revature.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import marian.revature.com.models.AccountApplication;
import marian.revature.com.utility.ConnectionUtil;
import marian.revature.com.utility.InputValidationUtil;

public class AccountTransactionsDaoImpl implements AccountTransactionsDao {
	
	private AccountApplicationDaoImpl applicationDao = new AccountApplicationDaoImpl();
	
	public static Logger LOG = Logger.getLogger(AccountTransactionsDaoImpl.class.getName());


	@Override
	public void deposit(String accountType) {
		float amount = InputValidationUtil.getUserAmount("Please enter an amount you would like to deposit greater than $0.00"); 
		// sql 
	}


	@Override
	public void withdraw(String accountType) {
		float amount = InputValidationUtil.getUserAmount("Please enter an amount you would like to withdraw greater than $0.00"); 
		// sql 
	}
	

	@Override
	public void transferMoney(String accountTypeFrom, float amountFrom, String accountTypeTo, float amountTo) {
		
		// sql 
	}
	
	@Override
	public float getAccountBalance(int userId, String accountType) {
		float amount = 0; 
		
		return amount;
		
	}

	@Override
	public boolean createAccount(int userId) {
		LOG.info("Creating Account for User ID " + userId);
		String userResponse = InputValidationUtil.promptUser("Will this be a checking account?", "Yes", "No");
		float amount = InputValidationUtil
				.getUserAmount("Please enter the amount of money you would like to deposit");
		if (userResponse.contentEquals("yes")) { // checking account
			String applicationStatus  = applicationDao.getStatusForAccountType(userId, "checkings");
			System.out.println("Your application status for this CHECKINGS ACCOUNT: " + applicationStatus);
			if (!applicationStatus.contentEquals("APPROVED")){
				System.out.println("We cannot create a checking accounts yet. Please see a bank rep. ");
				return false; 
			}
			createAccountHelperSQL(userId, ConnectionUtil.getCurrentDateString(), amount, "checkings"); 
			System.out.printf("Great. Creating a checkings account in the amount of $.2f\n", amount);
		} else if (userResponse.contentEquals("no")) { // savings account
			String applicationStatus  = applicationDao.getStatusForAccountType(userId, "savings");
			System.out.println("Your application status for this SAVINGS ACCOUNT: " + applicationStatus);
			if (!applicationStatus.contentEquals("APPROVED")){
				System.out.println("We cannot create a savings accounts yet. Please see a bank rep. ");
				return false; 
			}
			createAccountHelperSQL(userId, ConnectionUtil.getCurrentDateString(), amount, "savings"); 
			System.out.printf("Great. Creating a savings account in the amount of $.2f\n", amount);

		} else {
			System.out.println("Exiting the application.");
			System.exit(0);
		}
		return true;
	}


	@Override
	public AccountApplication getAccountApplicationByUserIdAndType(String userId, String accountType) {
		
		return null;
	}
	
	private static void createAccountHelperSQL(int userId, String accountCreationDate, float accountBalance, String accountType){
		LOG.info("Creating Account for specifically a " + accountType + " for user id " + userId);

		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "INSERT INTO project0.accounts (user_id, account_creation_date, account_balance,account_type) VALUES (?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, accountCreationDate);
			pstmt.setFloat(3, accountBalance);
			pstmt.setString(4, accountType);
			pstmt.execute();
		}catch (Exception e){
			LOG.error(e.getMessage());
		}

		
	}

	
}
