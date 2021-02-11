package marian.revature.com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import marian.revature.com.dao.AccountApplicationDaoImpl;
import marian.revature.com.dao.UserDaoImpl;
import marian.revature.com.models.Login;
import marian.revature.com.models.User;
import marian.revature.com.utility.ConnectionUtil;
import marian.revature.com.utility.InputValidationUtil;


public class BankingConsole { 

	private static Scanner scanner = new Scanner(System.in); 
	private static UserDaoImpl userDao = new UserDaoImpl(); 
	public static void main(String[] args) throws SQLException, IOException, InterruptedException {
		//ConnectionUtil.dropSchemas(); 
		ConnectionUtil.createSchemas(); 
		
		String response = null;
		
		System.out.println(" Welcome To The Barclays Bank ");
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
					String accountType = InputValidationUtil.promptUser(scanner, "Would you like to view your Checkings or Savings account?", "savings", "checkings"); 
					InputValidationUtil.closeProgramByInput(accountType); 
					User currentUser = login.getLoggedInUser();
					System.out.println("Coming soon!"); 
					// TODO  now check the application or check the bank account... 
					
					
				}
			} else if (response.contentEquals("no")) {
				
				User newUser = User.createUserByInput(scanner); 
				userDao.createUser(newUser); 
				
				// next apply for account... 
				String accountType = InputValidationUtil.promptUser(scanner, "Would you like to apply for Checkings or Savings account or none ?", "savings", "checkings", "none"); 
				if(accountType.equalsIgnoreCase("none")){
					InputValidationUtil.closeProgramByInput(null); 	
				}else {
					AccountApplicationDaoImpl accountApplication = new AccountApplicationDaoImpl();
					accountApplication.applyForAccount(newUser.getId(), accountType);
					System.out.println("Application was done...!");
					String secondAccount = InputValidationUtil.promptUser(scanner, "Would you like to apply for " + 
					("savings".contentEquals(accountType) ? "checkings" : "savings") + " account or none ?", 
							"savings".contentEquals(accountType) ? "checkings" : "savings", "none"); 
					if(accountType.equalsIgnoreCase("none")){
						InputValidationUtil.closeProgramByInput(null); 	
						}else {
							accountApplication.applyForAccount(newUser.getId(), secondAccount);
							System.out.println("Application was done...!");
							InputValidationUtil.closeProgramByInput(null); 	

						}
							
					}

				
			}
		
		

	}
	
	
	public static String displayLoginPage(){
		System.out.println(">>>>   Welcome to login page <<<<<<");
		return InputValidationUtil.promptUser(scanner, ">>>    Are you an existing customer? Enter Yes or No\n\n", "Yes", "No");
	}

}
