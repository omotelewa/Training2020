package marian.revature.com.utility;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class InputValidationUtil {

	
	private static Scanner scanner = new Scanner(System.in); 
	
	public static float getUserAmount(String inputMessage){
		while (true) {
			try {
				System.out.println(inputMessage);
				float amount = scanner.nextFloat();
				if (amount < 0){
					throw new InputMismatchException();
				}
				return amount; 
			} catch (InputMismatchException d) {
				System.out.println("Invalid input. Try again."); 
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Try again."); 
			}
		}
	}
	
	
	public static String promptUser(String input, String... choices) {
		System.out.println(input);
		System.out.println("Enter Q at anytime to quit."); 
		Scanner scan = new Scanner(System.in);
		String output = null; 
		
		while (true){
			String choice = scan.nextLine();
			int results = (int) Stream.of(choices).filter(ch -> choice.equalsIgnoreCase(ch)).count(); 
			if (choice.toUpperCase().matches("Q")){
				break; 
			}else if (results !=  1){
				System.out.println("Please try again"); 
				System.out.println(input);
				System.out.println("Enter Q at anytime to quit."); 
			}else {
				output = choice.toLowerCase(); 
				break; 
			}
			
		}
		scan.close();
		return output; 
	}
	
	public static String promptUser(Scanner scan, String input, String... choices) {
		System.out.println(input);
		System.out.println("Enter Q at anytime to quit."); 
		String output = null; 
		
		while (true){
			String choice = scan.nextLine();
			int results = (int) Stream.of(choices).filter(ch -> choice.equalsIgnoreCase(ch)).count(); 
			if (choice.toUpperCase().matches("Q")){
				break; 
			}else if (results !=  1){
				System.out.println("Please try again"); 
				System.out.println(input);
				System.out.println("Enter Q at anytime to quit."); 
			}else {
				output = choice.toLowerCase(); 
				break; 
			}
			
		}
		return output; 
	}
	
	public static void closeProgramByInput(String input){
		if (input == null){
			System.out.println("Thanks for visting us. Existing the program."); 
			System.exit(0);
		}
	}
	
	public static String pr(String str){
		System.out.println(str);
		return str;
	}
	
	
	public static String validateString(String strToValidate, String promptMsg, Scanner scanner){
		if (strToValidate.trim().isEmpty()){
			pr("Invalid Input. Try again."); 
			String prompt = pr(promptMsg); 
			strToValidate = scanner.nextLine(); 
			return validateString(strToValidate, prompt , scanner); 
		}else {
			return strToValidate.toLowerCase(); 
		}
	}
	
}
