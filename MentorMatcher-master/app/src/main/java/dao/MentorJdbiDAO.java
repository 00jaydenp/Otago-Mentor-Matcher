/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author jessierongen
 */
import domain.Mentor;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface MentorJdbiDAO extends MentorDAO {

    /**
     * Adds a Mentor
     *
     * @param mentor to add
     */
    @Override
    @SqlUpdate("INSERT INTO MENTOR(MENTOR_ID, PERSON_ID, PREFERRED_COMMUNICATION, MENTORING_PREFERENCE, LINKED_IN, CAPACITY) values (:mentorID, :person.personID, :preferredCommunication, :mentoringPreference, :linkedIn, :capacity)")
    public void saveMentor(@BindBean Mentor mentor);

    /**
     * Delete a mentor
     *
     * @param mentor to delete
     */
    @Override
    @SqlUpdate("DELETE FROM MENTOR WHERE MENTOR_ID = :mentorID")
    public void deleteMentor(@Bind("mentorID") int id);

    /**
     * Updates an existing mentor
     *
     * @param id of mentor to update
     * @param updatedMentor updated details of mentor
     */
    @Override
    @SqlUpdate("UPDATE MENTOR SET PREFERRED_COMMUNICATION= :preferredCommunication, MENTORING_PREFERENCE= :mentoringPreference, LINKED_IN= :linkedIn, CAPACITY= :capacity WHERE MENTOR_ID = :mentorID")
    public void updateMentor(@Bind("mentorID") int id, @BindBean Mentor updatedMentor);

    /**
     * Gets a specific mentor.
     *
     * @param id of the mentor to return
     * @return Mentor who's id was specified.
     */
    @Override
    @SqlQuery("SELECT * FROM MENTOR WHERE MENTOR_ID = :mentorID")
    @RegisterBeanMapper(Mentor.class)
    public Mentor getMentorByID(@Bind("mentorID") int id);

    /**
     * Gets a list of all mentors.
     *
     * @return Collection of mentors
     */
    @Override
    @SqlQuery("SELECT * FROM MENTOR ORDER BY PERSON_ID")
    @RegisterBeanMapper(Mentor.class)
    public Collection<Mentor> getMentors();

    @Override
    @SqlQuery("SELECT * FROM MENTOR WHERE PERSON_ID = :personID")
    @RegisterBeanMapper(Mentor.class)
    public Mentor getByPersonID(@Bind("personID") int id);

}
