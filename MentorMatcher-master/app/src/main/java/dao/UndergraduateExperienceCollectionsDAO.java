/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import domain.UndergraduateExperience;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thirzasmith
 */
public class UndergraduateExperienceCollectionsDAO implements UndergraduateExperienceDAO {

    private static final Map<Integer, UndergraduateExperience> undergraduateExperiences = new HashMap<>();
    private static final Multimap<Integer, UndergraduateExperience> undergradPersonIDMap = ArrayListMultimap.create();

    /**
     * Adds an UndergraduateExperience
     *
     * @param undergraduateExperience to add
     */
    @Override
    public void saveUndergraduateExperience(UndergraduateExperience undergraduateExperience) {
        undergraduateExperiences.put(undergraduateExperience.getUndergradExperienceID(), undergraduateExperience);
        undergradPersonIDMap.put(undergraduateExperience.getPersonID(), undergraduateExperience);
    }

    /**
     * Delete an undergraduateExperience
     *
     * @param undergraduateExperience to delete
     */
    @Override
    public void deleteUndergraduateExperience(UndergraduateExperience undergraduateExperience) {
        undergraduateExperiences.remove(undergraduateExperience.getUndergradExperienceID());
    }

    /**
     * Updates an existing undergraduateExperience
     *
     * @param id of undergraduateExperience to update
     * @param updatedUndergraduateExperience updated details of
     * undergraduateExperience
     */
    @Override
    public void updateUndergraduateExperience(int id, UndergraduateExperience updatedUndergraduateExperience) {
        undergraduateExperiences.put(id, updatedUndergraduateExperience);
    }

    /**
     * Gets a specific undergraduateExperience
     *
     * @param id of the undergraduateExperience to return
     * @return UndergraduateExperience from the specified id
     */
    @Override
    public Collection<UndergraduateExperience> getUndergraduateExperienceByID(int id) {
        return undergradPersonIDMap.get(id);
    }

    /**
     * Gets a list of all undergraduateExperience
     *
     * @return Collection of undergraduateExperiences
     */
    @Override
    public Collection<UndergraduateExperience> getUndergraduateExperiences() {
        return undergraduateExperiences.values();
    }

}
