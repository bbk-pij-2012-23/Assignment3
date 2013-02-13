package assignment3;

public class MeetingXML {
	private String fileName = "meetings.xml";
	private String xslRef = "meetings.xsl";
	private String rootElement = "meetings-list";
	private String element1 = "meeting";
	private String element2 = "id";
	private String element3 = "date";
	private String element4 = "contact";
	private String element5 = "note";
	public String getFileName() {
		return fileName;
	}
	public String getXslRef() {
		return xslRef;
	}
	public String getRootElement() {
		return rootElement;
	}
	public String getElement1() {
		return element1;
	}
	public String getElement2() {
		return element2;
	}
	public String getElement3() {
		return element3;
	}
	public String getElement4() {
		return element4;
	}
	public String getElement5() {
		return element5;
	}

}
