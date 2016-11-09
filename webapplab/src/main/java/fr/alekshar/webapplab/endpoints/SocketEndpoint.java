package fr.alekshar.webapplab.endpoints;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import fr.alekshar.webapplab.classes.WebsocketManagerSingleton;

@ServerEndpoint("/websocket/{userid}")
public class SocketEndpoint {
	
	@OnOpen
	public void onOpen(Session session, @PathParam("userid") String userid){
		WebsocketManagerSingleton.getInstance().connect(userid, session);
	}
	
    @OnClose
    public void onClose(Session session, CloseReason closeReason, @PathParam("userid") String userid) {
    	WebsocketManagerSingleton.getInstance().disconnect(userid, session);
    }

    @OnError
    public void onError(Session session, Throwable throwable, @PathParam("userid") String userid) {
    	WebsocketManagerSingleton.getInstance().disconnect(userid, session);
    }    
}
