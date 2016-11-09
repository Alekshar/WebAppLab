package fr.alekshar.webapplab.classes.countdown;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;

import fr.alekshar.webapplab.classes.UserSession;
import fr.alekshar.webapplab.classes.WebsocketManagerSingleton;

public class SocketsCountdownThread extends Thread{
	public static void main(String[] args) {
		new SocketsCountdownThread().start();
	}
	
	private WebsocketManagerSingleton manager;
	private CountdownsManagerSingleton countdownManager;

	public SocketsCountdownThread(){
	}
	
	@Override
	public void run() {
		manager = WebsocketManagerSingleton.getInstance();
		countdownManager = CountdownsManagerSingleton.getInstance();
		while(true){
			List<UserSession> sessions = manager.getSessions();
			for(UserSession session : sessions){
				try {
					StringBuilder jsonOutput = new StringBuilder("[");
					JSONArray json = new JSONArray();
					for(Countdown countdown : countdownManager.getCountdownsFor(session.getUserId())){
						json.put(countdown.toJSONObject());
					}
					session.getSession().getBasicRemote().sendText(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
