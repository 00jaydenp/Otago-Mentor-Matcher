/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Date;

/**
 *
 * @author mcaki150
 */
public class WorkExperience {
    private Integer workID;
    private Integer mentorID;
    private String title;
    private String organisation;
    private String industry;
    private String startDate;
    private String endDate;
    
    private Mentor mentor;

    public WorkExperience() {

    }

    public WorkExperience(int workID, int mentorID, String title, String organisation, String industry, String startDate, String endDate) {
        this.workID = workID;
        this.mentorID = mentorID;
        this.title = title;
        this.organisation = organisation;
        this.industry = industry;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }
    
    

    public Integer getWorkID() {
        return workID;
    }

    public void setWorkID(Integer workID) {
        this.workID = workID;
    }
    
    public Integer getMentorID() {
        return mentorID;
    }

    public void setMentorID(Integer mentorID) {
        this.mentorID = mentorID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }
    
    

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
