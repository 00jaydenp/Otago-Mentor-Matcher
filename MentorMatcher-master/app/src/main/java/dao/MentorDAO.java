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
import org.jdbi.v3.core.mapper.JoinRow;

public interface MentorDAO {

    /**
     * Get a list of all Mentors.
     *
     * @return list of mentors
     */
    Collection<Mentor> getMentors();

    /**
     * Get details of a specific mentor
     *
     * @param id of the mentor to retrieve.
     * @return mentor that is specified
     */
    Mentor getMentorByID(int id);

    /**
     * Update details of a mentor
     *
     * @param id of mentor to update.
     * @param updatedMentor updated details for mentor.
     */
    void updateMentor(int id, Mentor updatedMentor);

    /**
     * Delete a mentor
     *
     * @param id to remove mentor by.
     */
    void deleteMentor(int id);

    /**
     * Add a mentor
     *
     * @param mentor to add
     */
    void saveMentor(Mentor mentor);

    /**
     * Get Mentor by Person ID
     *
     * @param id person ID
     * @return mentor
     */
    Mentor getByPersonID(int id);

}
