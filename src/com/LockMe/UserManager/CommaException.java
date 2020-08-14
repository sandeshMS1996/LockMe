package com.LockMe.UserManager;

public class CommaException  extends Exception{
		@Override
		public String toString() {
			return "[Error] Application cannot store data that contains \",\" due to design limitations.."; 
		}
	
}
