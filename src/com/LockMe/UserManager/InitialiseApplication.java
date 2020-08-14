package com.LockMe.UserManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static com.LockMe.UserManager.UserDefaults.*;

public final class InitialiseApplication  {
	private static boolean isFirstRun = true;
	private static String welcomeString = ""; 
	static {
		Path loginFile = Paths.get(UNAME_AND_PWORD_FILENAME);
		if(Files.notExists(loginFile)) {
			try {
				Files.createFile(loginFile);
			} catch (IOException e) {
				fileOperationError(e);
			}
		}
		loginFile =  Paths.get(USER_DETAILS_FILENAME);
		if(Files.notExists(loginFile)) {
			try {
				Files.createFile(loginFile);
			} catch (IOException e) {
				fileOperationError(e);
			}
		}
	}
	
	
	public final static void Welcome() {
		if(isFirstRun) {
			isFirstRun = false;
			Path welcomePath = Paths.get(WELCOME_FILENAME);	
			try {	
				Files.readAllLines(welcomePath).forEach(a-> welcomeString+= a + "\n");
			}
			catch (IOException e) {
				if(DEBUG)
					e.printStackTrace();
				welcomeString = "**************************\n" 
						+   "Welcome to myLocker\n"
						+   "Developer: Sandesh MS\n"
						+ 	"**************************\n";
			}
			System.out.println(welcomeString);
		}
	}
	


	}


