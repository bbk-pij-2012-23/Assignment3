package assignment3;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ScratchPad {
	private Set<Contact> people;

	public Set<Contact> getPeople(){
		return people;
	}
	public void dateFinder(){
		Calendar date = new GregorianCalendar(2013, 0, 21);
		System.out.println("Today's date is " +  date.get(Calendar.DAY_OF_MONTH) + "/" + 
		date.get(Calendar.MONTH) + "1/" +  date.get(Calendar.YEAR));
		
	}
	
	public int varargs(int... numbers){
		int result = 0;
		for(int number : numbers){
			result = result + number;
		}
		return result;
	}
	

	private Set<Contact> tryAddContact(){
		Contact bob = new ContactImpl("bob", 1);
		Contact sally = new ContactImpl("sally", 2);
		Set<Contact> newSet = new HashSet<Contact>();
		newSet.add(sally);
		ContactManagerImpl test = new ContactManagerImpl();
		test.setAllContacts(newSet);
		printer(newSet);
		newSet.add(bob);
		printer(newSet);
		return newSet;
	}

	private void printer(Set<Contact> set){
		Iterator<Contact> it = set.iterator();
		while (it.hasNext()){
			System.out.println(it.next().getName());
		}
	}	
		
	
	private int trySetId(String name, String notes){
		Set<Contact> newSet = tryAddContact();
		int id;
		if (newSet.isEmpty()){
			id = 1;
		}
		else {
			id = newSet.size() + 1;
		}
		return id;
	}
	
	public void addNewContact(String name, String notes) throws NullPointerException {
		ContactManagerImpl test = new ContactManagerImpl();
		Set<Contact> contacts = new HashSet<Contact>();
		if (test.getAllContacts() ==null){
			test.setAllContacts(contacts);
		}
		
		int id = 0;
		if (contacts == null){
			id = 1;
		}
		else {
			id = contacts.size() + 1;
		}	
		
		Contact newContact = new ContactImpl(name, id);
		System.out.println(newContact.getName() + " and " + newContact.getId());
		System.out.println(contacts.isEmpty());
		
		contacts.add(newContact);
		printer(contacts);
	/*	}catch(NullPointerException ex){
			ex.printStackTrace();
			//how do I distinguish between two sources of NullPointerException?
			// if null notes continue, else if (!notes.equals("")){newContact.addNotes(notes);}
			//if null name prompt for a name;
		}*/
		//to add "successful completion" user feedback
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScratchPad run = new ScratchPad();
	/*	run.dateFinder();
		System.out.println(run.varargs(43,6,5,43));
		*/
		//run.tryAddContact();
		String name = "joe";
		String notes = "notes";
		System.out.println(run.trySetId("joe", "notes"));
		run.addNewContact(name, notes);
		
		
		
		
	}

}
