/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Match;
import java.util.Date;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Jayden
 */
public class MatchDAOTest {
    MatchDAO dao;
    
    Match m1;
    Match m2;
    Match m3;
   
     
    
    @BeforeEach
    public void setUp() {
        dao = new MatchCollectionsDAO();
        
        
      
        m1 = new Match(1, 1, 1,  true, true, new Date("23/09/2022"));
        m2 = new Match(2, 1, 2,  false, false, new Date("23/09/2022"));
        m3 = new Match(3, 2, 3,  true, false, new Date("23/09/2022"));
        
        dao.saveMatch(m1);
        dao.saveMatch(m2);
        
    }
    
    @AfterEach
    public void tearDown() {
        dao.deleteMatch(m1);
        dao.deleteMatch(m2);
        dao.deleteMatch(m3);
    }

    @Test
    public void testGetMatches() {
        // Make sure it returns the two students on the database
        assertThat(dao.getMatches(), hasItems(m1, m2));
        //Make sure it only has those two
        assertThat(dao.getMatches(), hasSize(2));
        //Make sure it doesnt have 3
        assertThat(dao.getMatches(), not(hasSize(3)));
    }

    @Test
    public void testSaveMatch() {
         //Check that there are only two mentees on system
        assertThat(dao.getMatches(), hasSize(2));

        //Make sure m3 isn't in the system
        assertThat(dao.getMatches(), not(hasItems(m3)));

        //Add m3 to system
        dao.saveMatch(m3);

        //Check that there are  3 mentees on system
        assertThat(dao.getMatches(), hasSize(3));

        //Make sure m3 is in the system
        assertThat(dao.getMatches(), hasItems(m3));
    }

    @Test
    public void testGetMatch() {
         //search for m1
        Match result = dao.getMatch(m1.getMatchID());

        //Check that result is m1
        assertThat(result, is(m1));
        assertThat(result, Matchers.samePropertyValuesAs(m1));
    }

    @Test
    public void testUpdateMatch() {
        //Create mentee with updated details
        Match update = new Match(m1.getMatchID(), m1.getMentorID(), m1.getMenteeID(), false, true, new Date("23/09/2022"));

        //Make sure that they are different
        assertThat(m1, not(Matchers.samePropertyValuesAs(update)));
        //Update
        dao.updateMatch(m1.getMatchID(), update);
        //Make sure that m1 now equals updated mentee
        assertThat(dao.getMatch(m1.getMatchID()), Matchers.samePropertyValuesAs(update));
    }

    @Test
    public void testDeleteMatch() {
         // Make sure it returns the two students on the database
        assertThat(dao.getMatches(), hasItems(m1, m2));
        //Make sure it only has those two
        assertThat(dao.getMatches(), hasSize(2));

        //Delete m1
        dao.deleteMatch(m1);

        // Make sure it doesnt have m1
        assertThat(dao.getMatches(), not(hasItems(m1)));
        //Make sure it only has 1 mentee
        assertThat(dao.getMatches(), hasSize(1));
    }

}
