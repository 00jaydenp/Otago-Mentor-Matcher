/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Mentor;
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
 * @author jessierongen
 */
public class MentorDAOTest {
    
    MentorDAO dao;
    Mentor m1;
    Mentor m2;
    Mentor m3;
    
    @BeforeEach
    public void setUp(){
        dao = new MentorCollectionsDAO();
        
        //Create test Mentor objects
        m1 = new Mentor(1, "email1@email.com", "M1_CAREER_HISTORY", "M1_PREFERRED_COMMUNICATION",  new String[]{"preference1", "preference2"}, "M1_LINKEDIN", 1, "M1_TITLE", "M1_ORG");
        m2 = new Mentor(2, "email2@email.com", "M2_CAREER_HISTORY", "M2_PREFERRED_COMMUNICATION", new String[]{"preference1"}, "M2_LINKEDIN", 2,  "M1_TITLE", "M1_ORG");
        m3 = new Mentor(3, "email3@email.com", "M3_CAREER_HISTORY", "M3_PREFERRED_COMMUNICATION", new String[]{"preference1", "preference2"}, "M3_LINKEDIN", 3,  "M1_TITLE", "M1_ORG");
        
        //Save Mentors 1 & 2, leave out 3 for test purposes
        dao.saveMentor(m1);
        dao.saveMentor(m2);
    }
    
    @AfterEach
    public void tearDown(){
        dao.deleteMentor(m1.getMentorID());
        dao.deleteMentor(m2.getMentorID());
        dao.deleteMentor(m3.getMentorID());
    }
    
    @Test
    public void testGetMentors(){
        //Ensure saved mentors are retrieved from database
        assertThat(dao.getMentors(), hasItems(m1, m2));
        //Ensure two mentors are retrieved
        assertThat(dao.getMentors(), hasSize(2));
        //Ensure no more than two mentors are retrieved
        assertThat(dao.getMentors(), not(hasSize(3)));
    }
            
    @Test
    public void testUpdateMentor() {

        //Change a mentor's details
        Mentor update = new Mentor(m1.getMentorID(), m1.getEmail(), m1.getCareerHistory(), "NEW PREFERRED COMMUNICATION", new String[]{"preference1", "preference2", "preference3"}, "NEW LINKEDIN", 1,  "M1_TITLE", "M1_ORG");

        //Ensure they are different
        assertThat(m1, not(Matchers.samePropertyValuesAs(update)));
        //Update mentor details
        dao.updateMentor(m1.getMentorID(), update);
        //Make sure that m1 is updated mentor
        assertThat(dao.getMentorByID(m1.getMentorID()), Matchers.samePropertyValuesAs(update));
    }

    @Test
    public void testDeleteMentor() {
        //Ensure the two saved mentors are saved
        assertThat(dao.getMentors(), hasItems(m1, m2));
        //Ensure only those two are saved
        assertThat(dao.getMentors(), hasSize(2));

        //Delete m1
        dao.deleteMentor(m1.getMentorID());

        //Ensure m1 is not contained in the database anymore
        assertThat(dao.getMentors(), not(hasItems(m1)));
        //Ensure only one mentor remains
        assertThat(dao.getMentors(), hasSize(1));
    }

    @Test
    public void testSaveMentor() {
        //Check that there are only two mentors
        assertThat(dao.getMentors(), hasSize(2));

        //Ensure m3 isn't in the database
        assertThat(dao.getMentors(), not(hasItems(m3)));

        //Add m3 to database
        dao.saveMentor(m3);

        //Check that there are now 3 mentors
        assertThat(dao.getMentors(), hasSize(3));

        //Ensure m3 is in the database
        assertThat(dao.getMentors(), hasItems(m3));
    }

            
}
