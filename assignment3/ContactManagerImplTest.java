package assignment3;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ContactManagerImplTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
	}
	
	@Before
	public void setUp() throws Exception {
	//	ContactManagerImpl test = new ContactManagerImpl();		
	//don't know how to 
	}

	@After
	public void tearDown() throws Exception {
	}

/*	@Test
	public void testContactManagerImpl() {
		ContactManager test = new ContactManagerImpl();
		assertNotNull(test);
		//fail("Not yet implemented");
	}
*/
	
	@Test
	public void testSetAllContacts(){
		ContactManagerImpl test = new ContactManagerImpl();	
		Set<Contact> testSet = new HashSet<Contact>();
		test.setAllContacts(testSet);
	}
	@Test
	public void testAddFutureMeeting() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPastMeeting() {
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
	public void testGetContacts(){
		fail("Not yet implemented");
	}
	@Test
	public void testAddNewContactName() {
		ContactManagerImpl test = new ContactManagerImpl();	
		test.addNewContact("joe", "notes");
		assertNotNull(test.getAllContacts());
		
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
