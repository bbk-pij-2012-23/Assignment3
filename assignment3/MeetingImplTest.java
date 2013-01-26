package assignment3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

import org.junit.Test;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class MeetingImplTest {
	private Meeting catchup;
	

	
	@Before
	public void setUp() throws Exception {
		Calendar date = new GregorianCalendar(2013,0,21);
		Set<Contact> contacts = new HashSet<Contact>();
		Contact bob = new ContactImpl("bob", 1);
		Contact sally = new ContactImpl("sally", 2);
		contacts.add(bob);
		contacts.add(sally);
		catchup = new MeetingImpl(date, 1, contacts);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMeetingImpl(){
		assertNotNull(catchup);
	}

	@Test
	public void testGetId() {
		assertEquals(1, catchup.getId());
	}

	@Test
	public void testGetDate() {
		
		assertNotNull(catchup.getDate()); //doesn't test much but its a start
	}

	@Test
	public void testGetContacts() {
		assertNotNull(catchup.getContacts()); //doesn't test much but its a start
	}

}
