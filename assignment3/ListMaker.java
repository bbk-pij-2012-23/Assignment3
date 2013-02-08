/**
 * 
 */
package assignment3;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * @author Student
 *
 */
public class ListMaker {
	private static String CONTACT = "contact";
	private static String NAME = "name";
	private static String ID = "id";
	private static String MEETING = "meeting";
	private static String DATE = "date";
	
	/**
	 * 
	 * @param fileName
	 * @return allContacts
	 * @throws Exception
	 */
	public Set<Contact> xmlToSet(String fileName) throws Exception {
		Set<Contact> allContacts = new HashSet<Contact>();
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream in = new FileInputStream(fileName); 
		XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		      // Read the XML document
		Contact person = null;
		String contactName = null;
		int contactId = 0;
		while (eventReader.hasNext()) { //iterate down the xmlevents
	        XMLEvent event = eventReader.nextEvent();
	        if (event.isStartElement()) { //if there is a < then set the event to be < event
	        	StartElement startElement = event.asStartElement();
		          // If we have a item element we create a new item
		        if (startElement.getName().getLocalPart() == (CONTACT)) {
		        	if (event.isStartElement()) {
		        		if (event.asStartElement().getName().getLocalPart().equals(NAME)) {
		        			event = eventReader.nextEvent();
		        			contactName = event.asCharacters().getData();
		        			continue;
		        		}
		        		if (event.asStartElement().getName().getLocalPart().equals(ID)) {
		        			event = eventReader.nextEvent();
		        			contactId = Integer.parseInt(event.asCharacters().getData());
		        			continue;
		        		}
		        		person = new ContactImpl(contactName, contactId);
		        		allContacts.add(person);
		        	}
		        }
	        }  
		}
		System.out.println(allContacts.size());
	    return allContacts;
	}
	/**
	 * 
	 * @param fileName
	 * @return allContacts
	 * @throws Exception
	 */
	public List<Meeting> xmlToList(String fileName) throws Exception {
		List<Meeting> meetingList = new ArrayList<Meeting>();
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream in = new FileInputStream(fileName); 
		XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		      // Read the XML document
		Meeting aMeeting = null;
		Calendar meetingDate = null;
		int meetingId = 0;
		Set<Contact> meetingContacts = new HashSet<Contact>();
		while (eventReader.hasNext()) { //iterate down the xmlevents
	        XMLEvent event = eventReader.nextEvent();
	        if (event.isStartElement()) { //if there is a < then set the event to be < event
	        	StartElement startElement = event.asStartElement();
		          // If we have a item element we create a new item
		        if (startElement.getName().getLocalPart() == (MEETING)) {
		        	if (event.isStartElement()) {
		        		if (event.asStartElement().getName().getLocalPart().equals(DATE)) {
		        			event = eventReader.nextEvent();
		        			meetingDate = Calendar.getInstance();
		        		    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		        		    meetingDate.setTime(sdf.parse(event.asCharacters().getData()));// Jigar Joshi; Mar 14 '11 (StackOverflow.com)
		        		    continue;
		        		}
		        		if (event.asStartElement().getName().getLocalPart().equals(ID)) {
		        			event = eventReader.nextEvent();
		        			meetingId = Integer.parseInt(event.asCharacters().getData());
		        			continue;
		        		}
		        		if (event.asStartElement().getName().getLocalPart().equals(CONTACT)){
		        			event = eventReader.nextEvent();
		        			//do something to create a set of contacts
		        			Contact person = new ContactImpl(event.asCharacters().getData());
		        			meetingContacts.add(person);
		        			continue;
		        		}
		        		aMeeting = new MeetingImpl(meetingDate, meetingId, meetingContacts);
		        		meetingList.add(aMeeting);
		        	}
		        }
	        }  
		}
		System.out.println(meetingList.size());
	    return meetingList;
	}
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		ListMaker run = new ListMaker();
		run.xmlToSet("D:\\testcontacts.xml");
		
	}

}
