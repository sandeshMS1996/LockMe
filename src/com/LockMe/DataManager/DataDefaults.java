package com.LockMe.DataManager;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public interface DataDefaults {
	String USER_DATA_PATH = "AppData//";
	
	String UNAME_AND_PWORD_FILENAME =  "secure.txt";
	
	
	boolean DEBUG = true;
	
	public static Map<String, Map<String, String>> putData(String website, String uname, String password) {
		Map<String, String> pair = new HashMap<String, String>(); 
		Map<String, Map<String, String>> r =  null;
		try {
			pair.put(uname, password);
			r =  new HashMap<String, Map<String,String>>();
			r.put(website, pair);
			return r;
		} finally {
			pair = null;
			r = null;
		}	
	}
	
	static void fileOperationError(Exception e) {
		if(DEBUG) e.printStackTrace();
		System.out.println("An unExpected error occured.. Please try again later.!");
		System.out.println("sorry for the inconvience caused...");
	}
	static void fileOperationError() {
		//if(DEBUG) e.printStackTrace();
		System.out.println("An unExpected error occured.. Please try again later.!");
		System.out.println("sorry for the inconvience caused...");
	}
	
	public static int acceptInputAsInt(Scanner scanner) {
		String c = null;
		int ch = 0;
		while(true) {
			try {
				c = scanner.nextLine();
				ch = Integer.parseInt(c);
				break;
		} catch (NumberFormatException e) {
			System.out.println("Please enter the correct choice..\n" + c);
		}
	}
		return ch;	
	}
	
	public static String acceptInputAsString(Scanner scanner, boolean checkForComma) throws CommaException  {
		String s =  null;
		try {
			s = scanner.nextLine();
			while(true) {
				if(!s.equals("")) break;
				System.out.println("Please enter a value..");
				s = scanner.nextLine();
			}
			if(checkForComma && s.contains(",")) {
				System.out.println("[Error] Application cannot store data "
						+ "that contains \",\" due to design limitations.."); 
				throw new CommaException();
		
			}
			else return s;
		}
		catch(InputMismatchException  e) {
			System.out.println("An error accured..");
			System.out.println("please Enter again..");
			acceptInputAsString(scanner, checkForComma);
		}
		return null;
	}
	
	public static String acceptInputAsString(Scanner scanner) {
		String s = ""; 
		try {
			s = scanner.nextLine();
			while(true) {
				if(!s.equals("")) break;
				System.out.println("Please enter a value..");
				s = scanner.nextLine();
			}
		}catch(Exception e) {
			System.out.println("An error accured..");
			System.out.println("please Enter again..");
			acceptInputAsString(scanner);
		}
		return s.toLowerCase();
	}

	
}