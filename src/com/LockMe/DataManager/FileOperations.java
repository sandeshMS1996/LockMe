package com.LockMe.DataManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.LockMe.DataManager.DataDefaults.*;
import static com.LockMe.UserManager.UserDefaults.USER_DATA_PATH;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.LockMe.UserManager.LoginData;

public class FileOperations {
	public static void storeData(LoginData user, Map<String, Map<String, String>> data) {
		if(data == null) return;
		Path file = null;
		String storeFilename = null; 
		BufferedWriter writer = null;
		String line = "";
		Map<String, String> temp =  new HashMap<String, String>();
		if(user != null && user.getLoginToken() == true) {

			try {
				storeFilename =  USER_DATA_PATH + user.getEmail().split("@")[0]+"_storedPasswords.txt";
				file = Paths.get(storeFilename);
				if(Files.notExists(file)) {
					System.out.println("file " + storeFilename + "does not exist");
					System.out.println("creating the file now");
					Files.createFile(file);
				}
				writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
				for(Entry<String, Map<String, String>> d : data.entrySet()) {
					temp =  d.getValue();
					line += d.getKey()+",";
					for(Entry<String, String> a: temp.entrySet())
						line += a.getKey()+","+a.getValue();
					writer.write(line);
					writer.newLine();
				}
			} catch (IOException e) {
				fileOperationError(e);
			} finally {
				try {
					if(writer != null) writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				line=null;
				storeFilename = null;
				file=null;
				temp = null;
			}
		}
	}

	public static  List<String> retriveData(LoginData user) {
		Path file = null;
		String storeFilename = null;
		storeFilename =  USER_DATA_PATH + user.getEmail().split("@")[0]+"_storedPasswords.txt";
		file = Paths.get(storeFilename);
		if(Files.notExists(file)) {
			System.out.println("file " + storeFilename + "does not exist");
			System.out.println("looks like you have not stored any data yet");
			return null; 
			}
		try {
			return Files.readAllLines(file);
		} catch (IOException e) {
			fileOperationError(e);
		}
		return null;
		}
}

