package assignment3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContactImplTest {
	private Contact bob;
	
	@Before
	public void setUp() throws Exception {
		bob = new ContactImpl("bob", 1);
		bob.addNotes("hello");
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	@Test
	public void testContactImpl() {
		
		fail("Not yet implemented");
	}
*/
	@Test
	public void testGetId() {
		assertEquals(1, bob.getId());
	}

	@Test
	public void testGetName() {
		assertEquals("bob", bob.getName());
	}

	@Test
	public void testGetNotes() {
		assertEquals("hello", bob.getNotes());
	}

	@Test
	public void testAddNotes() {
		bob.addNotes("goodbye");
		assertEquals("goodbye", bob.getNotes());
	}

}
