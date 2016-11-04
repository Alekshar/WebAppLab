package fr.alekshar.webapplab.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CountdownsManagerSingleton {
	private static CountdownsManagerSingleton instance;
	Map<String, List<Countdown>> map = new HashMap<String, List<Countdown>>();

	private CountdownsManagerSingleton(){
	}
	
	public static CountdownsManagerSingleton getInstance(){
		if(instance == null){
			instance = new CountdownsManagerSingleton();
		}
		return instance;
	}
	
	public int addCountdown(Countdown countdown) {
		List<Countdown> list = map.get(countdown.getUserid());
		if(list == null){
			list = new ArrayList<Countdown>();
			map.put(countdown.getUserid(), list);
		}
		int higherId = 0;
		for(Countdown counter : list){
			if(counter.getId() > higherId){
				higherId = counter.getId();
			}
		}
		countdown.setId(higherId+1);
		list.add(countdown);
		return higherId+1;
	}

	public List<Countdown> getCountdownsFor(String userid) {
		List<Countdown> list = map.get(userid);
		if(list == null){
			return new ArrayList<Countdown>();
		}
		return list ;
	}

	public void removeCountdown(String userid, int counterid) {
		List<Countdown> list = map.get(userid);
		Countdown found = null;
		for(Countdown element : list){
			if(element.getId() == counterid){
				found = element;
				break;
			}
		}
		if(found != null){
			list.remove(found);
		}
	}
	
}
