/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.PostgradCourses;
import java.util.Collection;

/**
 *
 * @author Jayden
 */
public interface PostgradCourseDAO {

    void deletePostgradCourse(PostgradCourses course);

    void savePostgradCourse(PostgradCourses course);

    PostgradCourses getPostgradCourse(String course);

    Collection<PostgradCourses> getPostgradCourses();

    void updatePostgradCourse(String course, PostgradCourses pg_course);
}
