import java.util.Calendar; 
import java.util.Set; 

import assignment3.Contact;

/**
*  A class to represent meetings * 
*  Meetings have unique IDs, scheduled date and a list of participating contacts */ 
public class MeetingImpl implements Meeting { //seems like this should be an abstract class, but whats the benefit?
	private int id;
	private Calendar date;
	private Set<Contact> participants;



/**
*  Returns the id of the meeting* 
*  @return the id of the meeting*/  	
	public int getId(){
 		return id;
	}

/**
*  Return the date of the meeting. * 
*  @return the date of the meeting. */  
	public Calendar getDate(){
		return date;
	}

/** 
*  Return the details of people that attended the meeting. * 
*  The list contains a minimum of one contact (if there were 
*  just two people: the user and the contact) and may contain an 
*  arbitraty number of them. * 
*  @return the details of people that attended the meeting. */  
	public Set<Contact> getContacts(){
		return participants;
	} 

// need a method for creating a participants list 
//should this be in the Manager? issue 6

	public void createParticipantList(){
		Set<Contact> participants = new HashSet<Contact>();
		
	}

}