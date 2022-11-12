/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author luxpe
 */
public class PostgradCourses {
    private String course;
    private String [] majors;
    
    public PostgradCourses(){
        
    }
    public PostgradCourses(String course, String[] majors) {
        this.course = course;
        this.majors = majors;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String[] getMajors() {
        return majors;
    }

    public void setMajors(String[] majors) {
        this.majors = majors;
    }
    
    
    
    
}
