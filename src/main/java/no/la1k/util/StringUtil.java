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
}



