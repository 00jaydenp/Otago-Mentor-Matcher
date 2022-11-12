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
public class UndergraduateExperience {
    
    private Integer undergradExperienceID;
    private Person person;
    private Integer personID;
    private String undergradMajor;
    private String undergradCourse;
    private String undergradInstitution;
    private Integer undergradYear;

    
    public UndergraduateExperience(){
        
    }
    
    public UndergraduateExperience(Integer undergradExperienceID, Integer personID, String undergradMajor, String undergradCourse, String undergradInstitution, Integer undergradYear) {
        this.undergradExperienceID = undergradExperienceID;
        this.personID = personID;
        this.undergradMajor = undergradMajor;
        this.undergradCourse = undergradCourse;
        this.undergradInstitution = undergradInstitution;
        this.undergradYear = undergradYear;
    }
    
    
    public Integer getUndergradExperienceID() {
        return undergradExperienceID;
    }

    public void setUndergradExperienceID(Integer undergradExperienceID) {
        this.undergradExperienceID = undergradExperienceID;
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

    public String getUndergradMajor() {
        return undergradMajor;
    }

    public void setUndergradMajor(String undergradMajor) {
        this.undergradMajor = undergradMajor;
    }

    public String getUndergradCourse() {
        return undergradCourse;
    }

    public void setUndergradCourse(String undergradCourse) {
        this.undergradCourse = undergradCourse;
    }

    public String getUndergradInstitution() {
        return undergradInstitution;
    }

    public void setUndergradInstitution(String undergradInstitution) {
        this.undergradInstitution = undergradInstitution;
    }

    public Integer getUndergradYear() {
        return undergradYear;
    }

    public void setUndergradYear(Integer undergradYear) {
        this.undergradYear = undergradYear;
    }

  
    
    
}
