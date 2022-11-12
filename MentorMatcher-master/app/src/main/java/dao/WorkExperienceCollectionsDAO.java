/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import domain.UndergraduateExperience;
import domain.WorkExperience;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public class WorkExperienceCollectionsDAO implements WorkExperienceDAO {

    private static final Map<Integer, WorkExperience> workExperiences = new HashMap<>();
    private static final Multimap<Integer, WorkExperience> workMentorIDMap = ArrayListMultimap.create();

    @Override
    public void saveWorkExperience(WorkExperience workExperience) {
        workExperiences.put(workExperience.getWorkID(), workExperience);
        workMentorIDMap.put(workExperience.getMentorID(), workExperience);
    }

    @Override
    public void deleteWorkExperience(WorkExperience workExperience) {
        workExperiences.remove(workExperience.getWorkID());
    }

    @Override
    public void updateWorkExperience(int id, WorkExperience workExperience) {
        workExperiences.put(id, workExperience);
    }

    @Override
    public Collection<WorkExperience> getMentorExperienceByID(int id) {
        return workMentorIDMap.get(id);
    }

    @Override
    public Collection<WorkExperience> getWorkExperiences() {
        return workExperiences.values();
    }

    @Override
    public Collection<WorkExperience> searchByIndusrty(String query) {
        return workExperiences.values();
    }
}
