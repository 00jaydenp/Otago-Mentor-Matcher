/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Mentee;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author Jayden
 */
public interface MenteeJdbiDAO extends MenteeDAO {

    /**
     * Adds a Mentee
     *
     * @param mentee to add
     */
    @Override
    @SqlUpdate("INSERT INTO MENTEE(MENTEE_ID, PERSON_ID, STUDY_YEAR, MOTIVATION, RANDOM_MATCH) values (:menteeID, :person.personID, :studyYear, :motivation, :randomMatch)")
    public void saveMentee(@BindBean Mentee mentee);

    /**
     * Delete a mentee
     *
     * @param mentee to delete
     */
    @Override
    @SqlUpdate("DELETE FROM MENTEE WHERE MENTEE_ID = :menteeID")
    public void deleteMentee(@BindBean Mentee mentee);

    /**
     * Updates an existing mentee
     *
     * @param id of mentee to update
     * @param updatedMentee updated details of mentee
     */
    @Override
    @SqlUpdate("UPDATE MENTEE SET STUDY_YEAR = :studyYear, MOTIVATION= :motivation, INTERESTS = :interests, RANDOM_MATCH = :randomMatch WHERE MENTEE_ID = :menteeID")
    public void updateMentee(@Bind("menteeID") int id, @BindBean Mentee updatedMentee);

    /**
     * Gets a specific mentee.
     *
     * @param id of the mentee to return
     * @return Mentee who's id was specified.
     */
    @Override
    @SqlQuery("SELECT * FROM MENTEE WHERE MENTEE_ID = :menteeID")
    @RegisterBeanMapper(Mentee.class)
    public Mentee getMenteeByID(@Bind("menteeID") int id);

    /**
     * Gets a list of all mentees.
     *
     * @return Collection of mentees
     */
    @Override
    @SqlQuery("SELECT * FROM MENTEE ORDER BY MENTEE_ID")
    @RegisterBeanMapper(Mentee.class)
    public Collection<Mentee> getMentees();

    @Override
    @SqlQuery("SELECT * FROM MENTEE WHERE PERSON_ID = :personID")
    @RegisterBeanMapper(Mentee.class)
    public Mentee getByPersonID(@Bind("personID") int id);

}
