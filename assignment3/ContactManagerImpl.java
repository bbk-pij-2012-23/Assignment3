package assignment3;

import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ContactManagerImpl implements ContactManager {
	private List<Meeting> meetingList;
	private List<Meeting> pastMeetingList; //to be changed to pastMeeting
	private Set<Contact> allContacts;
	
	
	
	public ContactManagerImpl() {
	}
	
	/**
	 * 
	 * @param meetingList
	 */
	public void setMeetingList(List<Meeting> meetingList){  //for testing
		this.meetingList = meetingList;
	}
	
	/**
	 * 
	 * @param allContacts
	 */
	public void setAllContacts(Set<Contact> allContacts){ //for testing
		this.allContacts = allContacts;
	}
	
	
	public Set<Contact> getAllContacts() {
	 	return allContacts;
	}
	
	public List<Meeting> getMeetingList(){
		return meetingList;
	}
	
	//other methods here to create a set of contacts and date to pass to this method
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException{
		// create a FutureMeeting, add it to a List<Meeting>		
		int meetingId = 0;
		if (getMeetingList() == null){
			meetingId = 1;
		}
		else{
			meetingId = getMeetingList().size() +1;
		}
		Meeting meeting = new MeetingImpl(date, meetingId, contacts);
		meetingList.add(meeting);
		return meetingId;
	}

	
	public Meeting getPastMeetingList(){
		return pastMeetingList;
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
	
	/**
	* Create a new contact with the specified name and notes.
	*
	* @param name the name of the contact.
	* @param notes notes to be added about the contact.
	* @throws NullPointerException if the name or the notes are null
	*/
	public void addNewContact(String name, String notes) throws NullPointerException {
		int id = 0;
		if(getAllContacts()==null){
			Set<Contact>allContacts = new HashSet<Contact>();
			this.setAllContacts(allContacts);
			id = 1;
		}
		else{
			id = allContacts.size() + 1;
		}
//		try{
			Contact newContact = new ContactImpl(name, id);
			allContacts.add(newContact);
//		}catch(NullPointerException ex){
//			ex.printStackTrace();
			//how do I distinguish between two sources of NullPointerException?
			// if null notes continue, else if (!notes.equals("")){newContact.addNotes(notes);}
			//if null name prompt for a name;
	
	}		//to add "successful completion" user feedback
	

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

	private void launch(){
		Set<Contact> allContacts = new HashSet<Contact>();
		this.setAllContacts(allContacts);
		//create a meeting list also
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ContactManagerImpl run = new ContactManagerImpl();
		run.launch();
	}

}
