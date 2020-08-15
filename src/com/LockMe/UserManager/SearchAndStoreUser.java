package com.LockMe.UserManager;

import static com.LockMe.UserManager.UserDefaults.*;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

class SearchAndStoreUser {
	public static LoginData searchUserbyEmail(String email) {
		Path p = Paths.get(UNAME_AND_PWORD_FILENAME);
		BufferedReader input =  null;
		ZonedDateTime time = null; 
		String line = null;
		if(Files.notExists(p)) 
			fileOperationError();
		try {
			input = Files.newBufferedReader(p);
			while((line= input.readLine()) != null) {
				String[] values = line.split(",");
				if(values[0].equals(email)) {
					try {
						time = ZonedDateTime.parse(values[2]).withZoneSameInstant(DEFAULT_TIME_ZONE);
					} catch(Exception e) {
						time= null;
					}

					return new LoginData(values[0], values[1], false, time);
				}
			}

		}catch(IOException e) {
			fileOperationError(e);
		}finally {
			try {
				if(input!=null) input.close();
				p = null; time = null;line=null;
			} catch (Exception e) {

			}
		} 

		return null;
	}


	static boolean StoreUser(UserData user) {
		Path userFile = Paths.get(USER_DETAILS_FILENAME);
		Path loginFile = Paths.get(UNAME_AND_PWORD_FILENAME);
		BufferedWriter userOutput = null;
		BufferedWriter loginOutput = null;
		String u = null;
		String l = null;
		if(Files.notExists(userFile) || Files.notExists(loginFile)) 
			fileOperationError();
		try {	
			userOutput = Files.newBufferedWriter(userFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			loginOutput = Files.newBufferedWriter(loginFile,StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			u = user.getFirstName() + "," + user.getLastName() + "," + user.getEmail() + "\n";
			l = user.getEmail() + "," + user.getPassword() + "," + ZonedDateTime.of(LocalDateTime.now(),DEFAULT_TIME_ZONE) + "\n"; 
			loginOutput.append(l);
			userOutput.append(u);
			createUserFile(user.getEmail());
			if(DEBUG) System.out.println("user details has been stored to the files");
			return true;
		} catch (IOException e) {
			removeUser(user.getEmail(), true, true);
			fileOperationError(e);
		}catch (Exception e) {
			removeUser(user.getEmail(), true, true);
			return false;
		}
		finally {
			try {
				if(userOutput != null) userOutput.close();
				if(loginOutput != null) loginOutput.close();
				u = null;l = null;
				userFile = null; loginFile = null;
			} catch (Exception e) {
				if(DEBUG)
					System.out.println("writers at Store data were not clased properly");
			}

		}
		return false;	
	}

	private static void createUserFile(String email) {
		String fileName = USER_DATA_PATH + email.split("@")[0]+"_storedPasswords.txt";
		Path filePath =  Paths.get(fileName);
		System.out.println(Files.exists(filePath));
		if(Files.notExists(filePath)) {
			try {
				Files.createFile(filePath);
				System.out.println("data file has been created sucessfully..!");
			} catch (IOException e) {
				System.out.println("could not create data File  at this time due to Unexpected error..");
			}
		}

	}

	public static int removeUser(String email, boolean removerFromUserData, boolean removerFromLoginData) {
		Path userFile = Paths.get(USER_DETAILS_FILENAME);
		Path loginFile = Paths.get(UNAME_AND_PWORD_FILENAME);
		BufferedWriter userOutput = null;
		BufferedWriter loginOutput = null;
		try {
			List<String> loginData1 = Files.readAllLines(loginFile);
			List<String> userData1 = Files.readAllLines(userFile);
			Files.delete(loginFile);
			Files.delete(userFile);
			Files.createFile(userFile);
			Files.createFile(loginFile);
			userOutput = Files.newBufferedWriter(userFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			loginOutput = Files.newBufferedWriter(loginFile,StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			if(removerFromLoginData) {
				for(String a: loginData1) {
					if(a.split(",")[0].equals(email))
						continue;
					else  {
						loginOutput.write(a);
						loginOutput.newLine();
				}
			}	
			}	
			if(removerFromUserData) {
				for(String a: userData1) {
					if(a.split(",")[2].equals(email))
						continue;
					else  {
						userOutput.write(a);
						userOutput.newLine();
				}
			}
			System.out.println("user " + email + "has been deleted from file system");
			}
			Files.deleteIfExists(Paths.get((USER_DATA_PATH + email.split("@")[0]+"_storedPasswords.txt")));
			return 0;
		}catch (IOException e) {
			return -1;
		}finally {
			try {
				if(userOutput != null)
					userOutput.close();
				if(loginOutput != null)
					loginOutput.close();
			}catch (Exception e) {
				if(DEBUG)
					System.out.println("writers at re,obe user were not clased properly");
				}
		}	
		
	}

}






