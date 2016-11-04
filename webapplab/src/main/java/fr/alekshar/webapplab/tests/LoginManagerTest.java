package fr.alekshar.webapplab.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.servlet.http.Cookie;

import org.junit.Test;

import fr.alekshar.webapplab.classes.LoginManager;

public class LoginManagerTest {

	@Test
	public void test_cookie_found(){
		Cookie[] cookies = new Cookie[1];
		cookies[0] = new Cookie(LoginManager.USER_TAG, "1478168199052");
		LoginManager login = new LoginManager(cookies );
		assertEquals("1478168199052", login.getUserId());
		Cookie cookie = login.getCookie();
		assertEquals("userid", cookie.getName());
		assertEquals("1478168199052", cookie.getValue());
	}

	@Test
	public void test_cookie_not_found(){
		Cookie[] cookies = new Cookie[1];
		cookies[0] = new Cookie("somecookie", "azdazd");
		LoginManager login = new LoginManager(cookies );
		assertNotNull(login.getUserId());
		Cookie cookie = login.getCookie();
		assertEquals("userid", cookie.getName());
		assertEquals(login.getUserId(), cookie.getValue());
	}

	@Test
	public void test_no_cookie(){
		Cookie[] cookies = new Cookie[0];
		LoginManager login = new LoginManager(cookies );
		assertNotNull(login.getUserId());
		Cookie cookie = login.getCookie();
		assertEquals("userid", cookie.getName());
		assertEquals(login.getUserId(), cookie.getValue());
	}
}
