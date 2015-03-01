package no.la1k.labeler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {

	public static SimpleDateFormat OUTPUT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat INPUT_SDF = new SimpleDateFormat("yyyyMMdd HHmmss") ;

	public static String formatDate(String date, String time){
		Date d;
		try {
			d = INPUT_SDF.parse(date + " " + time);
			return OUTPUT_SDF.format(d) + " UTC";
		} catch (ParseException e) {
			return date + " " + time + " UTC";
		}
	}
}
