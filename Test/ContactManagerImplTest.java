package Test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assignment3.Contact;
import assignment3.ContactImpl;
import assignment3.ContactManagerImpl;
import assignment3.Meeting;
import assignment3.MeetingImpl;

public class ContactManagerImplTest {
	 Meeting testMeeting;
	 ContactManagerImpl testContactManager;
	@Before
	public void setUp() throws Exception {
		Calendar date = new GregorianCalendar(20,01,2013);
		Set<Contact> contacts = new HashSet<Contact>();
		Contact bob = new ContactImpl("bob", 1);
		Contact sally = new ContactImpl("sally", 2);
		contacts.add(bob);
		contacts.add(sally);
		testMeeting = new MeetingImpl(date, 1, contacts);
		testContactManager = new ContactManagerImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testContactManagerImpl() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFutureMeetingID() {
		//check that id matches the expect id (i.e. number of meetings in list)
		Set<Contact> contacts = testMeeting.getContacts();
		Calendar date = testMeeting.getDate();
		assertEquals(1, testContactManager.addFutureMeeting(contacts, date));
	}
	
	@Test
	public void testAddFutureMeetingEmptyList() {
		//check added to ContactManagerImpl's meetingList field when empty
		Set<Contact> contacts = testMeeting.getContacts();
		Calendar date = testMeeting.getDate();
		testContactManager.addFutureMeeting(contacts, date);
		assertNotNull(testContactManager.getMeetingList());
	}
	
	@Test
	public void testAddFutureMeetingNotEmptyList() {
		Calendar newDate = new GregorianCalendar(03,02,2013);
		Set<Contact> newContacts = new HashSet<Contact>();
		newContacts.addAll(testMeeting.getContacts());
		testContactManager.addFutureMeeting(newContacts,newDate);
		List<Meeting> meetingList = testContactManager.getMeetingList();
		assertEquals(2, meetingList.size());
		//check added to ContactManagerImpl's meetingList field
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetPastMeetingMatchId() {
		//look up id in meetingList (which should have two elements with ids 1 and 2 atm
		assertNotNull(testContactManager.getPastMeeting(1));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testGetPastMeetingCheckDate() {
		//check date and see if passed
		testContactManager.getPastMeeting(1);
		testContactManager.getPastMeetingList(contact)
		assertEquals(1, pastMeeting.size());
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetPastMeeting() { //design decision - storing meetings by type
		//cast to pastMeeting if yes
		fail("Not yet implemented");
	}

	@Test
	public void testGetFutureMeeting() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMeeting() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFutureMeetingListContact() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFutureMeetingListCalendar() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPastMeetingList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNewPastMeeting() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMeetingNotes() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNewContact() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetContactsIntArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetContactsString() {
		fail("Not yet implemented");
	}

	@Test
	public void testFlush() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
