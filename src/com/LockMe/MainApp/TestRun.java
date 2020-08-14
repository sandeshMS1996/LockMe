package com.LockMe.MainApp;

import java.util.Scanner;

import com.LockMe.UserManager.*;
import static com.LockMe.DataManager.DataDefaults.*;
public class TestRun {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		//if(scanner.hasNextLine()) scanner.nextLine();
		InitialiseApplication.Welcome();
		//System.out.println("press any Key to continue...");
		//System.out.println(scanner.hasNextInt());
		//String c = "";
		int ch =0;
		System.out.println("Please choose from the below options..\n");
		while(true) {
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				ch = acceptInputAsInt(scanner);
				while(true) {
					if(ch < 0 || ch > 3) {
						System.out.println("please enter correct details\n");
						ch = acceptInputAsInt(scanner);
					}	
					else break;
				}
			switch(ch) {
			case 1: {
				if(UserRegistractionAndLogin.Register(scanner)) {
					System.out.println("Registration successful");
				}
				else System.out.println("Registration not successful");
				break;
			} 
			case 2: { 
					DataOperations.LoginOperations(scanner);
					break;
			}	
			case 3: {
				System.out.println("Bye...!");
				scanner.close();
				System.exit(0);
			}
			default : {
				System.out.println("invalid selection");
			}
			System.out.println("----------------------------------");
			
		}
		}
	}
}