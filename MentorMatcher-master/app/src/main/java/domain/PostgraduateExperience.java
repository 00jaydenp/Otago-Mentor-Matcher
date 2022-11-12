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
public class PostgraduateExperience {
    
    private Integer postgradExperienceID;
    private Person person;
    private Integer personID;
    private String postgradCourse;
    private String postgradMajor;
    private String postgradInstitution;
    private Integer postgradYear;

    
    public PostgraduateExperience(){
        
    }
    public PostgraduateExperience(Integer postgradExperienceID, Integer personID, String postgradCourse, String postgradMajor, String postgradInstitution, Integer postgradYear) {
        this.postgradExperienceID = postgradExperienceID;
        this.personID = personID;
        this.postgradCourse = postgradCourse;
        this.postgradMajor = postgradMajor;
        this.postgradInstitution = postgradInstitution;
        this.postgradYear = postgradYear;
    }
    
    public Integer getPostgradExperienceID() {
        return postgradExperienceID;
    }

    public void setPostgradExperienceID(Integer postgradExperienceID) {
        this.postgradExperienceID = postgradExperienceID;
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

    public String getPostgradCourse() {
        return postgradCourse;
    }

    public void setPostgradCourse(String postgradCourse) {
        this.postgradCourse = postgradCourse;
    }

    public String getPostgradMajor() {
        return postgradMajor;
    }

    public void setPostgradMajor(String postgradMajor) {
        this.postgradMajor = postgradMajor;
    }

    public String getPostgradInstitution() {
        return postgradInstitution;
    }

    public void setPostgradInstitution(String postgradInstitution) {
        this.postgradInstitution = postgradInstitution;
    }

    public Integer getPostgradYear() {
        return postgradYear;
    }

    public void setPostgradYear(Integer postgradYear) {
        this.postgradYear = postgradYear;
    }


    
    
}
