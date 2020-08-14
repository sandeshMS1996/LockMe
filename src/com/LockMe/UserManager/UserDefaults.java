package com.LockMe.UserManager;

import java.time.ZoneId;
import java.util.Scanner;
import java.util.regex.Pattern;

public interface UserDefaults {
	boolean DEBUG = true;
	String USER_DETAILS_FILENAME =  "UserDetails.txt";
	
	String UNAME_AND_PWORD_FILENAME =  "secure.txt";
	
	String WELCOME_FILENAME = "Welcome.txt";
	
	String FIRST_NAME_PATTERN = "[a-zA-Z]{6,}";
	
	String LAST_NAME_PATTERN = "[a-zA-Z]{2,}";
	
	String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	
	String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,40})";
	
	ZoneId DEFAULT_TIME_ZONE = ZoneId.systemDefault();
	
	String DEFAULT_DATE_TIME_FORMAT = "dd-MMM-yyyy HH:mm:ss";
	
	String USER_DATA_PATH = "AppData//";
	
	static void fileOperationError(Exception e) {
		if(DEBUG) e.printStackTrace();
		System.out.println("An unExpected error occured.. Please try again later.!");
		System.out.println("sorry for the inconvience caused...");
		System.exit(-1);
	}
	static void fileOperationError() {
		
		System.out.println("An unExpected error occured.. Please try again later.!");
		System.out.println("sorry for the inconvience caused...");
		System.exit(-1);
	}
	
	
	
	static String acceptInputAsString(Scanner scanner, String pattern, String errorMessage, boolean checkForComma){
		String val =  "";
		try {
			val = scanner.nextLine();
			//val = val.toLowerCase();
			if(checkForComma && val.contains(",")) throw new CommaException();
			while(!Pattern.matches(pattern, val)) {
				System.out.println(errorMessage);
				val = scanner.nextLine();
			}
		}catch(CommaException e) {
			System.out.println(errorMessage);
			acceptInputAsString(scanner, pattern, errorMessage, checkForComma);
		}
		
		catch(Exception e) {
			if(DEBUG) e.printStackTrace();
			System.out.println("An error accured..");
			System.out.println("please Enter again..");
			acceptInputAsString(scanner, pattern, errorMessage, checkForComma);
		}
			
		return val;
	}
	

}