package fr.alekshar.webapplab.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.alekshar.webapplab.classes.countdown.Countdown;
import fr.alekshar.webapplab.classes.countdown.CountdownMessagesUtil;
import fr.alekshar.webapplab.classes.countdown.CountdownsManagerSingleton;

public class CountdownMessagesUtilTest {

	private static CountdownsManagerSingleton manager;

	@BeforeClass
	public static void beforeclass() {
		new File("database.db").delete();
		manager = CountdownsManagerSingleton.getInstance();
	}

	@Test
	public void test_add() {
		CountdownMessagesUtil.process("{\"action\":\"create\",\"name\":\"test\",\"date\":\"2016-11-10T13:21:00\",\"timezone\":\"Z\",\"userid\":\"user1\"}");
		Countdown counter = manager.getCountdownsFor("user1").get(0);
		assertEquals("test", counter.getName() );
		assertEquals("2016-11-10T13:21:00Z", counter.getDateString()+counter.getTimezoneString() );
	}

	@Test
	public void test_update() {
		manager.addCountdown(new Countdown("user2", "2016-11-10T13:21:00","Z", "test"));
		Countdown counter = manager.getCountdownsFor("user2").get(0);
		assertEquals("test", counter.getName() );
		assertEquals("2016-11-10T13:21:00Z", counter.getDateString()+counter.getTimezoneString() );
				
		CountdownMessagesUtil.process("{\"action\":\"update\",\"id\":1,\"date\":\"2016-11-10T14:00:00\",\"timezone\":\"+01:00\",\"name\":\"test2\",\"userid\":\"user2\"}");

		counter = manager.getCountdownsFor("user2").get(0);
		assertEquals("test2", counter.getName() );
		assertEquals("2016-11-10T14:00:00+01:00", counter.getDateString()+counter.getTimezoneString() );
	}

	@Test
	public void test_remove() {
		manager.addCountdown(new Countdown("user3", "2016-11-10T13:21:00","Z", "test"));
		Countdown counter = manager.getCountdownsFor("user3").get(0);
		assertEquals("test", counter.getName() );
				
		CountdownMessagesUtil.process("{\"action\":\"delete\",\"id\":1,\"userid\":\"user3\"}");

		assertEquals(0, manager.getCountdownsFor("user3").size());
	}

}
