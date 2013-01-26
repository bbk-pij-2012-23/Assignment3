package assignment3;

import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl implements PastMeeting {	
	private String notes;
	private int id;
	private Calendar date;
	private Set<Contact> contacts;

	public String getNotes() {
		return notes;
	}
	
	/**
	 * additional method not in the PastMeeting inferface to allow notes to be set after construction 
	 * @param notes
	 */
	public void setNotes(String notes){
		this.notes = notes;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public Calendar getDate() {
		return date;
	}

	@Override
	public Set<Contact> getContacts() {
		return contacts;
	}

}
