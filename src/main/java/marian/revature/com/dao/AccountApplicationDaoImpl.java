package marian.revature.com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import marian.revature.com.models.AccountApplication;
import marian.revature.com.utility.ConnectionUtil;
import marian.revature.com.utility.InputValidationUtil;

public class AccountApplicationDaoImpl implements AccountApplicationDao {

	public static Logger LOG = Logger.getLogger(AccountApplicationDaoImpl.class.getName());

	@Override
	public List<AccountApplication> getAllAccountsApplications() {
		LOG.info("GETTING ALL ACCOUNTS");
		List<AccountApplication> aa = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT * FROM project01.account_application";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				AccountApplication a = new AccountApplication();
				AccountApplication.resultSetMapper(a, rs);
				aa.add(a);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		return aa;
	}

	@Override
	public AccountApplication getAccountApplicationByUserIdAndType(String userId, String accountType) {
		LOG.info("GETTING ACCOUNT BY USER ID and Account type");
		AccountApplication aa = null;
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT * FROM project01.account_application where user_id = ? AND account_type = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, accountType);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				aa = new AccountApplication();
				AccountApplication.resultSetMapper(aa, rs);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		return aa;
	}

	@Override
	public boolean denyApplication(String userId, String accountType) {
		LOG.info("Denying application for user " + userId + " for the account type: " + accountType);
		boolean success = false;

		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "UPDATE project0.account_application SET status = ? WHERE user_id = ? AND account_type = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "DENIED");
			pstmt.setString(2, userId);
			pstmt.setString(3, accountType);
			pstmt.execute();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean approveApplication(String userId, String accountType) {
		LOG.info("Approving application for user " + userId + " for the account type: " + accountType);
		boolean success = false;

		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "UPDATE project0.account_application SET status = ? WHERE user_id = ? AND account_type = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "APPROVED");
			pstmt.setString(2, userId);
			pstmt.setString(3, accountType);
			pstmt.execute();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}

		return success;
	}

	@Override
	public String getStatusForAccountType(int userId, String accountType) {
		LOG.info("CHECKING THe status for this account application FOR THIS bank account");
		String accountStatus = null;
		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "SELECT account_status FROM project01.account_application where user_id = ? AND account_type = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, accountType);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				accountStatus = rs.getString("account_status"); 
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage());
		}
		return accountStatus;
	}

	@Override
	public void applyForAccount(int userId, String accountType) {
		
		LOG.info("Creating An application for User ID " + userId + " For type of " + accountType + " account ");

		try (Connection conn = ConnectionUtil.getConnectionfromPostgres()) {
			String sql = "INSERT INTO project0.account_application (user_id, status, account_type) VALUES (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, "waiting");
			pstmt.setString(3, accountType);
			pstmt.execute();
			LOG.info("Application created User ID " + userId + " For type of " + accountType + " account. Now it is in waiting status! ");
		}catch (Exception e){
			LOG.error(e.getMessage());
		}

				
	}

}
