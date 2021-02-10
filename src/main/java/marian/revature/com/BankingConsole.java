package marian.revature.com;

import java.util.Scanner;
import java.util.function.Predicate;

import marian.revature.com.dao.CustomerDAOimpl;
import marian.revature.com.models.CustomerDetails;
import marian.revature.com.models.Login;


public class BankingConsole { 

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println(" Welcome To The Barclays Bank  > + ");
		try {
			System.out.println(">>>>   Welcome to login page <<<<<<");
			System.out.println(">>>    Are you an existing customer? Enter Yes or No");
			String userInput = scanner.next();
			Predicate<String> checkIfUserEnterYes = (x) -> x.equalsIgnoreCase("Yes");
			Predicate<String> checkIfUserEnteNos = (x) -> x.equalsIgnoreCase("No");
			if (userInput != null && checkIfUserEnterYes.test(userInput)) {
				System.out.println("Existing Customer Operation Executed");

				Login login = new Login();
			} else if (userInput != null && checkIfUserEnteNos.test(userInput)) {
				CustomerDAOimpl user = new CustomerDAOimpl();
				CustomerDetails users = user.createUser();
				marian.revature.com.models.Login nulogin = new marian.revature.com.models.Login(users);
			} else {
				System.out.println("INvalid input");
			}

		} catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			scanner.close();
		}

	}

	/*
	 * private static Boolean validate(CustomerDetails objs) { if (objs != null) {
	 * if (objs.getLogInPassword().length() == 0 ||
	 * objs.getLogInPassword().isEmpty()) {
	 * System.out.println("Error :please enter a valid LogInPassword"); } if
	 * (objs.getLogInUserName().length() == 0 || objs.getLogInUserName().isEmpty())
	 * { System.out.println("Error :please enter a valid LogInUserName"); } } return
	 * null; }
	 */

}
