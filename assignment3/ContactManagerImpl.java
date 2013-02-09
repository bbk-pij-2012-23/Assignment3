package assignment3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class ContactManagerImpl implements ContactManager {
	private List<Meeting> meetingList;
	private List<PastMeeting> pastMeetingList; 
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
	 * @param pastMeetingList
	 */
	public void setPastMeetingList(List<PastMeeting> pastMeetingList){
		this.pastMeetingList = pastMeetingList;
	}
	
	/**
	 * 
	 * @param allContacts
	 */
	public void setAllContacts(Set<Contact> allContacts){ //for testing
		this.allContacts = allContacts;
	}
	
	/**
	 * 
	 * @return allContacts
	 */
	public Set<Contact> getAllContacts() {
	 	return allContacts;
	}
	
	public List<Meeting> getMeetingList(){
		return meetingList;
	}
	
	public List<PastMeeting> getPastMeetingList(){
		return pastMeetingList;
	}
	
	//other methods here to create a set of contacts and date to pass to this method
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException{
		// create a FutureMeeting, add it to a List<Meeting>		
		int meetingId = 0;
		if (getMeetingList() == null){
			meetingId = 1;
			List<Meeting> meetingList = new LinkedList<Meeting>(); //this seems a bit sloppy, since we need a meeting list from the start
			setMeetingList(meetingList);
		}
		else{
			meetingId = getMeetingList().size() +1;
		}
		Meeting meeting = new FutureMeetingImpl(date, meetingId, contacts);
		meetingList.add(meeting);
		System.out.println(meeting.toString());
		return meetingId;
	}

	/**
	*Returns the PAST meeting with the requested ID, or null if it there is none.
	* 
	* @param id the ID for the meeting
	* @return the meeting with the requested ID, or null if it there is none.
	* @throws IllegalArgumentException if there is a meeting with that ID happening in the future Method checks whether meeting exists by checking eetings 
	*/
	@Override	
	public PastMeeting getPastMeeting(int id) {
		PastMeeting meeting = null;
		try{
			if(meetingList == null){
				return meeting;
			}
			else{
				meeting = (PastMeeting) getMeetingList().get(id -1);
			}	
		}catch(IllegalArgumentException ex){
			ex.printStackTrace();
		}
		return meeting;
	}
	/**
	* Returns the FUTURE meeting with the requested ID, or null if there is none.
	*
	* @param id the ID for the meeting
	* @return the meeting with the requested ID, or null if it there is none.
	* @throws IllegalArgumentException if there is a meeting with that ID happening in the past
	*/
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		FutureMeeting meeting = null;
		try{
			meeting = (FutureMeeting) getMeetingList().get(id -1);
		}catch(IllegalArgumentException ex){
			ex.printStackTrace();
		}
		return meeting;
	}

	@Override
	public Meeting getMeeting(int id) {
		Meeting meeting = null;
		
		if(getMeetingList().get(id-1)==null){
			System.out.println("no meetings with id " + id);
			return meeting;
		}
		else if (getMeetingList().get(id-1).getDate().before(Calendar.getInstance())){
			meeting = getPastMeeting(id);
		}
		else{
			meeting = getFutureMeeting(id);
		}
		
		return meeting;
		
	}
	
	/**
	* Returns the list of future meetings scheduled with this contact.
	*
	* If there are none, the returned list will be empty. Otherwise,
	* the list will be chronologically sorted and will not contain any
	* duplicates.
	*
	* @param contact one of the user’s contacts
	* @return the list of future meeting(s) scheduled with this contact (maybe empty).
	* @throws IllegalArgumentException if the contact does not exist
	*/
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		List<Meeting> futureMeetingList = new ArrayList<Meeting>();
		Iterator<Meeting> it = getMeetingList().iterator();
		while (it.hasNext()){
			Set<Contact> temp = it.next().getContacts();
			Iterator<Contact> tempit = temp.iterator();
			while(tempit.hasNext()){
				if(tempit.next() == contact){
					futureMeetingList.add(it.next());
				}
			}
		}
		//System.out.println(futureMeetingList.toString());
		return futureMeetingList; //may have to create a field in the end but not for now...
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		//create a list(or some data structure) containing a subset of meetingList (where date  has not passed)
		List<Meeting> futureMeetingList = new LinkedList<Meeting>(); //change this when futureMeetingList has something to point at
		//System.out.println(futureMeetingList.toString());
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
		int id = 0;
		if(getMeetingList()==null){
			List<Meeting> meetingList = new LinkedList<Meeting>();
			setMeetingList(meetingList);
			id = 1;
		}
		else{
			id = getMeetingList().size()+1;
		}
		Meeting meeting = new PastMeetingImpl(date, id, contacts);
		meetingList.add(meeting);
/*		if (!text.equals("")){
			addMeetingNotes(id, text);
		}
*/	}

	@Override
	public void addMeetingNotes(int id, String text) {
		Meeting meeting = getMeeting(id);
		//work out how to call the right instantiation of PastMeeting and assign notes to its notes field 
		((PastMeetingImpl) meeting).setNotes(text); //this cast doesn't work
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
		try{
			Contact newContact = new ContactImpl(name, id);
			newContact.addNotes(notes);
			allContacts.add(newContact);
			System.out.println("Contact "+ newContact.getName() + " has been added to your contacts list");
		}catch(NullPointerException ex){
			ex.printStackTrace();
		}catch (Exception e) { //sort this out when handled exceptions properly in XMLMaker
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	//to be run on exit
	//this is high dependency, not a good implementation
	public String makeContactsXMLFile(String fileName) throws Exception { //change this to try/catches later
		XMLMaker xml = new XMLMaker();
		xml.setFile(fileName + ".xml");
		OutputStream output = new FileOutputStream(xml.getFile());
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(output);
		String rootElement = "contacts-list";
		String xslRef = "contacts.xsl";
		xml.createSkeleton(eventWriter, xslRef, rootElement);
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent newLine = eventFactory.createDTD("\n"); //adds the new line after the non-#PCDATA elements
		/*
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		*/
		
		Iterator<Contact> it = getAllContacts().iterator(); //I know allContacts can be seen but Keith says using the getter as standard is good practice
		while (it.hasNext()){
			StartElement startElement = eventFactory.createStartElement("", "", "contact");
			eventWriter.add(startElement);
			eventWriter.add(newLine);
			Contact person = it.next();
			xml.createElement(eventWriter, "id", Integer.toString(person.getId()));
		    xml.createElement(eventWriter, "name", person.getName());
		    xml.createElement(eventWriter, "note", person.getNotes());
			EndElement endElement = eventFactory.createEndElement("", "", "contact");
			eventWriter.add(endElement);
			eventWriter.add(newLine);
		}
		EndElement endElement = eventFactory.createEndElement("", "", rootElement);
		eventWriter.add(endElement);
		eventWriter.add(newLine);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();	
		return fileName;
		
	}
	
	//get rid of this repeated code
	public String makeMeetingsXMLFile(String fileName) throws Exception { //change this to try/catches later
		XMLMaker xml = new XMLMaker();
		xml.setFile(fileName + ".xml");
		OutputStream output = new FileOutputStream(xml.getFile());
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(output);
		String rootElement = "meetings-list";
		String xslRef = "meetings.xsl";
		xml.createSkeleton(eventWriter, xslRef, rootElement);
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent newLine = eventFactory.createDTD("\n"); //adds the new line after the non-#PCDATA elements
		/*
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		*/
		
		Iterator<Meeting> it = getMeetingList().iterator(); //I know allContacts can be seen but Keith says using the getter as standard is good practice
		while (it.hasNext()){
			StartElement startElement = eventFactory.createStartElement("", "", "meeting");
			eventWriter.add(startElement);
			eventWriter.add(newLine);
			Meeting meeting = it.next();
			xml.createElement(eventWriter, "id", Integer.toString(meeting.getId()));
		    xml.createElement(eventWriter, "date", meeting.getDate().getTime().toString());
		    Iterator<Contact> it1 = meeting.getContacts().iterator();
		    while(it1.hasNext()){
		    	Contact person = it1.next();
		    	xml.createElement(eventWriter, "contact", person.getName());
		    }
		    //xml.createElement(eventWriter, "note", pastMeeting.getNotes()); //only past meeting
			EndElement endElement = eventFactory.createEndElement("", "", "meeting");
			eventWriter.add(endElement);
			eventWriter.add(newLine);
		}
		EndElement endElement = eventFactory.createEndElement("", "", rootElement);
		eventWriter.add(endElement);
		eventWriter.add(newLine);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();	
		return fileName;
		
	}
	
	public Set<Contact> importContacts() throws Exception{
		ListMaker run = new ListMaker();
		setAllContacts(run.xmlToSet("contacts.xml"));
		return allContacts;
	}
	
	public List<Meeting> importMeetings() throws Exception{
		ListMaker run = new ListMaker();
		setMeetingList(run.xmlToList("meetings.xml"));
		return meetingList;
	}
	/* on opening or closing */
	
	public void updateMeetingList(){ //to be run on opening
		List<Meeting> meetingList = getMeetingList();
		if(getPastMeetingList() ==null){
			List<PastMeeting> pastMeetingList = new LinkedList<PastMeeting>();
			setPastMeetingList(pastMeetingList);
		}		
		int i = 0;
		while(i<meetingList.size()){
			if(meetingList.get(i).getDate().before(Calendar.getInstance())){
				pastMeetingList.add((PastMeeting) meetingList.get(i)); //dont think this casting works
			}	
			i++;
		}	
	}
	
	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	private void launch(){
		//create a Set<Contact> from contacts.xml and List<Meeting> from meetings.xml
		
		Set<Contact> allContacts = new HashSet<Contact>();
		this.setAllContacts(allContacts);
		System.out.println("hello world");
		
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
