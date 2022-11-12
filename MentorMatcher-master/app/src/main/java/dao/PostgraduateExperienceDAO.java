/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.PostgraduateExperience;
import java.util.Collection;

/**
 *
 * @author thirzasmith
 */
public interface PostgraduateExperienceDAO {

    /**
     * Get a list of all Postgraduate Experiences.
     *
     * @return list of postgraduateExperiences
     */
    Collection<PostgraduateExperience> getPostgraduateExperiences();

    /**
     * Get details of a specific postgraduateExperience
     *
     * @param id of the postgraduateExperience to retrieve
     * @return postgraduateExperience that is specified
     */
    Collection<PostgraduateExperience> getPostgraduateExperienceByID(int id);

    /**
     * Update details of a postgraduateExperience
     *
     * @param id of postgraduateExperience to update
     * @param updatedPostgraduateExperience updated details for
     * postgraduateExperience
     */
    void updatePostgraduateExperience(int id, PostgraduateExperience updatedPostgraduateExperience);

    /**
     * Delete an postgraduateExperience
     *
     * @param postgraduateExperience to remove
     */
    void deletePostgraduateExperience(PostgraduateExperience postgraduateExperience);

    /**
     * Add an postgraduateExperience
     *
     * @param postgraduateExperience to add
     */
    void savePostgraduateExperience(PostgraduateExperience postgraduateExperience);

}
