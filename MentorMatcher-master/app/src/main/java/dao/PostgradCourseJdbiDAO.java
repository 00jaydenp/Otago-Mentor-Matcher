/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.PostgradCourses;
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
public interface PostgradCourseJdbiDAO extends PostgradCourseDAO {

    @Override
    @SqlUpdate("UPDATE POSTGRAD_COURSES SET MAJORS = :majors WHERE COURSE = :course")
    public void updatePostgradCourse(@Bind("course") String course, @BindBean PostgradCourses pg_course);

    @Override
    @SqlQuery("SELECT * FROM POSTGRAD_COURSES WHERE COURSE = :course")
    @RegisterBeanMapper(PostgradCourses.class)
    public PostgradCourses getPostgradCourse(@Bind("course") String course);

    @Override
    @SqlQuery("SELECT * FROM POSTGRAD_COURSES")
    @RegisterBeanMapper(PostgradCourses.class)
    public Collection<PostgradCourses> getPostgradCourses();

    @Override
    @SqlUpdate("INSERT INTO POSTGRAD_COURSES(pg_course, pg_majors) VALUES (:course, :majors)")
    public void savePostgradCourse(@BindBean PostgradCourses course);

    @Override
    @SqlUpdate("DELETE FROM POSTGRAD_COURSES WHERE COURSE = :course")
    public void deletePostgradCourse(@BindBean PostgradCourses course);
}
