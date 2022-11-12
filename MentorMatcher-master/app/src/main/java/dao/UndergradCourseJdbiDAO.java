/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.UndergradCourses;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author Jayden
 */
public interface UndergradCourseJdbiDAO extends UndergradCourseDAO {

    @Override
    @SqlUpdate("UPDATE UNDERGRAD_COURSES SET MAJORS = :majors WHERE COURSE = :course")
    public void updateUndergradCourse(@Bind("course") String course, @BindBean UndergradCourses ug_course);

    @Override
    @SqlQuery("SELECT * FROM UNDERGRAD_COURSES WHERE COURSE = :course")
    @RegisterBeanMapper(UndergradCourses.class)
    public UndergradCourses getUndergradCourse(@Bind("course") String course);

    @Override
    @SqlQuery("SELECT * FROM UNDERGRAD_COURSES")
    @RegisterBeanMapper(UndergradCourses.class)
    public Collection<UndergradCourses> getUndergradCourses();

    @Override
    @SqlUpdate("INSERT INTO UNDERGRAD_COURSES(ug_course, ug_majors) VALUES (:course, :majors)")
    public void saveUndergradCourse(@BindBean UndergradCourses course);

    @Override
    @SqlUpdate("DELETE FROM UNDERGRAD_COURSES WHERE COURSE = :course")
    public void deleteUndergradCourse(@BindBean UndergradCourses course);

}
