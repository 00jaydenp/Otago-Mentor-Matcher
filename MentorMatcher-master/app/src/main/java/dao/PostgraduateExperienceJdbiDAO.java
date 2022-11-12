/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.PostgraduateExperience;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author thirzasmith
 */
public interface PostgraduateExperienceJdbiDAO extends PostgraduateExperienceDAO {

    /**
     * Adds/inserts an Postgraduate Experience
     *
     * @param postgraduateExperience to add
     */
    @Override
    @SqlUpdate("INSERT INTO POSTGRADUATE_EXPERIENCE(POSTGRAD_EXPERIENCE_ID, PERSON_ID, POSTGRAD_MAJOR, POSTGRAD_COURSE, POSTGRAD_INSTITUTION, POSTGRAD_YEAR) values (:postgradExperienceID, :person.personID, :postgradMajor, :postgradCourse, :postgradInstitution, :postgradYear)")
    public void savePostgraduateExperience(@BindBean PostgraduateExperience postgraduateExperience);

    /**
     * Updates an existing undergraduateExperience
     *
     * @param id of postgraduateExperience to update
     * @param updatedPostgraduateExperience updated details of
     * postgraduateExperience
     */
    @Override
    @SqlUpdate("UPDATE POSTGRADUATE_EXPERIENCE SET POSTGRAD_MAJOR = :postgradMajor, POSTGRAD_COURSE = :postgradCourse, POSTGRAD_INSTITUTION = :postgradInstitution, POSTGRAD_YEAR = :postgradYear WHERE POSTGRADUATE_EXPERIENCE_ID = :postgradExperienceID")
    public void updatePostgraduateExperience(@Bind("postgradExperienceID") int id, @BindBean PostgraduateExperience updatedPostgraduateExperience);

    /**
     * Delete a postgraduateExperience
     *
     * @param postgraduateExperience to delete
     */
    @Override
    @SqlUpdate("DELETE FROM POSTGRADUATE_EXPERIENCE WHERE POSTGRAD_EXPERIENCE_ID = :postgradExperienceID")
    public void deletePostgraduateExperience(@BindBean PostgraduateExperience postgraduateExperience);

    /**
     * Gets a list of all postgraduate experiences
     *
     * @return Collection of postgraduateExperiences
     */
    @Override
    @SqlQuery("SELECT * FROM POSTGRADUATE_EXPERIENCE ORDER BY POSTGRAD_EXPERIENCE_ID")
    @RegisterBeanMapper(PostgraduateExperience.class)
    public Collection<PostgraduateExperience> getPostgraduateExperiences();

    @Override
    @SqlQuery("SELECT * FROM POSTGRADUATE_EXPERIENCE WHERE PERSON_ID = :person.personID")
    @RegisterBeanMapper(PostgraduateExperience.class)
    public Collection<PostgraduateExperience> getPostgraduateExperienceByID(@Bind("person.personID") int id);

}
