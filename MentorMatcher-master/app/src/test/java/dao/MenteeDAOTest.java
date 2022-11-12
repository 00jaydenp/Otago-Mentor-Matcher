/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Mentee;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasItems;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

/**
 *
 * @author Jayden
 */
public class MenteeDAOTest {

    MenteeDAO dao;
    Mentee m1;
    Mentee m2;
    Mentee m3;


    @BeforeEach
    public void setUp() {
        dao = new MenteeCollectionsDAO();

        // Create Mentee objects
        m1 = new Mentee(1, 1, 2022, "M1_MOTIVATION", new String[]{"interest_1", "interest_2"} , false);
        m2 = new Mentee(2, 2, 2023, "M2_MOTIVATION", new String[]{"interest_1"}, true);
        m3 = new Mentee(3, 3, 2021, "M3_MOTIVATION", new String[]{"interest_1", "interest_2"}, true);

        //Save Mentees, leave out m3 for testing purposes
        dao.saveMentee(m1);
        dao.saveMentee(m2);
    }

    @AfterEach
    public void tearDown() {
        dao.deleteMentee(m1);
        dao.deleteMentee(m2);
        dao.deleteMentee(m3);
    }

    @Test
    public void testGetMentees() {
        // Make sure it returns the two students on the database
        assertThat(dao.getMentees(), hasItems(m1, m2));
        //Make sure it only has those two
        assertThat(dao.getMentees(), hasSize(2));
        //Make sure it doesnt have 3
        assertThat(dao.getMentees(), not(hasSize(3)));
    }

    @Test
    public void testGetMenteeByID() {
        //search for m1
        Mentee result = dao.getMenteeByID(m1.getMenteeID());

        //Check that result is m1
        assertThat(result, is(m1));
        assertThat(result, Matchers.samePropertyValuesAs(m1));
    }

    @Test
    public void testUpdateMentee() {

        //Create mentee with updated details
        Mentee update = new Mentee(m1.getMenteeID(), m1.getPersonID(), 1998, "NEW MOTIVATION", m1.getInterests(), false);

        //Make sure that they are different
        assertThat(m1, not(Matchers.samePropertyValuesAs(update)));
        //Update
        dao.updateMentee(m1.getMenteeID(), update);
        //Make sure that m1 now equals updated mentee
        assertThat(dao.getMenteeByID(m1.getMenteeID()), Matchers.samePropertyValuesAs(update));
    }

    @Test
    public void testDeleteMentee() {
        // Make sure it returns the two students on the database
        assertThat(dao.getMentees(), hasItems(m1, m2));
        //Make sure it only has those two
        assertThat(dao.getMentees(), hasSize(2));

        //Delete m1
        dao.deleteMentee(m1);

        // Make sure it doesnt have m1
        assertThat(dao.getMentees(), not(hasItems(m1)));
        //Make sure it only has 1 mentee
        assertThat(dao.getMentees(), hasSize(1));
    }

    @Test
    public void testSaveMentee() {
        //Check that there are only two mentees on system
        assertThat(dao.getMentees(), hasSize(2));

        //Make sure m3 isn't in the system
        assertThat(dao.getMentees(), not(hasItems(m3)));

        //Add m3 to system
        dao.saveMentee(m3);

        //Check that there are  3 mentees on system
        assertThat(dao.getMentees(), hasSize(3));

        //Make sure m3 is in the system
        assertThat(dao.getMentees(), hasItems(m3));

    }

}
