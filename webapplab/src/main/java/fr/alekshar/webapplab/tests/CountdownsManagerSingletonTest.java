package fr.alekshar.webapplab.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import fr.alekshar.webapplab.classes.Countdown;
import fr.alekshar.webapplab.classes.CountdownsManagerSingleton;

public class CountdownsManagerSingletonTest {

	@Test
	public void test() throws ParseException {
		CountdownsManagerSingleton manager = CountdownsManagerSingleton.getInstance();
		assertEquals(1, manager.addCountdown(new Countdown("user1", "04/11/16 20:20:20")));
		assertEquals(1, manager.getCountdownsFor("user1").size());
		assertEquals(1, manager.addCountdown(new Countdown("user2", "04/11/16 20:20:20")));
		assertEquals(1, manager.getCountdownsFor("user1").size());
		assertEquals(2, manager.addCountdown(new Countdown("user1", "04/11/16 20:25:20")));
		assertEquals(2, manager.getCountdownsFor("user1").size());
		manager.removeCountdown("user2", 1);
		assertEquals(2, manager.getCountdownsFor("user1").size());
		manager.removeCountdown("user1", 1);
		assertEquals(1, manager.getCountdownsFor("user1").size());
		assertEquals(2, manager.getCountdownsFor("user1").get(0).getId());
	}

}
