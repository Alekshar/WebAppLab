package fr.alekshar.webapplab.classes;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

public final class WebsocketManagerSingleton {
	private static WebsocketManagerSingleton instance;
	private List<UserSession> list = new ArrayList<UserSession>();

	private WebsocketManagerSingleton(){
	}
	
	public static WebsocketManagerSingleton getInstance() {
		if(instance == null){
			instance = new WebsocketManagerSingleton();
		}
		return instance;
	}

	public void connect(String userid, Session session) {
		list.add(new UserSession(session, userid));
	}

	public void disconnect(String userid, Session session) {
		UserSession found = null;
		for(UserSession userSession : list){
			if(userSession.getSession().equals(session)){
				found = userSession;
				break;
			}
		}
		if(found != null){
			list.remove(found);
		}
	}

	public List<UserSession> getSessions() {
		return new ArrayList<UserSession>(list);
	}

}
