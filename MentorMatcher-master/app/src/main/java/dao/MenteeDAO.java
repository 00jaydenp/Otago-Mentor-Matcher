/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Mentee;
import java.util.Collection;

/**
 *
 * @author Jayden
 */
public interface MenteeDAO {

    /**
     * Get a list of all Mentees.
     *
     * @return list of mentees
     */
    Collection<Mentee> getMentees();

    /**
     * Get details of a specific mentee
     *
     * @param id of the mentee to retrieve.
     * @return mentee that is specified
     */
    Mentee getMenteeByID(int id);

    /**
     * Update details of a mentee
     *
     * @param id of mentee to update.
     * @param updatedMentee updated details for mentee.
     */
    void updateMentee(int id, Mentee updatedMentee);

    /**
     * Delete a mentee
     *
     * @param mentee to remove.
     */
    void deleteMentee(Mentee mentee);

    /**
     * Add a mentee
     *
     * @param mentee to add
     */
    void saveMentee(Mentee mentee);

    /**
     * Get Mentee by personID
     *
     * @param id personID
     * @return Mentee
     */
    Mentee getByPersonID(int id);

}
