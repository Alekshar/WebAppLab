package fr.alekshar.webapplab.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;

import org.junit.Test;

import fr.alekshar.webapplab.classes.countdown.Countdown;
import fr.alekshar.webapplab.classes.countdown.CountdownsManagerSingleton;

public class CountdownsManagerSingletonTest {

	@Test
	public void test_create_remove() throws ParseException {
		CountdownsManagerSingleton manager = CountdownsManagerSingleton.getInstance(true);
		assertEquals(1, manager.addCountdown(new Countdown("user1", "2016-11-04T20:20:20", "+01:00", "name")));
		assertEquals(1, manager.getCountdownsFor("user1").size());
		assertEquals(1, manager.addCountdown(new Countdown("user2", "2016-11-04T20:20:20","+01:00", "name")));
		assertEquals(1, manager.getCountdownsFor("user1").size());
		assertEquals(2, manager.addCountdown(new Countdown("user1", "2016-11-04T20:25:20","+01:00", "name")));
		assertEquals(2, manager.getCountdownsFor("user1").size());
		manager.removeCountdown("user2", 1);
		assertEquals(2, manager.getCountdownsFor("user1").size());
		manager.removeCountdown("user1", 1);
		assertEquals(1, manager.getCountdownsFor("user1").size());
		assertEquals(2, manager.getCountdownsFor("user1").get(0).getId());
	}

	@Test
	public void test_get() throws ParseException {
		CountdownsManagerSingleton manager = CountdownsManagerSingleton.getInstance(true);
		assertEquals(1, manager.addCountdown(new Countdown("user", "2016-11-04T20:20:20","+01:00", "name")));
		assertNotNull(manager.getCountdown("user", 1));
		assertNull(manager.getCountdown("user", 2));
	}

}
