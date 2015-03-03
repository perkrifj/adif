package no.la1k.util;

import java.util.Collection;

public class StringUtil {
	
	public static String implode(Collection<String> data, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (String elm : data) {
			sb.append(elm + delimiter);
		}
		return sb.substring(0, sb.length() - delimiter.length());
	}
	
	/**
	 * Checks if the String s is null or empty
	 * @param s String to test
	 * @return true if null or empty. False otherwise.
	 */
	public static boolean isNullOrEmpty(String s) {
		if(s == null || s.isEmpty()) {
			return true;
		}
		return false;
	}
}



