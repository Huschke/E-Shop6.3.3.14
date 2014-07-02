package shop.ui.cui;

import java.io.*;

public class IO {
	
	public static BufferedReader input
    = new BufferedReader(new InputStreamReader(System.in));
	public static String eingabe = "";

	// Einlesen eines char
	public static char readChar() {
	try {
		eingabe = input.readLine();
		return eingabe.charAt(0);
	}
	catch(Exception e) {
		return '\0';
		}
	}
	

	// Einlesen eines int
		public static int readInt() {
			try {
				eingabe = input.readLine();
				Integer string_to_int = new Integer(eingabe);
				return string_to_int.intValue();
			}
			catch (Exception e) {
			  return 0;
			}
		}
		
		// Einlesen eines float
		public static float readFloat() {
			try {
				eingabe = input.readLine();
				Float string_to_float = new Float(eingabe);
				return string_to_float.floatValue();
			}
			catch (Exception e) {
				return 0.0F;
			}
		}
		
		// Einlesen eines string
		public static String readString() {
			try {
				return input.readLine();
			}
			catch (Exception e) {
				return "";
			}
		}
		
		
}
