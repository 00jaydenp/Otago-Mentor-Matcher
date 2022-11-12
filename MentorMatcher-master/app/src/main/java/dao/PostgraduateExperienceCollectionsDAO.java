/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import domain.PostgraduateExperience;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thirzasmith
 */
public class PostgraduateExperienceCollectionsDAO implements PostgraduateExperienceDAO {

    private static final Map<Integer, PostgraduateExperience> postgraduateExperiences = new HashMap<>();
    private static final Multimap<Integer, PostgraduateExperience> postgradPersonIDMap = ArrayListMultimap.create();

    /**
     * Adds an PostgraduateExperience
     *
     * @param postgraduateExperience to add
     */
    @Override
    public void savePostgraduateExperience(PostgraduateExperience postgraduateExperience) {
        postgraduateExperiences.put(postgraduateExperience.getPostgradExperienceID(), postgraduateExperience);
    }

    /**
     * Delete an postgraduateExperience
     *
     * @param postgraduateExperience to delete
     */
    @Override
    public void deletePostgraduateExperience(PostgraduateExperience postgraduateExperience) {
        postgraduateExperiences.remove(postgraduateExperience.getPostgradExperienceID());
    }

    /**
     * Updates an existing postgraduateExperience
     *
     * @param id of postgraduateExperience to update
     * @param updatedPostgraduateExperience updated details of
     * postgraduateExperience
     */
    @Override
    public void updatePostgraduateExperience(int id, PostgraduateExperience updatedPostgraduateExperience) {
        postgraduateExperiences.put(id, updatedPostgraduateExperience);
    }

    /**
     * Gets a specific postgraduateExperience
     *
     * @param id of the postgraduateExperience to return
     * @return PostgraduateExperience from the specified id
     */
    @Override
    public Collection<PostgraduateExperience> getPostgraduateExperienceByID(int id) {
        return postgradPersonIDMap.get(id);
    }

    /**
     * Gets a list of all postgraduateExperience
     *
     * @return Collection of postgraduateExperiences
     */
    @Override
    public Collection<PostgraduateExperience> getPostgraduateExperiences() {
        return postgraduateExperiences.values();
    }
}
