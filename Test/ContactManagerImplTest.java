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
import assignment3.ContactManager;
import assignment3.ContactManagerImpl;
import assignment3.FutureMeetingImpl;
import assignment3.Meeting;
import assignment3.MeetingImpl;
import assignment3.PastMeeting;

public class ContactManagerImplTest {
	 Meeting testMeeting;
	 ContactManagerImpl testContactManager = new ContactManagerImpl();
	 
	@Before
	public void setUp() throws Exception {
		Calendar date = new GregorianCalendar(2013,06,03,15,30);
		Set<Contact> contacts = new HashSet<Contact>();
		Contact bob = new ContactImpl("bob", 1);
		Contact sally = new ContactImpl("sally", 2);
		contacts.add(bob);
		contacts.add(sally);
		testMeeting = new FutureMeetingImpl(date, 1, contacts);
		//testContactManager = new ContactManagerImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	/*passes*/
	@Test
	public void testContactManagerImpl() {
		ContactManager test = new ContactManagerImpl();
		assertNotNull(test);
	}

	/*passes*/
	@Test
	public void testAddFutureMeetingID() {
		//check that id matches the expect id (i.e. number of meetings in list)
		Set<Contact> contacts = testMeeting.getContacts();
		Calendar date = testMeeting.getDate();
		assertEquals(1, testContactManager.addFutureMeeting(contacts, date));
	}
	
	/*passes*/
	@Test
	public void testAddFutureMeetingEmptyList() {
		//check added to ContactManagerImpl's meetingList field when empty
		Set<Contact> contacts = testMeeting.getContacts();
		Calendar date = testMeeting.getDate();
		testContactManager.addFutureMeeting(contacts, date);
		assertNotNull(testContactManager.getMeetingList());
	}
	
	/*passes*/
	@Test
	public void testAddFutureMeetingPastDate() {
		//check added to ContactManagerImpl's meetingList field when empty
		Set<Contact> contacts = testMeeting.getContacts();
		Calendar date = new GregorianCalendar(2012,02,02,10,10);
		testContactManager.addFutureMeeting(contacts, date);
		assertNotNull(testContactManager.getMeetingList());
	}
	
	/*passes*/
	@Test 
	public void testAddFutureMeetingNotEmptyList() {
		Calendar newDate = new GregorianCalendar(2013,03,03,10,00);
		Set<Contact> newContacts = new HashSet<Contact>();
		newContacts.addAll(testMeeting.getContacts());
		testContactManager.addFutureMeeting(newContacts,newDate);
		testContactManager.addFutureMeeting(newContacts,newDate);
		List<Meeting> meetingList = testContactManager.getMeetingList();
		assertEquals(2, meetingList.size());
	}
	
	/*passes*/
	@Test
	public void testGetPastMeeting(){
		testAddNewPastMeeting();
		assertEquals(1,testContactManager.getPastMeeting(1).getId());
	}

	/*passes*/
	@Test(expected= IllegalArgumentException.class)
	public void testGetPastMeetingWithFutureDate(){
		testAddNewPastMeetingWithFutureDate();
		testContactManager.getPastMeeting(1).getId();
	}
	
	/*passes*/
	@Test(expected= IllegalArgumentException.class)
	public void testGetFutureMeetingWithPastDate() {
		testAddFutureMeetingPastDate();
		testContactManager.getFutureMeeting(1).getId();
	}
	
	/*passes*/
	@Test
	public void testGetFutureMeeting(){
		testAddFutureMeetingEmptyList();
		assertEquals(1,testContactManager.getFutureMeeting(1).getId());
	}
	
	/*passes*/
	@Test
	public void testGetMeetingWithFutureDate() {
		testAddFutureMeetingEmptyList();
		Meeting test = testContactManager.getMeeting(1);
		assertEquals(1,test.getId());
	}

	/*passes*/
	@Test
	public void testGetMeetingWithPastDate() {
		testAddNewPastMeeting();
		Meeting test = testContactManager.getMeeting(1);
		assertEquals(1,test.getId());
	}
	
	/*FAILS*/
	@Test
	public void testGetFutureMeetingListContact() {
		//tests whether a list of meetings returned but not whether unique or corrected ordered. Method doesn't do that yet anyway
		Contact sally = new ContactImpl("sally", 2);
		Calendar newDate = new GregorianCalendar(2013,03,03,10,00);
		Set<Contact> newContacts = new HashSet<Contact>();
		newContacts.add(sally);
		testContactManager.addFutureMeeting(newContacts,newDate);
		testContactManager.addFutureMeeting(newContacts,newDate);
		List<Meeting> futureMeetingList = testContactManager.getFutureMeetingList(sally);
		System.out.println("Meetings with sally: " + futureMeetingList.size());
		assertEquals("2", futureMeetingList.size());
	}

	/*FAILS*/
	@Test
	public void testGetFutureMeetingListCalendar() {
		//tests whether a list of meetings returned but not whether correct ones
		Contact sally = new ContactImpl("sally", 2);
		Calendar newDate = new GregorianCalendar(2013,03,03,10,00);
		Set<Contact> newContacts = new HashSet<Contact>();
		newContacts.add(sally);
		testContactManager.addFutureMeeting(newContacts,newDate);
		testContactManager.addFutureMeeting(newContacts,newDate);
		List<Meeting> futureMeetingList = testContactManager.getFutureMeetingList(newDate);
		System.out.println("meetings on 3rd April: " + futureMeetingList.size());
		assertEquals("2", futureMeetingList.size());
	}

	@Test
	public void testGetPastMeetingList() {
		//tests whether a list of meetings returned but not whether correct ones
		//assertNotNull(testContactManager.getPastMeetingList(sally));
		fail("Not yet implemented");
	}

	/*passes*/
	@Test
	public void testAddNewPastMeeting() {
		//check that id matches the expect id (i.e. number of meetings in list)
		Set<Contact> contacts = testMeeting.getContacts();
		Calendar date = new GregorianCalendar(2012,02,02,02,02,02);
		testContactManager.addNewPastMeeting(contacts, date, "text");
		assertNotNull(testContactManager.getMeetingList());
		//fail("Not yet implemented");
	}
	
	/*passes*/
	@Test
	public void testAddNewPastMeetingWithFutureDate() {
		//check that id matches the expect id (i.e. number of meetings in list)
		Set<Contact> contacts = testMeeting.getContacts();
		Calendar date = new GregorianCalendar(2013,06,30,10,00);
		testContactManager.addNewPastMeeting(contacts, date, "text");
		assertNotNull(testContactManager.getMeetingList());
		//fail("Not yet implemented");
	}

	@Test
	public void testAddMeetingNotes() {
		//add testpastmeeting
		//assertEquals("notes", testPastMeeting.getNotes());
		fail("Not yet implemented");
	}

	/*passes*/
	@Test
	public void testAddNewContact() {
		//ContactManagerImpl tester = new ContactManagerImpl();
		String name = "ellie";
		String notes = "some notes about ellie";
		testContactManager.addNewContact(name, notes);
		String anothername = "chris";
		String othernotes = "stuff about chris";
		testContactManager.addNewContact(anothername, othernotes);
		assertEquals(2,testContactManager.getAllContacts().size());
	}
	
	/*passes*/
	@Test
	public void testGetContactsIntArray1Contact() {
		Set<Contact> returnedSet = new HashSet<Contact>();
		testContactManager.addNewContact("ellie", "notes");
		returnedSet = testContactManager.getContacts(1);
		assertEquals(1, returnedSet.size());
	}
	
	/*passes*/
	@Test
	public void testGetContactsIntArray2Contacts() {
		Set<Contact> returnedSet = new HashSet<Contact>();
		testContactManager.addNewContact("ellie", "notes");
		testContactManager.addNewContact("bob",  "stuff");
		testContactManager.addNewContact("sue", "notes about sue");
		returnedSet = testContactManager.getContacts(1,3);
		assertEquals(2, returnedSet.size());
	}
	/*passes*/
	@Test (expected = IllegalArgumentException.class)
	public void testGetContactsIntArrayIllegalArgumentException() {
		testContactManager.addNewContact("ellie", "notes");
		testContactManager.getContacts(3);
	}
	
	/*fails but I don't know why because I expect a nullpointerexception, maybe it has others*/
	@Test (expected = NullPointerException.class)
	public void testGetContactsIntArrayNullPointerException() {
		testContactManager.getContacts(1);
	}
	
	/*passes*/
	@Test
	public void testGetContactsString() {
		testContactManager.addNewContact("eleanor mann", "none");
		assertNotNull(testContactManager.getContacts("or m"));
	}
	
	/*passes*/
	@Test
	public void testGetContactsStringMultiple() {
		testContactManager.addNewContact("eleanor mann", "none");
		testContactManager.addNewContact("oliver mann", "n/a");
		Set<Contact> test = testContactManager.getContacts("mann");
		assertEquals(2, test.size());
	}

	//what happens if parameter is null?
	@Test (expected = NullPointerException.class)
	public void testGetContactsNullString() {
		String name = null;
		testContactManager.addNewContact("eleanor mann", "none");
		Set<Contact> test = testContactManager.getContacts(name);
		assertEquals(0,test.size());
	}
/*
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
*/	
	
	/*passes*/
	//this is a rubbish test, it only checks whether filename assigned. 
	@Test
	public void testMakeContactsXMLFileCreated() throws Exception{
		testAddNewContact();
		//System.out.println(testContactManager.getAllContacts().toString());
		assertEquals("testcontacts", testContactManager.makeContactsXMLFile("testcontacts")); 
	}
	
	/*passes*/
	//only partial test of the method (see above)
	@Test
	public void testMakeMeetingsXMLFileCreated() throws Exception{
		testAddFutureMeetingNotEmptyList();
		//System.out.println(testContactManager.getMeetingList().toString());
		assertEquals("testmeetings", testContactManager.makeMeetingsXMLFile("testmeetings")); 
	}
	
	/*passes*/
	@Test
	public void testXSLToSet() throws Exception {
		assertNotNull(testContactManager.importContacts());
	}
	
	/*passes*/
	@Test
	public void testXSLToList() throws Exception {
		assertNotNull(testContactManager.importMeetings());
	}
	
	/*passes*/
	@Test
	public void testSpecialCharactersInString(){
		String expression = "<?xml-stylesheet type=\"text/xsl\" href=\"contacts.xsl\"?>"; //couldn't work out how to create a parser, so using this work around
		System.out.println(expression);
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
