/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.UndergraduateExperience;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * @author thirzasmith
 */
public interface UndergraduateExperienceJdbiDAO extends UndergraduateExperienceDAO {

    /**
     * Adds/inserts an Undergraduate Experience
     *
     * @param undergraduateExperience to add
     */
    @Override
    @SqlUpdate("INSERT INTO UNDERGRADUATE_EXPERIENCE(PERSON_ID, UNDERGRAD_MAJOR, UNDERGRAD_COURSE, UNDERGRAD_INSTITUTION, UNDERGRAD_YEAR) values (:person.personID, :undergradMajor, :undergradCourse, :undergradInstitution, :undergradYear)")
    public void saveUndergraduateExperience(@BindBean UndergraduateExperience undergraduateExperience);

    /**
     * Updates an existing undergraduateExperience
     *
     * @param id of undergraduateExperience to update
     * @param updatedUndergraduateExperience updated details of
     * undergraduateExperience
     */
    @Override
    @SqlUpdate("UPDATE UNDERGRADUATE_EXPERIENCE SET UNDERGRAD_MAJOR = :undergradMajor, UNDERGRAD_COURSE = :undergradCourse, UNDERGRAD_INSTITUTION = :undergradInstitution, UNDERGRAD_YEAR = :undergradYear WHERE UNDERGRADUATE_EXPERIENCE_ID = :undergradExperienceID")
    public void updateUndergraduateExperience(@Bind("undergradExperienceID") int id, @BindBean UndergraduateExperience updatedUndergraduateExperience);

    /**
     * Delete an undergraduateExperience
     *
     * @param undergraduateExperience to delete
     */
    @Override
    @SqlUpdate("DELETE FROM UNDERGRADUATE_EXPERIENCE WHERE UNDERGRAD_EXPERIENCE_ID = :undergradExperienceID")
    public void deleteUndergraduateExperience(@BindBean UndergraduateExperience undergraduateExperience);

    /**
     * Gets a list of all undergraduate experiences
     *
     * @return Collection of undergraduateExperiences
     */
    @Override
    @SqlQuery("SELECT * FROM UNDERGRADUATE_EXPERIENCE ORDER BY UNDERGRAD_EXPERIENCE_ID")
    @RegisterBeanMapper(UndergraduateExperience.class)
    public Collection<UndergraduateExperience> getUndergraduateExperiences();

    @Override
    @SqlQuery("SELECT * FROM UNDERGRADUATE_EXPERIENCE WHERE PERSON_ID = :person.personID")
    @RegisterBeanMapper(UndergraduateExperience.class)
    public Collection<UndergraduateExperience> getUndergraduateExperienceByID(@Bind("person.personID") int id);

}
