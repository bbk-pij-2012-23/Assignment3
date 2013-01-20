/** 
*  A meeting that was held in the past. * 
*  It includes your notes about what happened and what was agreed. */  

public class PastMeetingImpl implements PastMeeting, extends MeetingImpl { // see issue 3
	private String meetingNotes = ""; //see issue 1
/** 
*  Returns the notes from the meeting. * 
*  If there are no notes, the empty string is returned. * 
*  @return the notes from the meeting. */  
	public String getNotes() {
		return meetingNotes;
	} 

} 
