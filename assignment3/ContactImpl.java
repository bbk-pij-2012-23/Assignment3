package assignment3;

public class ContactImpl implements Contact {
	private String name;
	private int id;
	private String note = "";
	
	public ContactImpl(String name, int id){
		this.name = name;
		this.id = id;
	}
	/*temporary constructor for making Contact sets for individual meetings - update when resolved.
	 * */
	public ContactImpl(String data) {
		this.name = name;
	}

	public void setId(int id){ //not needed using current implementation but i think it will using xml
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public void setName(String name){ //as above
		this.name = name;
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
	
	 @Override
	  public String toString() {
		 return "Contact id: " + id + "\rname: " + name + "\rnotes: " + note + "\r\r";
	  }
	 
}
