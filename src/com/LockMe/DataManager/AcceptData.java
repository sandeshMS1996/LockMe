package com.LockMe.DataManager;

import java.util.Map;
import static com.LockMe.DataManager.DataDefaults.*;
import java.util.Scanner;

public class AcceptData {
	public static Map<String, Map<String, String>> accept(Scanner scanner) {
			try {
				System.out.println("Enter the website for which you want to store data");
				String website = acceptInputAsString(scanner, true);
				System.out.println("enter username for " + website);
				String uname = acceptInputAsString(scanner, true);
				System.out.println("enter password for " + uname);
				String password = acceptInputAsString(scanner, true);
				return putData(website, uname, password);
			}catch(CommaException e) {
				return null;
			}
	
	}	
}