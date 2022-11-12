/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Jayden
 */
public class Feedback {
    int feedbackID;
    int personID;
    float feedbackRating;
    String feedbackComment;
    
    public Feedback(){
        
    }

    public Feedback(int feedbackID, int personID, float feedbackRating, String feedbackComment) {
        this.feedbackID = feedbackID;
        this.personID = personID;
        this.feedbackRating = feedbackRating;
        this.feedbackComment = feedbackComment;
    }
    
    

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public float getFeedbackRating() {
        return feedbackRating;
    }

    public void setFeedbackRating(float feedbackRating) {
        this.feedbackRating = feedbackRating;
    }

    public String getFeedbackComment() {
        return feedbackComment;
    }

    public void setFeedbackComment(String feedbackComment) {
        this.feedbackComment = feedbackComment;
    }

 
    
    
}
