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
import java.util.HashMap;
import java.util.Map;

public final class MentorCollectionsDAO implements MentorDAO {

    private static final Map<Integer, Mentor> mentors = new HashMap<>();

    /**
     * Adds a Mentor
     *
     * @param mentor to add
     */
    @Override
    public void saveMentor(Mentor mentor) {
        mentors.put(mentor.getMentorID(), mentor);
    }

    @Override
    public Mentor getByPersonID(int id) {
        Collection<Mentor> values = mentors.values();
        for (Mentor m : values) {
            if (m.getPersonID() == id) {
                return m;
            }
        }
        return null;
    }

    /**
     * Delete a mentor
     *
     * @param mentor to delete
     */
    @Override
    public void deleteMentor(int id) {
        mentors.remove(id);
    }

    /**
     * Updates an existing mentor
     *
     * @param id of mentor to update
     * @param updatedMentor updated details of mentor
     */
    @Override
    public void updateMentor(int id, Mentor updatedMentor) {
        mentors.put(id, updatedMentor);
    }

    /**
     * Gets a specific mentor.
     *
     * @param id of the mentor to return
     * @return Mentor who's id was specified.
     */
    @Override
    public Mentor getMentorByID(int id) {
        return mentors.get(id);
    }

    /**
     * Gets a list of all mentors.
     *
     * @return Collection of mentors
     */
    @Override
    public Collection<Mentor> getMentors() {
        return mentors.values();
    }
}
