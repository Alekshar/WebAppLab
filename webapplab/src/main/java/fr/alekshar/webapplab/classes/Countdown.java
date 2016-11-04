package fr.alekshar.webapplab.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Countdown {
	private int id;
	private String userid;
	private String dateString;
	private Date date;
	private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public Countdown(String userid, String dateString) throws ParseException {
		super();
		this.userid = userid;
		this.date = format.parse(dateString);
		this.dateString = dateString;
	}

	public String getDiffString() {
		return getDiffString(new Date());
	}

	public String getDiffString(Date seedDate) {
		long diff = seedDate.getTime() - date.getTime();

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
	
	
}
