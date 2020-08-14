package com.LockMe.MainApp;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static com.LockMe.DataManager.DataDefaults.*;
import com.LockMe.DataManager.AcceptData;
import com.LockMe.DataManager.FileOperations;
import com.LockMe.UserManager.LoginData;
import com.LockMe.UserManager.UserRegistractionAndLogin;

public class DataOperations {
	public static int LoginOperations(Scanner scanner)  {
		System.out.println("please login");
		LoginData data =  UserRegistractionAndLogin.Login(scanner);
		if(data == null) {
			System.out.println("invalid credencials..");
			return -1;
		}	
		//System.out.println("token " + data.getLoginToken());
		if(data != null && data.getLoginToken() == true) {
					System.out.println("******************************************************");
					System.out.println("[Note] Application cannot store data that contains \",\" due to design limitations..");
					System.out.println("       System will reject data that contains \",\" in it...");
					System.out.println("******************************************************");
					System.out.println("Please select from the below options..\n");
					while(true) {
						System.out.println("1. store password");
						System.out.println("2. Search stored password");
						System.out.println("3. Display all password");
						System.out.println("4. Logoff and return to main menu");
						System.out.println("5. Logoff Exit Application");	
						int ch = acceptInputAsInt(scanner);
						while(true) {
							if(ch < 0 || ch > 5) {
								System.out.println("please enter correct Choice\n");
								ch = acceptInputAsInt(scanner);
							}	
							else break;
						} 

				switch(ch) {
				case 1: 
					FileOperations.storeData(data, AcceptData.accept(scanner));
					break;
				case 2:	{
					System.out.println("Enter the Website for which you want to retrive Credencials");
					String searchString;
					searchString = acceptInputAsString(scanner);
					
					boolean found = false;
					List<String> matches = new ArrayList<String>();
					String[] vals = null;
					for(String a :FileOperations.retriveData(data)) {
						vals  = a.split(",");
						if(vals[0].equalsIgnoreCase(searchString)) {
							found = true;
							System.out.println("Matching Website found ");
							System.out.println(vals[0] + "->");
							System.out.println("\tUsername: " + vals[1] + "\n\tPassword: " + vals[2]);
							break;
						}
						else matches.add(vals[0]);
					}
					//System.out.println(matches.toString());	
					if(!found) {
							System.out.println("No passowrd found for the searchString " + searchString);
							String relate = "";
							for(String i : matches) {
								if(i.substring(0,1).equalsIgnoreCase(searchString.substring(0, 1))) 
									relate += "-\t" + i + "\n";
							}
							if(!relate.equals("")) {
								System.out.println("here are some suggestions..");
								System.out.println(relate);
							}
						}
						break;
					}
				case 3: {
					for(String a :FileOperations.retriveData(data)) {
						String[] vals = a.split(","); 
						System.out.println(vals[0] + ": ");
						System.out.println("\t-username: " + vals[1]);
						System.out.println("\t-password: " + vals[2]);
					}
					break;
				}
				
				case 4 : {
					UserRegistractionAndLogin.logoff(data);
					return 3;
				}
				case 5 : {
					UserRegistractionAndLogin.logoff(data);
					System.out.println("Bye..");
					scanner.close();
					System.exit(0);
				}
				default : {
					System.out.println("invalied selection..!");
				}
				}
				}
		}	
		return 0;
	
	

}
}