package com.LockMe.UserManager;

import static com.LockMe.UserManager.UserDefaults.*;

import java.util.Scanner;
import java.util.regex.Pattern;


class UserInteractions {
	 static UserData forRegistration(Scanner scanner) {
		String fName =  null;;
		String lName=null;
		String emailID=null;
		String pass = null;
		
		try {
		//Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your first Name.!\n");
			fName = acceptInputAsString(scanner, FIRST_NAME_PATTERN, "Please enter the correct FirstName:", true).toLowerCase();
			
			System.out.println("Enter your Last Name.!\n");
			lName = acceptInputAsString(scanner, LAST_NAME_PATTERN, "Please enter the correct LastName:", true).toLowerCase();
			
			System.out.println("Enter your Email ID.!\n");
			emailID = acceptInputAsString(scanner, EMAIL_PATTERN, "Please enter the correct email:", true).toLowerCase();
			
			System.out.println("[Password Policy]: Password much conatin atleast one: ");
			System.out.println("\t\t1. Capital letter\n\t\t2. Smapp letter");
			System.out.println("\t\t3. Digit\n\t\t4. Special Character in(@#$%!) ");
			System.out.println("\t\t5. password length: 8 to 40 characters");
			
			System.out.println("\nEnter your password.!\n");
			pass = getPassowrd(scanner);
			while(pass.contains(",") || !Pattern.matches(PASSWORD_PATTERN, pass)) {
				System.out.println("Your password \"" + pass + "\" does not match pasowrd policy");
				pass = getPassowrd(scanner);
			}
		}
		catch (Exception e) {
			System.out.println("Unexpected Error..!\n Please try again..\n");
			if(DEBUG)
				e.printStackTrace();
			forRegistration(scanner);
		}
		return   new  UserData(fName, lName, emailID, pass);	
	}
	
	private static final String getPassowrd(Scanner scanner) {
		String pass1 = null;
		//Scanner scanner = new Scanner(System.in);
		pass1 = scanner.nextLine();
		System.out.println("Enter your password Again.!\n");
		String verifyPassword = scanner.nextLine();
		while(!pass1.equals(verifyPassword)) {
			System.out.println("Password verification does not Match.Please try again..");
			System.out.println("Enter your password.!");
			pass1 = scanner.nextLine();
			System.out.println("Enter your password Again.!\n");
			verifyPassword = scanner.nextLine();
		}
		return pass1;
	}
		
	static LoginData forLogin(Scanner scanner)  {
			String uname = null;
			String password = null;
			System.out.println("Enter your email ID: \n");
			uname = acceptInputAsString(scanner, EMAIL_PATTERN, "Please enter correct email", false).toLowerCase();
			while(uname == null) {
				System.out.println("please provide your details" + uname);
				System.out.println("Enter your email ID: \n");
				uname = acceptInputAsString(scanner, EMAIL_PATTERN, "Please enter correct email", false).toLowerCase();
			}
			
			System.out.println("Enter your Password: \n");
			password = acceptInputAsString(scanner, PASSWORD_PATTERN, "Please enter correct Password", false);
			while(password == null) {
				System.out.println("please provide your details" + password);
				System.out.println("Enter your Password: \n");
				password = acceptInputAsString(scanner, PASSWORD_PATTERN, "Please enter correct Password", false);
			}
			return new LoginData(uname, password);
	}
	
}
