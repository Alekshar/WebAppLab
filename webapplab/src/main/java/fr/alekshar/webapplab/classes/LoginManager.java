package fr.alekshar.webapplab.classes;

import javax.servlet.http.Cookie;

public class LoginManager {
	public static final String USER_TAG = "userid";
	String userid;

	public LoginManager(Cookie[] cookies) {
		Cookie cookie = null;
		if(cookies != null){
			for(Cookie cook : cookies){
				if(cook.getName().equals("userid")){
					cookie = cook;
				}
			}
		}
		
		if(cookie == null){
			userid =  generateId();
		} else {
			userid = cookie.getValue();
		}

	}

	public String getUserId() {
		return userid;
	}

	private String generateId() {
		return String.valueOf(System.currentTimeMillis());
	}

	public Cookie getCookie() {
		return new Cookie(USER_TAG, userid);
	}
}
