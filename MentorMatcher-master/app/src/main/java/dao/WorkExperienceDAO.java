/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.WorkExperience;
import java.util.Collection;

/**
 *
 * @author David
 */
public interface WorkExperienceDAO {

    void saveWorkExperience(WorkExperience workExperience);

    void deleteWorkExperience(WorkExperience workExperience);

    void updateWorkExperience(int id, WorkExperience workExperience);

    Collection<WorkExperience> getMentorExperienceByID(int id);

    Collection<WorkExperience> getWorkExperiences();

    Collection<WorkExperience> searchByIndusrty(String query);
}
