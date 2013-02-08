package assignment3;

//Draws heavily on the tutorial by Lars Vogel (http://www.vogella.com/articles/JavaXML/article.html)
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLMaker {
	private String contactsFile;
	
	public XMLMaker() {
		// TODO Auto-generated constructor stub
	}
	
	public void setFile(String contactsFile){
		this.contactsFile = contactsFile;
	}
	
	public String getFile(){
		return contactsFile;
	}
	
	public void createSkeleton(XMLEventWriter eventWriter, String rootElement)  {
		try{
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			XMLEvent newLine = eventFactory.createDTD("\n"); //for formating
			XMLEvent tab = eventFactory.createDTD("\t");//for formating xml
			StartDocument startDocument = eventFactory.createStartDocument();
			eventWriter.add(startDocument);
			eventWriter.add(newLine);
			XMLEvent stylesheet = eventFactory.createDTD("<?xml-stylesheet type=\"text/xsl\" href=\"contacts.xsl\"?>");
			eventWriter.add(stylesheet);
			StartElement element = eventFactory.createStartElement("","",rootElement);
			eventWriter.add(element);
			eventWriter.add(newLine);
			eventWriter.add(tab);
		}catch(XMLStreamException ex){
			ex.printStackTrace();
			System.out.println("There was a problem creating the XML document. See stack trace for details");
		}
	}
	
	//handle exceptions properly
	//really should be able to encapsulate more than this. maybe for v2 now.
	public void createElement(XMLEventWriter eventWriter, String elementLabel, String elementValue) throws Exception{
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent newLine = eventFactory.createDTD("\n"); //for formating
		XMLEvent tab = eventFactory.createDTD("\t");//for formating xml
		StartElement element = eventFactory.createStartElement("","",elementLabel);
		eventWriter.add(tab);
		eventWriter.add(element);
		Characters characters = eventFactory.createCharacters(elementValue);
		eventWriter.add(characters); 
		EndElement endElement = eventFactory.createEndElement("", "", elementLabel);
		eventWriter.add(endElement);
		eventWriter.add(newLine);
	}
}
