package assignment3;

public class CommandLineInterface {

	public void text(){
		System.out.println("ContactManager");
		System.out.println("Manage your meetings diary and contacts.");
		System.out.println("A new account has been created for you. What would you like to do now? (type in number and hit enter)");
	}
	
	public void menu(){
		System.out.println("1. Add new contacts \n2. Add a future meeting to your diary \n3. Add details of a past meeting and meetings notes to your meeting notes repository \n4. Get the meeting schedule for a contact");
		System.out.println("5. Get the meetings scheduled for a particular date \n6. Find contact details by ID numbers \n7. Save and exit");
	}
	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
