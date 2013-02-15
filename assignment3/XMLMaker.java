package assignment3;

//Draws heavily on the tutorial by Lars Vogel (http://www.vogella.com/articles/JavaXML/article.html)
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;


import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLMaker {
	
	
	public void createSkeleton(XMLEventWriter eventWriter, String xslRef, String rootElement)  {
		try{
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			XMLEvent newLine = eventFactory.createDTD("\n"); //for formating
			XMLEvent tab = eventFactory.createDTD("\t");//for formating xml
			StartDocument startDocument = eventFactory.createStartDocument();
			eventWriter.add(startDocument);
			eventWriter.add(newLine);
			XMLEvent stylesheet = eventFactory.createDTD("<?xml-stylesheet type=\"text/xsl\" href=\"" + xslRef + "\"?>");
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
	public void createElement(XMLEventWriter eventWriter, String elementLabel, String elementValue) throws Exception {
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
		
		//need to control the create element calls from the type specific stuff
	
	public void makeContactsXMLFile(String type){ 
		String file;
		String rootElement;
		String xslRef;
		Iterator it;
		String element1;
		String element2;
		String element3;
		String element4;
		String value2;
		String value3;
		String value4;
		
		if (type.equals("contacts")){
			ContactXML contactXML = new ContactXML();
			file = contactXML.getFileName();
			rootElement = contactXML.getRootElement();
			xslRef = contactXML.getXslRef();
			ContactManagerImpl getInfo = new ContactManagerImpl();
			it = getInfo.getAllContacts().iterator();
			Contact person = (Contact) it.next();
			element1 = contactXML.getElement1();
			element2 = contactXML.getElement2();
			element3 = contactXML.getElement3();
			element4 = contactXML.getElement4();
			value2 = Integer.toString(person.getId());
			value3 = person.getName();
			value4 = person.getNotes();
		}	
		else{
			MeetingXML meetingXML = new MeetingXML();
			file = meetingXML.getFileName();
			rootElement = meetingXML.getRootElement();
			xslRef = meetingXML.getXslRef();
			ContactManagerImpl getInfo = new ContactManagerImpl();
			it = getInfo.getMeetingList().iterator();
			Meeting meeting = (Meeting) it.next();
			element1 = meetingXML.getElement1();
			element2 = meetingXML.getElement2();
			element3 = meetingXML.getElement3();
			element4 = meetingXML.getElement4();
			value2 = Integer.toString(meeting.getId());
			value3 = meeting.getDate();//sort this out
			value4 = meeting.getNotes();//and this
		}		
		try{
			OutputStream output = new FileOutputStream(file);
			XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
			XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(output);
			createSkeleton(eventWriter, xslRef, rootElement);
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			XMLEvent newLine = eventFactory.createDTD("\n"); //adds the new line after the non-#PCDATA elements
			while (it.hasNext()){
				StartElement startElement = eventFactory.createStartElement("", "", element1);
				eventWriter.add(startElement);
				eventWriter.add(newLine);
				createElement(eventWriter, element2, value2);
			    createElement(eventWriter, element3, value3);
			    createElement(eventWriter, element4, value4);
				EndElement endElement = eventFactory.createEndElement("", "", element1);
				eventWriter.add(endElement);
				eventWriter.add(newLine);
			}
			EndElement endElement = eventFactory.createEndElement("", "", rootElement);
			eventWriter.add(endElement);
			eventWriter.add(newLine);
			eventWriter.add(eventFactory.createEndDocument());
			eventWriter.close();	
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
			System.out.println("could not access contacts file");
		}catch(XMLStreamException ex){
			ex.printStackTrace();
			System.out.println("there was a problem with the XML writer");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
	

	}
}