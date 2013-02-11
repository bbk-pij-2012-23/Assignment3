package assignment3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class ContactManagerImpl implements ContactManager {
	private List<Meeting> meetingList;
//	private List<PastMeeting> pastMeetingList; 
	private Set<Contact> allContacts;
	
		
	public ContactManagerImpl() {
	}
	
	/**
	 * 
	 * @param meetingList
	 */
	public void setMeetingList(List<Meeting> meetingList){ 
		this.meetingList = meetingList;
	}
	
/*
	 * 
	 * @param pastMeetingList
	 *
	public void setPastMeetingList(List<PastMeeting> pastMeetingList){
		this.pastMeetingList = pastMeetingList;
	}
*/	

	
	/**
	 * 
	 * @param allContacts
	 */
	public void setAllContacts(Set<Contact> allContacts){ 
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
	
/*	public List<PastMeeting> getPastMeetingList(){
		return pastMeetingList;
	}
*/	
	
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
		if(meetingList == null){
			return meeting;
		}
		else{
			meeting = (PastMeeting) getMeetingList().get(id -1);
			if(meeting.getDate().after(Calendar.getInstance())){
				throw new IllegalArgumentException("this meeting hasn't happened yet");
			}
			else{
				return meeting;
			}
		}
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
		if(meetingList == null){
			return meeting;
		}
		else{
			meeting = (FutureMeeting) getMeetingList().get(id -1);
			if(meeting.getDate().before(Calendar.getInstance())){
				throw new IllegalArgumentException("this meeting has already happened");
			}
			else{
				return meeting;
			}
		}
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
			Meeting tempMeeting = it.next();
			Set<Contact> tempContacts = tempMeeting.getContacts();
			Iterator<Contact> itC = tempContacts.iterator();
			while(itC.hasNext()){
				if(itC.next() == contact){
					futureMeetingList.add(tempMeeting);
				}
			}
		}
		//System.out.println(futureMeetingList.toString());
		return futureMeetingList; //may have to create a field in the end but not for now...
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		List<Meeting> futureMeetingList = new ArrayList<Meeting>();
		Iterator<Meeting> it = getMeetingList().iterator();
		while (it.hasNext()){
			Meeting tempMeeting = it.next();
			if(tempMeeting.getDate().equals(date)){
				futureMeetingList.add(tempMeeting);
			}
		}
		return futureMeetingList;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		//create a list(or some data structure) containing a subset of meetingList (where date has passed)
		List<PastMeeting> pastMeetingList = new ArrayList<PastMeeting>();
		Iterator<Meeting> itM = getMeetingList().iterator();
		while(itM.hasNext()){
			Meeting tempMeeting = itM.next();
			if (tempMeeting.getDate().before(Calendar.getInstance())){
				Set<Contact> tempContacts = tempMeeting.getContacts();
				Iterator<Contact> itC = tempContacts.iterator();
				while (itC.hasNext()){
					if (contact == itC.next()){
						pastMeetingList.add((PastMeeting) tempMeeting);
					}
				}				
			}
		}
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
		}	
	}
	
	/**
	* Returns a list containing the contacts that correspond to the IDs.
	*
	* @param ids an arbitrary number of contact IDs
	* @return a list containing the contacts that correspond to the IDs.
	* @throws IllegalArgumentException if any of the IDs does not correspond to a real contact
	*/
	@Override
	public Set<Contact> getContacts(int... ids) {
		Set<Contact> returnedSet = new HashSet<Contact>();
		try{
			for (int id : ids){
				Iterator<Contact> it = getAllContacts().iterator();
				boolean idExists = false;
				while (it.hasNext()){
					Contact e = it.next();
					if(e.getId()==id){
						returnedSet.add(e);
						idExists = true;
					}
				}
				if (idExists == false){
					throw new IllegalArgumentException("this contact could not be found");
				}
			}
		}catch(NullPointerException ex){
			ex.printStackTrace();
			System.out.println("There are no contacts in the list");
		}
		return returnedSet;
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
			Iterator<Contact> it = getAllContacts().iterator();
			while (it.hasNext()){
				Contact e = it.next();
				if(e.getName().contains(name)){
						names.add(e);
				}
				else{
					throw new NullPointerException("name is null");
				}
			}
		return names;
	}
	
	//to be run on exit
	//this is high dependency, not a good implementation
	public String makeContactsXMLFile(String fileName){ //change this to try/catches later
		XMLMaker xml = new XMLMaker();
		xml.setFile(fileName + ".xml");
		try{
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
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			System.out.println("could not access contacts file");
		}catch(XMLStreamException ex){
			ex.printStackTrace();
			System.out.println("there was a problem with the XML writer");
		}catch(Exception ex){
			ex.printStackTrace();
		}
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

	
	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	private void launch(){
		CommandLineInterface run = new CommandLineInterface();
		run.text();
		run.menu();
		String str = System.console().readLine();
		switch(str){
		case "1": System.out.println("Please type in the name of the contact you want to add");
		String name = System.console().readLine();
		System.out.println("Please type in any notes about the contact, e.g. job title, role in startup, contact details, expertise etc. else type none (you can add notes at a later date).");
		String notes = "" + System.console().readLine();
		addNewContact(name,notes);
		run.menu();
		
		//this does not handle incorrect format yet - UI is to show working code
		case "2": System.out.println("Type in a date and time for your meeting in the form dd/mm/yyyy hh:mm (24hr clock).");
		//parse string into yyyy, mm, dd, hh, mm
		str = System.console().readLine();
		Calendar date = new GregorianCalendar(yyyy,mm,dd,hh,min);
		System.out.println(allContacts.toString());
		System.out.println("your contact list is displayed above, choose the contacts to invite to this meeting, then enter their ids.");
		str = System.console().readLine();
		//not sure how to get these into the right int vararg
		//...
		int ids;
		Set<Contact> meetingContacts = getContacts(ids);
		addFutureMeeting(meetingContacts, date);
		
		case "3": System.out.println("");
		addNewPastMeeting(contacts,date,notes);
		
		case "4": System.out.println("");
		case "5": System.out.println("");
		case "6": System.out.println("");
		case "7": 
			
		}
		
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
