/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author kieran
 */
public class Match {
    
    private Integer matchID;
    private Mentor mentor;
    private Integer mentorID;
    private Mentee mentee;
    private Boolean approved;
    private Integer menteeID;
    private Date matchDate;
    private Boolean adminApproved;
    
    public Match(){
        
    }

    public Match(Integer matchID,  Integer mentorID,   Integer menteeID, Boolean approved, Boolean adminApproved, Date matchDate) {
        this.matchID = matchID;
        this.mentorID = mentorID;
        this.approved = approved;
        this.adminApproved = adminApproved;
        this.menteeID = menteeID;
        this.matchDate = matchDate;
    }
    
    


    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    
    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getAdminApproved() {
        return adminApproved;
    }

    public void setAdminApproved(Boolean adminApproved) {
        this.adminApproved = adminApproved;
    }
    

    public Integer getMatchID() {
        return matchID;
    }

    public void setMatchID(Integer matchID) {
        this.matchID = matchID;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Integer getMentorID() {
        return mentorID;
    }

    public void setMentorID(Integer mentorID) {
        this.mentorID = mentorID;
    }

    public Mentee getMentee() {
        return mentee;
    }

    public void setMentee(Mentee mentee) {
        this.mentee = mentee;
    }

    public Integer getMenteeID() {
        return menteeID;
    }

    public void setMenteeID(Integer menteeID) {
        this.menteeID = menteeID;
    }

 
     
}
