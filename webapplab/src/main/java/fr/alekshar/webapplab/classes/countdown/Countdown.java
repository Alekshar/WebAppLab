package fr.alekshar.webapplab.classes.countdown;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONObject;

public class Countdown implements Serializable{
	private static final long serialVersionUID = -6920934251418207253L;
	private int id;
	private String userid;
	private String name;
	private String dateString;
	private String timezoneString;
	private Date date;
	private static DateTimeFormatter format =  ISODateTimeFormat.dateTimeNoMillis();
	
	public Countdown(String userid, String dateString, String timezoneString, String name) {
		super();
		this.userid = userid;
		this.name = name;
		this.setDate(dateString, timezoneString);
	}

	public String getDiffString() {
		return getDiffString(new Date());
	}

	public String getDiffString(Date seedDate) {
		long diff = date.getTime() - seedDate.getTime() ;

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays+" jour(s) "+diffHours+" heure(s) "+diffMinutes+" minute(s) "+diffSeconds+" seconde(s)";
	}

	public String getUserid() {
		return userid;
	}

	public String getDateString() {
		return dateString;
	}

	public Date getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate(String dateString, String timezoneString) throws IllegalArgumentException {
		this.dateString = dateString;
		this.timezoneString = timezoneString;
		this.date = format.parseDateTime(dateString+timezoneString).toDate();
	}

	public String getName() {
		return name;
	}

	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		json.put("userid", userid);
		json.put("id", id);
		json.put("name", name);
		json.put("value", this.getDiffString());
		json.put("date", this.getDateString());
		json.put("timezone", this.getTimezoneString());
		return json;
	}

	public String getTimezoneString() {
		return timezoneString;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
