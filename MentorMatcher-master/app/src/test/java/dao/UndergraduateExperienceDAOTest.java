/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.UndergraduateExperience;
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

public class UndergraduateExperienceDAOTest {
    
    UndergraduateExperienceDAO dao;
    UndergraduateExperience ue1;
    UndergraduateExperience ue2;
    UndergraduateExperience ue3;

    
    //Set up before each test
    @BeforeEach
    public void setUp(){
        dao = new UndergraduateExperienceCollectionsDAO();
        
        //Create the UndergraduateExperience test objects
        ue1 = new UndergraduateExperience(1, 1, "Accounting", "Bachelor of Commerce", "University of Otago", 2015);
        ue2 = new UndergraduateExperience(2, 1, "Computer Science", "Bachelor of Science", "University of Auckland", 2018);
        ue3 = new UndergraduateExperience(42, 2, "Chinese", "Bachelor of Arts", "University of Waikato", 2009);
        
        //Save undergrad experiences 1 & 2, leave out 3 for test purposes
        dao.saveUndergraduateExperience(ue1);
        dao.saveUndergraduateExperience(ue2);
    }
        
    
    //Reset before the next test
    @AfterEach
    public void tearDown(){
        dao.deleteUndergraduateExperience(ue1);
        dao.deleteUndergraduateExperience(ue2);
        dao.deleteUndergraduateExperience(ue3);
    }
    
    
    //Test to ensure undergraduate experiences are retrieved from the database correctly
    @Test
    public void testGetUndergraduateExperiences(){
        //Ensure saved undergraduate experiences are retrieved from database
        assertThat(dao.getUndergraduateExperiences(), hasItems(ue1, ue2));
        //Ensure two undergraduate experiences are retrieved
        assertThat(dao.getUndergraduateExperiences(), hasSize(2));
        //Ensure no more than two undergraduate experiences are retrieved
        assertThat(dao.getUndergraduateExperiences(), not(hasSize(3)));
    }
    
    
    //Test to update an undergraduate experience's details correctly
    @Test
    public void testUpdateUndergraduateExperience() {
        //Change an experience's details
        UndergraduateExperience updatedExperience = new UndergraduateExperience(ue1.getUndergradExperienceID(), ue1.getPersonID(), ue1.getUndergradMajor(), ue1.getUndergradCourse(), ue1.getUndergradInstitution(), 1999);

        //Ensure ue1 and updatedExperience are different
        assertThat(ue1, not(Matchers.samePropertyValuesAs(updatedExperience)));
        
        //Update experience details
        dao.updateUndergraduateExperience(ue1.getUndergradExperienceID(), updatedExperience);
        
        //Make sure that ue1 is updated experience
        assertThat(dao.getUndergraduateExperienceByID(ue1.getUndergradExperienceID()), Matchers.samePropertyValuesAs(updatedExperience));
    }
    
    
    //Test to delete an undergraduate experience from the database
    @Test
    public void testDeleteUndergraduateExperience() {
        //Ensure the two saved experiences are saved
        assertThat(dao.getUndergraduateExperiences(), hasItems(ue1, ue2));
        //Ensure only those two are saved
        assertThat(dao.getUndergraduateExperiences(), hasSize(2));

        //Delete ue1
        dao.deleteUndergraduateExperience(ue1);

        //Ensure ue1 is not stored in the database anymore
        assertThat(dao.getUndergraduateExperiences(), not(hasItems(ue1)));
       //Ensure only one experience remains
        assertThat(dao.getUndergraduateExperiences(), hasSize(1));
    }
    
    
    //Test to save a new experience to the database
    @Test
    public void testSaveUndergraduateExperience() {
        //Check that there are only two experiences to start with
        assertThat(dao.getUndergraduateExperiences(), hasSize(2));

        //Ensure ue3 isn't in the database
        assertThat(dao.getUndergraduateExperiences(), not(hasItems(ue3)));

        //Save ue3 to database
        dao.saveUndergraduateExperience(ue3);

        //Check that there are now 3 mentors
        assertThat(dao.getUndergraduateExperiences(), hasSize(3));

        //Ensure m3 is in the database
        assertThat(dao.getUndergraduateExperiences(), hasItems(ue3));
    }
    
}
