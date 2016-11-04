package fr.alekshar.webapplab.classes;

import java.io.IOException;
import java.util.List;

public class SocketsCountdownThread extends Thread{
	public static void main(String[] args) {
		new SocketsCountdownThread().start();
	}
	
	private WebsocketManagerSingleton manager;

	public SocketsCountdownThread(){
	}
	
	@Override
	public void run() {
		manager = WebsocketManagerSingleton.getInstance();
		while(true){
			List<UserSession> sessions = manager.getSessions();
			for(UserSession session : sessions){
				try {
					session.getSession().getBasicRemote().sendText(String.valueOf(System.currentTimeMillis()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
