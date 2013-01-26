package assignment3;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class ScratchPad {
	private Set<Contact> people;

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
	

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScratchPad run = new ScratchPad();
		run.dateFinder();
		System.out.println(run.varargs(43,6,5,43));
		
		Contact bob = new ContactImpl("bob", 1);
		Contact sally = new ContactImpl("sally", 2);
		
		
		
	}

}
