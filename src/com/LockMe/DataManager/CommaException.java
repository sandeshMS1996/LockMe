package com.LockMe.DataManager;

public class CommaException extends Exception {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Override
		public String toString() {
			return "[Error] Application cannot store data that contains \",\" due to design limitations.."; 
		}
	
}
