package assignment3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ScratchPad {
	private Set<Contact> people;
	private List<Meeting> meetings;

	public Set<Contact> getPeople(){
		return people;
	}
	
	public void setMeetings(List<Meeting> meetings){
		this.meetings = meetings;
	}
	
	public List<Meeting> getMeetings(){
		return meetings;
	}
	
//	public List<PastMeeting> getPastMeetings(){
//		return meetings;
//	}
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
	
	public void meetingOrganisation(){
		Calendar date1 = new GregorianCalendar(2013, 0, 01, 12, 30);
		Calendar date2 = new GregorianCalendar(2013, 0, 21, 10, 0);
		Calendar date3 = new GregorianCalendar(2013, 01, 01, 11, 0);
		Calendar date4 = new GregorianCalendar(2013,01,21);
		Set<Contact> contacts = getPeople();
		Meeting meeting1 = new MeetingImpl(date1,1,contacts);
		Meeting meeting2 = new MeetingImpl(date2,2,contacts);
		Meeting meeting3 = new MeetingImpl(date3,3,contacts);
		Meeting meeting4 = new MeetingImpl(date4,4,contacts);
		List<Meeting> meetings = new LinkedList<Meeting>();
		setMeetings(meetings);
		meetings.add(meeting1);
		meetings.add(meeting2);
		meetings.add(meeting3);
		meetings.add(meeting4);
		
		System.out.println(meetings.size());
		List<Meeting> newMeetings = getMeetings();
		System.out.println(newMeetings.size()); //looks like has assigned to Class field as intended
		List<Meeting> pastMeetings = new LinkedList<Meeting>();
		
		/*Iterator<Meeting> it = newMeetings.iterator();
		while(it.hasNext()){
			if (it.next().getDate().before(Calendar.getInstance())){
				pastMeetings.add(it.next());
				newMeetings.remove(it.next());
			}
		}
		*/
		int i = 0;
		/*while(i<newMeetings.size()){
			if(newMeetings.get(i).getDate().before(Calendar.getInstance())){
				pastMeetings.add(newMeetings.get(i));
				newMeetings.remove(i);
			}
			i++;
		}*/	
		System.out.println(pastMeetings.size()); //need to cast to PastMeeting somehow
		System.out.println(newMeetings.size());
	}
	
	/**
	 * Always call this first
	 * @param id
	 * @return meeting
	 */
	public Meeting getMeeting(int id){
		//when someone wants to view a meeting, run this first
		Meeting meeting = null;
		System.out.println("id = " + id);
		if(getMeetings().get(id-1)==null){
			System.out.println("no meetings with id " + id);
			return meeting;
		}
		else if (getMeetings().get(id-1).getDate().before(Calendar.getInstance())){
			meeting = getMeetings().get(id-1);
			System.out.println("meeting with id " + id + " is in the past");
		}
		else{
			meeting = getMeetings().get(id-1);
			System.out.println("this is a future meeting");
		}
		
		return meeting;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScratchPad run = new ScratchPad();
	
		System.out.println("<?xml-stylesheet type="text/xsl" href="meetings.xsl"?>");
		/*	run.dateFinder();
		System.out.println(run.varargs(43,6,5,43));
		
		//run.tryAddContact();*/
		String name = "joe";
		String notes = "notes";
		System.out.println(run.trySetId("joe", "notes"));
		run.addNewContact(name, notes);
		run.meetingOrganisation();
		System.out.println("the meeting is " + run.getMeeting(3).getDate().getTime().toString());
		Calendar cal = Calendar.getInstance();
		
		System.out.println("today is " + cal.getTime().toString());
		
		Contact bob = new ContactImpl("bob",1);
		bob.addNotes("notes");
		String record = bob.toString();
		
		
		File data = new File("test.txt");
		try{
			FileWriter fw = new FileWriter(data, true);
			fw.write(record);
			fw.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		
		
	}

}
