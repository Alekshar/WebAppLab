package fr.alekshar.webapplab.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import fr.alekshar.webapplab.classes.Countdown;

public class CountdownTest {

	@Test
	public void test_good_countdown() {
		try {
			new Countdown("user", "04/11/2018 20:54:45");
		} catch (ParseException e) {
			fail("date refused");
		}
		try {
			new Countdown("user", "04/11/15 20:54:45");
		} catch (ParseException e) {
			fail("date refused");
		}
	}

	@Test(expected=ParseException.class)
	public void test_bad_date_format() throws ParseException {
		new Countdown("user", "04/11/ 20:54:45");
	}

	@Test()
	public void test_countdown_time() throws ParseException {
		Countdown countdown = new Countdown("user", "04/11/2016 20:20:20");
		Date seedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("05/11/2016 22:23:24");
		assertEquals("1 jour(s) 2 heure(s) 3 minute(s) 4 seconde(s)", countdown.getDiffString(seedDate));
	}

}
