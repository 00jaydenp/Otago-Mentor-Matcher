/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author kieran
 */
public class Meeting {
    
    private Integer meetingID;
    private Match match; 
    private Integer matchID;
    private String meetingTime;
    private Integer meetingLength;
    private Boolean inPerson;
    private String Notes; 

    public Integer getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(Integer meetingID) {
        this.meetingID = meetingID;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getMatchID() {
        return matchID;
    }

    public void setMatchID(Integer matchID) {
        this.matchID = matchID;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public Integer getMeetingLength() {
        return meetingLength;
    }

    public void setMeetingLength(Integer meetingLength) {
        this.meetingLength = meetingLength;
    }

    public Boolean getInPerson() {
        return inPerson;
    }

    public void setInPerson(Boolean inPerson) {
        this.inPerson = inPerson;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }



  
 
}
