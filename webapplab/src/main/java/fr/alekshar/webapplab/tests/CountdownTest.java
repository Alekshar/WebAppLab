package fr.alekshar.webapplab.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import fr.alekshar.webapplab.classes.countdown.Countdown;

public class CountdownTest {

	@Test
	public void test_good_countdown() {
		new Countdown("user", "2018-05-04T20:54:45","Z", "name");
		new Countdown("user", "2018-05-04T20:54:45","+01", "name");
		new Countdown("user", "2018-05-04T20:54:45","+0100", "name");
		new Countdown("user", "2018-05-04T20:54:45","+01:00", "name");
	}
	
	// 2017-04-05T03:04:00+01:00

	@Test(expected=IllegalArgumentException.class)
	public void test_bad_date_format() {
		new Countdown("user", "16-11-04T20:54:45","", "name");
	}

	@Test()
	public void test_countdown_time() throws ParseException {
		Countdown countdown = new Countdown("user", "2016-11-05T22:23:24","+01:00", "name");
		Date seedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("04/11/2016 20:20:20");
		assertEquals("1 jour(s) 2 heure(s) 3 minute(s) 4 seconde(s)", countdown.getDiffString(seedDate));
	}

}
