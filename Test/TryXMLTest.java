package Test;

import static org.junit.Assert.*;
import TryXML;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import assignment3.Contact;
import assignment3.ContactImpl;
import assignment3.ContactManagerImpl;
import assignment3.FutureMeetingImpl;
import assignment3.Meeting;

public class TryXMLTest {
	Meeting testMeeting;
	 ContactManagerImpl testContactManager;
	 Contact bob = new ContactImpl("bob", 1);
	Contact sally = new ContactImpl("sally", 2);
	 Calendar date = new GregorianCalendar(2013,06,03,15,30);
	
	 
	@Before
	public void setUp() throws Exception {
		//testContactManager = new ContactManagerImpl();
	}
	
	@Test
	public void testCreateSkeleton() {
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(bob);
		contacts.add(sally);
		testContactManager.addFutureMeeting(contacts, date);
		TryXML tryit = new TryXML();
		tryit.makeContactsXMLFile("contacts");
	}

	@Test
	public void testCreateElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeContactsXMLFile() {
		fail("Not yet implemented");
	}

}
