package com.LockMe.UserManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static com.LockMe.UserManager.UserDefaults.*;

public class UserRegistractionAndLogin {
	/**
	 * One time user registration for a user.</br>
	 * If user has already registered with the email, Returns false
	 * @author Sandesh MS
	 * @return true if registration is successful else false
	 * @param Scanner
	 * @category User Data Maintenance
	 */
	public static boolean Register(Scanner scanner) {
		UserData tryUser = UserInteractions.forRegistration(scanner);
		if(tryUser == null) return false;
		if( SearchAndStoreUser.searchUserbyEmail(tryUser.getEmail()) == null) 
			return SearchAndStoreUser.StoreUser(tryUser);
		else {
			System.out.println("user has already registered with this email");
			System.out.println("please use another email to register");
			return false;
		}
	}

	/**
	 * User login for a registered user.</br>
	 * If not Registered return false.</br>
	 * If incorrect password return false</br>
	 * 
	 * @author Sandesh MS
	 * @param Scanner
	 * @return true if login successful else false
	 * @throws CommaException 
	 */

	public static LoginData Login(Scanner scanner)  {
		LoginData user;
		user = UserInteractions.forLogin(scanner); 
		LoginData validate = SearchAndStoreUser.searchUserbyEmail(user.getEmail());
		//System.out.println(validate);
		if(validate != null && user.getPassword().equals(validate.getPassword())) {
			System.out.println("Login Success for user \"" + validate.getEmail() + "\"");
			if(validate.getlastLoginDateTime() != null) {
				System.out.println("last login: " + (validate.getlastLoginDateTime().format(
						DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT))));
				Duration d = Duration.between(validate.getlastLoginDateTime(), 
					ZonedDateTime.of(LocalDateTime.now(), DEFAULT_TIME_ZONE));
				if(d.toMinutes() > 10)
					System.out.println("it's been " + d.toMinutes() + " minutes since you login");
			validate.setPassword(null);
			validate.loginToken = true;
			}
			return validate;
			
		}
		user = null;
		return null;
	}

	public static void logoff(LoginData data) {
	Path userFile =  null;
	List<String> lines =  null;
	try {	
		if(data == null) {
			System.out.println("stuppied, you are loggoff null");
			return;
		}
		BufferedWriter writer = null;
		try {
			userFile = Paths.get(UNAME_AND_PWORD_FILENAME);
			lines = Files.readAllLines(userFile);
			Files.delete(userFile);
			Files.createFile(userFile);
			writer = Files.newBufferedWriter(userFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			for(String line: lines ) {
				if(line.split(",")[0].equals(data.getEmail())) {
					ZonedDateTime dt = ZonedDateTime.of(LocalDateTime.now(), DEFAULT_TIME_ZONE);
					line = data.getEmail()+","+line.split(",")[1]+"," + dt;
				}
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException e) {
			if(DEBUG) {
				System.out.println("Error detected..");
				e.printStackTrace();
				}
			}finally {
			System.out.println("logged off..");
			data = null;
			userFile = null;
			lines = null;
			try {
				if(writer != null) writer.close();
			} catch(IOException e) {
				System.out.println("error while closing writer at Logoff..");
			}

		}
	}catch (Exception e) {
		System.out.println("could not Update lastLogintime due to unexpeced error");
		System.out.println("please note your last login time"); 
		
	}
	}

}

