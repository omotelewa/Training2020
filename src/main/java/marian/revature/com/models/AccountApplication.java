package marian.revature.com.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * 
 * Customers should be able to apply for joint accounts (meaning checkings and savings)  
 * STATUS = WAITING, APPROVED, DENIED
 * JOINT = 1/0
 * application_id
 * account_type
 * user_id
 */
public class AccountApplication {
	
	public String status; 
	public String applicationType; 
	public int applicationId; 
	public int userId;
	
	public AccountApplication() {
		
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Application [status=" + status + ", applicationType=" + applicationType + ", applicationId="
				+ applicationId + ", userId=" + userId + "]";
	} 
	
	public static void resultSetMapper(AccountApplication aa, ResultSet rs) throws SQLException{
		int userId = rs.getInt("user_id");
		int applicationId = rs.getInt("application_id");
		String status = rs.getString("status");
		String accountType = rs.getString("application_type");
		aa.setUserId(userId);
		aa.setApplicationId(applicationId); 
		aa.setStatus(status); 
		aa.setApplicationType(accountType); 
	}

	

}
