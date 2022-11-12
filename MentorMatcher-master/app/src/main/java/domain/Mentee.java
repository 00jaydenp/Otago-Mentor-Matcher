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
public class Mentee {
    
    private Integer menteeID;
    private Person person;
    private Integer personID;
    private Integer studyYear;
    private String motivation;
    private String [] interests;
    private Boolean randomMatch;

    
    public Mentee(){
        
    }
    public Mentee(Integer menteeID, Integer personID, Integer studyYear, String motivation, String [] interests, Boolean randomMatch) {
        this.menteeID = menteeID;
        this.personID = personID;
        this.studyYear = studyYear;
        this.motivation = motivation;
        this.interests = interests;
        this.randomMatch = randomMatch;
    }

    
    

    public Integer getMenteeID() {
        return menteeID;
    }

    public void setMenteeID(Integer menteeID) {
        this.menteeID = menteeID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public Integer getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(Integer studyYear) {
        this.studyYear = studyYear;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String [] interests) {
        this.interests = interests;
    }

    public Boolean getRandomMatch() {
        return randomMatch;
    }

    public void setRandomMatch(Boolean randomMatch) {
        this.randomMatch = randomMatch;
    }  
}
