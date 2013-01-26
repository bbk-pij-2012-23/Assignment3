package assignment3;

public class ContactImpl implements Contact {
	private String name;
	private int id;
	private String note = "";
	
	public ContactImpl(String name, int id){
		this.name = name;
		this.id = id;
	}
	

	public int getId() {
		return id;
	}

	
	public String getName() {
		return name;
	}

	
	public String getNotes() {
		return note;
	}


	public void addNotes(String note) {
		this.note = note;
	}

}
