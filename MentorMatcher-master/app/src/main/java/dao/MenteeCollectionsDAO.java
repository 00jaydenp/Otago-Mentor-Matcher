/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Mentee;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jayden
 */
public class MenteeCollectionsDAO implements MenteeDAO {

    private static final Map<Integer, Mentee> mentees = new HashMap<>();

    /**
     * Adds a Mentee
     *
     * @param mentee to add
     */
    @Override
    public void saveMentee(Mentee mentee) {
        mentees.put(mentee.getMenteeID(), mentee);
    }

    @Override
    public Mentee getByPersonID(int id) {
        Collection<Mentee> values = mentees.values();
        for (Mentee m : values) {
            if (m.getPersonID() == id) {
                return m;
            }
        }
        return null;
    }

    /**
     * Delete a mentee
     *
     * @param mentee to delete
     */
    @Override
    public void deleteMentee(Mentee mentee) {
        mentees.remove(mentee.getMenteeID());
    }

    /**
     * Updates an existing mentee
     *
     * @param id of mentee to update
     * @param updatedMentee updated details of mentee
     */
    @Override
    public void updateMentee(int id, Mentee updatedMentee) {
        mentees.put(id, updatedMentee);
    }

    /**
     * Gets a specific mentee.
     *
     * @param id of the mentee to return
     * @return Mentee who's id was specified.
     */
    @Override
    public Mentee getMenteeByID(int id) {
        return mentees.get(id);
    }

    /**
     * Gets a list of all mentees.
     *
     * @return Collection of mentees
     */
    @Override
    public Collection<Mentee> getMentees() {
        return mentees.values();
    }

}
