package no.la1k.labeler;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class DateFormaterTester {

	@Test
	public void testFormatDate() {
		String date = "20140315";
		String time = "102429";
		
		String expected = "2014-03-15 10:24:29";
		
		try {
			String formatted = DateFormater.formatDate(date, time);
			assertEquals(formatted, expected);
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = ParseException.class)
	public void testFailString() throws ParseException{
		String date = "Dette er ikke en dato";
		String time = "Dette er ikke en tid";
			DateFormater.formatDate(date, time);
	}
}
