/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Person;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.Matchers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *
 * @author David
 */
public class PersonDAOTest {
    
    PersonDAO dao;
    
    Person p1;
    Person p2; 
    Person p3;
    
    
    @BeforeEach
    public void setUp() {
        dao = new PersonCollectionsDAO();
        
        p1 = new Person(1, "1@example.com", "Password", "p", "1", "1", "1", new String[]{"1", "2"}, "1", "1", true, "1", "1", "1", new String[]{"skill_1", "skill_2"});
        p2 = new Person(2, "2@example.com", "Password", "p", "2", "2", "2", new String[]{"1"}, "2", "2", true, "2", "2", "2", new String[]{"skill_1"});
        p3 = new Person(3, "3@example.com", "Password", "p", "3", "3", "3", new String[]{"1", "2", "3"}, "3", "3", true, "3", "3", "3", new String[]{"skill_1", "skill_2"});
            
        dao.savePerson(p1);
        dao.savePerson(p2);
    }
    
    @AfterEach
    public void tearDown() {
        
        dao.deletePerson(p1.getPersonID());
        dao.deletePerson(p2.getPersonID());
        dao.deletePerson(p3.getPersonID());
        
    }

    /**
     * Test of deletePerson method, of class PersonDAO.
     */
    @Test
    public void testDeletePerson() {
        // Check that collection has the 2 saved people
        assertThat(dao.getPersons(), hasItems(p1, p2));
        assertThat(dao.getPersons(), hasSize(2));
        
        // Delete p1 by their email
        dao.deletePerson(p1.getPersonID());
        
        // Check the collection does not have p1 and only size 1
        assertThat(dao.getPersons(), not(hasItems(p1)));
        assertThat(dao.getPersons(), hasSize(1));
        
    }

    /**
     * Test of getPerson method, of class PersonDAO.
     */
    @Test
    public void testGetPerson() {
        // Check that the returned person is p1
        assertThat(dao.getPerson(p1.getPersonID()), is(p1));
        assertThat(dao.getPerson(p1.getPersonID()), Matchers.samePropertyValuesAs(p1));
    }

    /**
     * Test of getPersons method, of class PersonDAO.
     */
    @Test
    public void testGetPersons() {
        // Check that collection has the 2 saved people; p1 and p2
        assertThat(dao.getPersons(), hasItems(p1, p2));
        assertThat(dao.getPersons(), hasSize(2));
    }

    /**
     * Test of savePerson method, of class PersonDAO.
     */
    @Test
    public void testSavePerson() {
        //Make sure p3 is not in colection
        assertThat(dao.getPersons(), not(hasItems(p3)));

        //Add p3 to collection
        dao.savePerson(p3);

        //Check that collection has 3 persons
        assertThat(dao.getPersons(), hasSize(3));
        assertThat(dao.getPersons(), hasItems(p1, p2, p3));
    }

    /**
     * Test of updatePerson method, of class PersonDAO.
     */
    @Test
    public void testUpdatePerson() {
        Person updatedP1 = new Person(p1.getPersonID(), p1.getEmail(), "12345678", p1.getFirstName(), p1.getLastName(), p1.getPhone(), p1.getMobile(), p1.getInterests(), p1.getBio(), p1.getCity(), false, p1.getFindOut(), p1.getRole(), p1.getNotes(), p1.getSkills());

        //Make sure that they are different
        assertThat(p1, not(Matchers.samePropertyValuesAs(updatedP1)));
        //Update
        dao.updatePerson(p1.getPersonID(), updatedP1);
        //Make sure that m1 now equals updated mentee
        assertThat(dao.getPerson(p1.getPersonID()), Matchers.samePropertyValuesAs(updatedP1));
    }
    
}
