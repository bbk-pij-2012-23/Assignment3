package assignment3;

import java.util.Calendar;
import java.util.Set;

public abstract class MeetingImpl implements Meeting {
	private Calendar date;
	private int id;
	private Set<Contact> contacts; 
	

	public MeetingImpl(Calendar date, int id, Set<Contact> contacts){ //remove if pos
		this.date = date;
		this.id = id;
		this.contacts = contacts;
	}
	
	public MeetingImpl(Calendar date, Set<Contact>contacts){
		this.date = date;
		this.contacts = contacts;
	}

	public MeetingImpl() {
	}

	public int getId() {
		return id;
	}


	public Calendar getDate() {
		return date;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}
	@Override
	public String toString(){
		return "Meeting id: " + id + "\rDate: " + date.getTime().toString() + "\rContacts:" + contacts + "\r\r";
	}

}
