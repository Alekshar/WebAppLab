package fr.alekshar.webapplab.classes.countdown;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CountdownsManagerSingleton {
	private static CountdownsManagerSingleton instance;
	private Map<String, List<Countdown>> map = new HashMap<String, List<Countdown>>();

	@SuppressWarnings("unchecked")
	private CountdownsManagerSingleton(){
		map = loadMap();
	}

	public static CountdownsManagerSingleton getInstance(){
		return getInstance(false);
	}
	public static CountdownsManagerSingleton getInstance(boolean recreate){
		if(instance == null || recreate){
			instance = new CountdownsManagerSingleton();
		}
		return instance;
	}
	
	public int addCountdown(Countdown countdown) {
		List<Countdown> list =  map.get(countdown.getUserid());
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
		saveMap();
		return higherId+1;
	}

	public List<Countdown> getCountdownsFor(String userid) {
		List<Countdown> list =  map.get(userid);
		if(list == null){
			return new ArrayList<Countdown>();
		}
		return list ;
	}

	public void removeCountdown(String userid, int counterid) {
		List<Countdown> list =  map.get(userid);
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
		saveMap();
	}

	public void updateCountdown(String userid, int counterid, String newDateString, String newTimezoneString) {
		List<Countdown> list = map.get(userid);
		Countdown found = null;
		for(Countdown element : list){
			if(element.getId() == counterid){
				found = element;
				break;
			}
		}
		if(found != null){
			found.setDate(newDateString, newTimezoneString);
		}
		saveMap();
	}

	public Countdown getCountdown(String userid, int counterid) {
		for(Countdown counter : this.getCountdownsFor(userid)){
			if(counter.getId() == counterid){
				return counter;
			}
		}
		return null;
	}

	private Map<String, List<Countdown>> loadMap() {
		try {
	         FileInputStream fileIn = new FileInputStream("database.db");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         map = (Map) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	      }
		return map;
	}

	private synchronized void saveMap() {
         try {
    		 FileOutputStream fileOut =
    		         new FileOutputStream("database.db");
             ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(map);
	         out.close();
	         fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
