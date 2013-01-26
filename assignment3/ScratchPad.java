package assignment3;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ScratchPad {


	public void launch(){
		Calendar date = new GregorianCalendar(2013, 0, 21);
		System.out.println("Today's date is " +  date.get(Calendar.DAY_OF_MONTH) + "/" + 
		date.get(Calendar.MONTH) + "1/" +  date.get(Calendar.YEAR));
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScratchPad run = new ScratchPad();
		run.launch();
		
		
	}

}
