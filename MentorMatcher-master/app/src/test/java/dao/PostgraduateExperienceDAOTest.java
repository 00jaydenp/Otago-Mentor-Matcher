/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.PostgraduateExperience;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author thirzasmith
 */

public class PostgraduateExperienceDAOTest {
    
    PostgraduateExperienceDAO dao;
    PostgraduateExperience pe1;
    PostgraduateExperience pe2;
    PostgraduateExperience pe3;

    
    //Set up before each test
    @BeforeEach
    public void setUp(){
        dao = new PostgraduateExperienceCollectionsDAO();
        
        //Create the PostgraduateExperience test objects
        pe1 = new PostgraduateExperience(1, 1, "Law", "Bachelor of Arts", "University of Otago", 2002);
        pe2 = new PostgraduateExperience(2, 1, "Microbiology", "Bachelor of Science", "Melbourne University", 1988);
        pe3 = new PostgraduateExperience(13, 2, "Fine Arts", "Bachelor of Arts", "Auckland University of Technology", 2021);
        
        //Save postgrad experiences 1 & 2, leave out 3 for test purposes
        dao.savePostgraduateExperience(pe1);
        dao.savePostgraduateExperience(pe2);
    }
        
    
    //Reset before the next test
    @AfterEach
    public void tearDown(){
        dao.deletePostgraduateExperience(pe1);
        dao.deletePostgraduateExperience(pe2);
        dao.deletePostgraduateExperience(pe3);
    }
    
    
    //Test to ensure postgraduate experiences are retrieved from the database correctly
    @Test
    public void testGetPostgraduateExperiences(){
        //Ensure saved postgraduate experiences are retrieved from database
        assertThat(dao.getPostgraduateExperiences(), hasItems(pe1, pe2));
        //Ensure two postgraduate experiences are retrieved
        assertThat(dao.getPostgraduateExperiences(), hasSize(2));
        //Ensure no more than two postgraduate experiences are retrieved
        assertThat(dao.getPostgraduateExperiences(), not(hasSize(3)));
    }
    
    
    //Test to update an postgraduate experience's details correctly
    @Test
    public void testUpdatePostgraduateExperience() {
        //Change an experience's details
        PostgraduateExperience updatedExperience = new PostgraduateExperience(pe1.getPostgradExperienceID(), pe1.getPersonID(), pe1.getPostgradMajor(), pe1.getPostgradCourse(), pe1.getPostgradInstitution(), 1999);

        //Ensure ue1 and updatedExperience are different
        assertThat(pe1, not(Matchers.samePropertyValuesAs(updatedExperience)));
        
        //Update experience details
        dao.updatePostgraduateExperience(pe1.getPostgradExperienceID(), updatedExperience);
        
        //Make sure that ue1 is updated experience
        assertThat(dao.getPostgraduateExperienceByID(pe1.getPostgradExperienceID()), Matchers.samePropertyValuesAs(updatedExperience));
    }
    
    
    //Test to delete an postgraduate experience from the database
    @Test
    public void testDeletePostgraduateExperience() {
        //Ensure the two saved experiences are saved
        assertThat(dao.getPostgraduateExperiences(), hasItems(pe1, pe2));
        //Ensure only those two are saved
        assertThat(dao.getPostgraduateExperiences(), hasSize(2));

        //Delete ue1
        dao.deletePostgraduateExperience(pe1);

        //Ensure ue1 is not stored in the database anymore
        assertThat(dao.getPostgraduateExperiences(), not(hasItems(pe1)));
       //Ensure only one experience remains
        assertThat(dao.getPostgraduateExperiences(), hasSize(1));
    }
    
    
    //Test to save a new experience to the database
    @Test
    public void testSavePostgraduateExperience() {
        //Check that there are only two experiences to start with
        assertThat(dao.getPostgraduateExperiences(), hasSize(2));

        //Ensure ue3 isn't in the database
        assertThat(dao.getPostgraduateExperiences(), not(hasItems(pe3)));

        //Save ue3 to database
        dao.savePostgraduateExperience(pe3);

        //Check that there are now 3 mentors
        assertThat(dao.getPostgraduateExperiences(), hasSize(3));

        //Ensure m3 is in the database
        assertThat(dao.getPostgraduateExperiences(), hasItems(pe3));
    }
    
}
