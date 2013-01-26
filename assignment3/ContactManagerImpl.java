package assignment3;

import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ContactManagerImpl implements ContactManager {
	private List<Meeting> meetingList;
	private Set<Contact> allContacts;
	
	
	public ContactManagerImpl() {
	}
	
	//other methods here to create a set of contacts and date to pass to this method
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		// create a FutureMeeting, add it to a List<Meeting>		
		int meetingId = 0;
		Meeting meeting = new FutureMeetingImpl();
		return meetingId;
	}

	@Override	
	public PastMeeting getPastMeeting(int id) {
		//after FutureMeeting date has passed, look up meeting in List<Meeting> 
		// convert it to a PastMeeting meeting
		return meeting;  
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {
		//before FutureMeeting date has passed, look up meeting in List<Meeting> 
		return meeting;
	}

	@Override
	public Meeting getMeeting(int id) {
		//not really sure what this does or why its different from the more specific methods
		return meeting;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		//create a list(or some data structure) containing a subset of meetingList (where date  has not passed)
		List<Meeting> futureMeetingList = new LinkedList<Meeting>(); //change this when futureMeetingList has something to point at
		return futureMeetingList; //may have to create a field in the end but not for now...
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		//create a list(or some data structure) containing a subset of meetingList (where date  has not passed)
		List<Meeting> futureMeetingList = new LinkedList<Meeting>(); //change this when futureMeetingList has something to point at
		return futureMeetingList;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		//create a list(or some data structure) containing a subset of meetingList (where date has passed)
		List<PastMeeting> pastMeetingList = new LinkedList<PastMeeting>(); //change this when something can be assigned to pastMeetingList (pos not linkedlist)
		return pastMeetingList;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		// add to meetingList 
		//find id by size of list
		int id = meetingList.size()+1;
		Meeting meeting = new MeetingImpl(date, id, contacts);
		meetingList.add(meeting);
		if (!text.equals("")){
			addMeetingNotes(id, text);
		}
	}

	@Override
	public void addMeetingNotes(int id, String text) {
		Meeting meeting = getMeeting(id);
		//work out how to call the right instantiation of PastMeeting and assign notes to its notes field 
		((PastMeetingImpl) meeting).setNotes(text);
	}	
		
	public void addNewContact(String name, String notes) {
		// count contacts in Set<Contact> contacts
		int id = allContacts.size() + 1;
		Contact newContact = new ContactImpl(name, id);
		if (!notes.equals("")){
			newContact.addNotes(notes);
		}
	}

	@Override
	public Set<Contact> getContacts(int... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* Returns a list with the contacts whose name contains that string.
	*
	* @param name the string to search for
	* @return a list with the contacts whose name contains that string.
	* @throws NullPointerException if the parameter is null
	*/
	@Override
	public Set<Contact> getContacts(String name) {
		Set<Contact> names = new HashSet<Contact>();
		//procedure to look up the string in allContacts
		//what about regex?
		return names;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}