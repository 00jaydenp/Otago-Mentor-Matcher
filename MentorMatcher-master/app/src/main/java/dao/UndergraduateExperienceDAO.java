/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.UndergraduateExperience;
import java.util.Collection;

/**
 *
 * @author thirzasmith
 */
public interface UndergraduateExperienceDAO {

    /**
     * Get a list of all Undergraduate Experiences.
     *
     * @return list of undergraduateExperiences
     */
    Collection<UndergraduateExperience> getUndergraduateExperiences();

    /**
     * Get details of a specific undergraduateExperience
     *
     * @param id of the undergraduateExperience to retrieve
     * @return undergraduateExperience that is specified
     */
    Collection<UndergraduateExperience> getUndergraduateExperienceByID(int id);

    /**
     * Update details of an undergraduateExperience
     *
     * @param id of undergraduateExperience to update
     * @param updatedUndergraduateExperience updated details for
     * undergraduateExperience
     */
    void updateUndergraduateExperience(int id, UndergraduateExperience updatedUndergraduateExperience);

    /**
     * Delete an undergraduateExperience
     *
     * @param undergraduateExperience to remove
     */
    void deleteUndergraduateExperience(UndergraduateExperience undergraduateExperience);

    /**
     * Add an undergraduateExperience
     *
     * @param undergraduateExperience to add
     */
    void saveUndergraduateExperience(UndergraduateExperience undergraduateExperience);

}
