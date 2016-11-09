package fr.alekshar.webapplab.classes.countdown;

import java.util.Map;

import org.json.JSONObject;


public final class CountdownMessagesUtil {
	private static CountdownsManagerSingleton manager = CountdownsManagerSingleton.getInstance();;

	private CountdownMessagesUtil(){
	}
	
	public static void process(String message){
		Map<String, Object> obj = new JSONObject(message).toMap();
		Countdown countdown;
		switch((String) obj.get("action")){
		case "create":
			countdown = new Countdown((String) obj.get("userid"), (String) obj.get("date"), (String) obj.get("timezone"), (String) obj.get("name"));
			manager.addCountdown(countdown);
			break;
		case "update":
			countdown = manager.getCountdown((String) obj.get("userid"), (int) obj.get("id"));
			if(countdown != null){
				countdown.setDate((String) obj.get("date"), (String) obj.get("timezone"));
				countdown.setName((String) obj.get("name")); 
			}
			break;
		case "delete":
			manager.removeCountdown((String) obj.get("userid"), (int) obj.get("id"));
			break;
		}
	}
}
