package assignment3;

import java.util.Calendar;
import java.util.Set;

public class FutureMeetingImpl implements FutureMeeting {
	private Calendar date;
	private int id;
	private Set<Contact> contacts;
	
	public FutureMeetingImpl(Calendar date, int id, Set<Contact> contacts) {
		// TODO Auto-generated constructor stub
	}
	 public FutureMeetingImpl(){
		 
	 }

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Calendar getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Contact> getContacts() {
		// TODO Auto-generated method stub
		return null;
	}

}
