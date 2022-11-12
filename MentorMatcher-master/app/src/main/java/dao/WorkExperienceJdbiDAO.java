/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.WorkExperience;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author David
 */
public interface WorkExperienceJdbiDAO extends WorkExperienceDAO {

    @Override
    @SqlUpdate("INSERT INTO WORK_EXPERIENCE(MENTOR_ID, TITLE, ORGANISATION, INDUSTRY, START_DATE, END_DATE) values (:mentor.mentorID, :title, :organisation, :industry, :startDate, :endDate)")
    void saveWorkExperience(@BindBean WorkExperience workExperience);

    @Override
    @SqlUpdate("DELETE FROM WORK_EXPERIENCE WHERE WORK_ID = :workID")
    void deleteWorkExperience(@BindBean WorkExperience workExperience);

    @Override
    @SqlUpdate("UPDATE WORK_EXPERIENCE SET TITLE = :title, ORGANIZATION = :organization, INDUSTRY = :industry, START_DATE = :startDate, END_DATE = :endDate WHERE WORK_ID = :workID")
    void updateWorkExperience(@Bind("workID") int id, @BindBean WorkExperience workExperience);

    @Override
    @SqlQuery("SELECT * FROM WORK_EXPERIENCE WHERE MENTOR_ID = :mentor.mentorID ORDER BY END_DATE DESC")
    @RegisterBeanMapper(WorkExperience.class)
    Collection<WorkExperience> getMentorExperienceByID(@Bind("mentor.mentorID") int id);

    @Override
    @SqlQuery("SELECT * FROM WORK_EXPERIENCE ORDER BY WORK_ID")
    @RegisterBeanMapper(WorkExperience.class)
    Collection<WorkExperience> getWorkExperiences();

    @Override
    @SqlQuery("SELECT * FROM WORK_EXPERIENCE.INDUSTY LIKE %:query%")
    @RegisterBeanMapper(WorkExperience.class)
    Collection<WorkExperience> searchByIndusrty(@Bind("query") String query);

}
