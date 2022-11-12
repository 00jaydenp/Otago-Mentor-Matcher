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
public class Mentor {
    
    private Integer mentorID;
    private Person person;
    private Integer personID;
    private String email;
    private String careerHistory;
    private String preferredCommunication;
    private String[] mentoringPreference;
    private String linkedIn;
    private Integer capacity;
    private String currentTitle;
    private String currentOrganisation;
	

    
    public Mentor(){
    
}
    public Mentor(Integer mentorID, String email, String careerHistory, String preferredCommunication, String[] mentoringPreference, String linkedIn, Integer capacity, String currentTitle, String currentOrganisation) {
        this.mentorID = mentorID;
        this.email = email;
        this.careerHistory = careerHistory;
        this.preferredCommunication = preferredCommunication;
        this.mentoringPreference = mentoringPreference;
        this.linkedIn = linkedIn;
        this.capacity = capacity;
        this.currentTitle = currentTitle;
        this.currentOrganisation = currentOrganisation;
    }


    public Integer getMentorID() {
        return mentorID;
    }

    public void setMentorID(Integer mentorID) {
        this.mentorID = mentorID;
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
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCareerHistory() {
        return careerHistory;
    }

    public void setCareerHistory(String careerHistory) {
        this.careerHistory = careerHistory;
    }

    public String getPreferredCommunication() {
        return preferredCommunication;
    }

    public void setPreferredCommunication(String preferredCommunication) {
        this.preferredCommunication = preferredCommunication;
    }

    public String[] getMentoringPreference() {
        return mentoringPreference;
    }

    public void setMentoringPreference(String[] mentoringPreference) {
        this.mentoringPreference = mentoringPreference;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCurrentTitle() {
        return currentTitle;
    }

    public void setCurrentTitle(String currentTitle) {
        this.currentTitle = currentTitle;
    }

    public String getCurrentOrganisation() {
        return currentOrganisation;
    }

    public void setCurrentOrganisation(String currentOrganisation) {
        this.currentOrganisation = currentOrganisation;
    }

    
   
}
