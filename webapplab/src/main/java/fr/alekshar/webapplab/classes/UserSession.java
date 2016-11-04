package fr.alekshar.webapplab.classes;

import javax.websocket.Session;

public class UserSession {
	Session session;
	String userId;
	public UserSession(Session session, String userId) {
		super();
		this.session = session;
		this.userId = userId;
	}
	public Session getSession() {
		return session;
	}
	public String getUserId() {
		return userId;
	}
	
}
