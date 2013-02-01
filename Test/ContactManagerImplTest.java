package Test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
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
import assignment3.PastMeeting;

public class ContactManagerImplTest {
	 Meeting testMeeting;
	 ContactManagerImpl testContactManager;
	@Before
	public void setUp() throws Exception {
		Calendar date = new GregorianCalendar(2013,01,03,15,30);
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
		Calendar newDate = new GregorianCalendar(2013,03,03,10,00);
		Set<Contact> newContacts = new HashSet<Contact>();
		newContacts.addAll(testMeeting.getContacts());
		testContactManager.addFutureMeeting(newContacts,newDate);
		List<Meeting> meetingList = testContactManager.getMeetingList();
		assertEquals(2, meetingList.size());
		//check added to ContactManagerImpl's meetingList field
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetPastMeeting(){
		//throw exception if pastmeeting not found (i.e in the future; assuming other test correctly gathers null ids)
		//return a meeting
		//return correct pastmeeting to getmeeting
		fail("Not yet implemented");
	}

	@Test
	public void testGetFutureMeeting() {
		//throw exception if pastmeeting not found (i.e in the future; assuming other test correctly gathers null ids)
		//return a meeting
		//return correct futuremeeting to getmeeting
		fail("Not yet implemented");
	}

	@Test
	public void testGetMeeting() {
		//identify the correct element on the Meeting list
		//return null if no meeting exists
		//branch based on whether meeting date is past or future
		//return correctly casted meeting
		fail("Not yet implemented");
	}

	@Test
	public void testGetFutureMeetingListContact() {
		//tests whether a list of meetings returned but not whether correct ones
		//assertNotNull(testContactManager.getFutureMeetingList(sally));
		fail("Not yet implemented");
	}

	@Test
	public void testGetFutureMeetingListCalendar() {
		//tests whether a list of meetings returned but not whether correct ones
		//Calendar cal = new GregorianCalendar(2013,03,03,10,00);
		//assertNotNull(testContactManager.getFutureMeetingList(cal));
		fail("Not yet implemented");
	}

	@Test
	public void testGetPastMeetingList() {
		//tests whether a list of meetings returned but not whether correct ones
		//assertNotNull(testContactManager.getPastMeetingList(sally));
		fail("Not yet implemented");
	}

	@Test
	public void testAddNewPastMeeting() {
		//check that id matches the expect id (i.e. number of meetings in list)
		List<PastMeeting> emptyList = new LinkedList<PastMeeting>();
		testContactManager.setPastMeetingList(emptyList);
		Set<Contact> contacts = testMeeting.getContacts();
		Calendar date = testMeeting.getDate();
		testContactManager.addNewPastMeeting(contacts, date, "text");
		assertNotNull(testContactManager.getPastMeetingList());
		//fail("Not yet implemented");
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
	public void testUpdateMeetingListMatchId() {
		//look up id in meetingList (which should have two elements with ids 1 and 2 atm
		assertNotNull(testContactManager.getPastMeeting(1));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testUpdateMeetingListCheckDate() {
		//check date and see if passed. I don't know how to do this separately from next test.
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdateMeetingList() { //design decision - storing meetings by type
		//test whether added to pastMeetingList
		testContactManager.updateMeetingList();
		List<PastMeeting> pastMeetingList = testContactManager.getPastMeetingList();
		assertEquals(1, pastMeetingList.size());
		//fail("Not yet implemented");
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
