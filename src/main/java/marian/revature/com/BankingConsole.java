package marian.revature.com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import marian.revature.com.dao.UserDaoImpl;
import marian.revature.com.models.Login;
import marian.revature.com.models.User;
import marian.revature.com.utility.ConnectionUtil;
import marian.revature.com.utility.InputValidationUtil;


public class BankingConsole { 

	public static void main(String[] args) throws SQLException, IOException, InterruptedException {
		ConnectionUtil.createSchemas(); 
		
		Scanner scanner = new Scanner(System.in); 
		String response = null;
		System.out.println(" Welcome To The Barclays Bank  > + ");
		while (true){
			response = displayLoginPage(); 
			if (response != null){
				break;
			}else{
				System.out.println("No action was taken... Trying again.."); 
			}
		}
	
			/// user exists... let them login
			if (response.contentEquals("yes")) {
				System.out.println("Existing Customer Operation Executed");
				Login login = new Login();
				String actionResult = login.loginAttempt();
				if (actionResult.contentEquals("failed")){
					System.out.println("Thanks for using Barclays Bank. Goodbye!"); 
				}else if (actionResult.contentEquals("success")){
					// access to bank account... 
					String accountType = InputValidationUtil.promptUser("Would you like to view your Checkings or Savings account?", "savings", "checkings"); 
					InputValidationUtil.closeProgramByInput(accountType); 	
				}
			} else if (response.contentEquals("no")) {
				User newUser = User.createUserByInput(scanner); 
				UserDaoImpl userDao = new UserDaoImpl(); 
				userDao.createUser(newUser); 
			}
		
		

	}
	
	
	public static String displayLoginPage(){
		System.out.println(">>>>   Welcome to login page <<<<<<");
		return InputValidationUtil.promptUser(">>>    Are you an existing customer? Enter Yes or No\n\n", "Yes", "No");
	}

}
