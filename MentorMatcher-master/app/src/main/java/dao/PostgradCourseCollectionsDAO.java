/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.PostgradCourses;
import domain.UndergradCourses;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jayden
 */
public class PostgradCourseCollectionsDAO implements PostgradCourseDAO {

    private static final Map<String, PostgradCourses> courses = new HashMap<>();

    @Override
    public void deletePostgradCourse(PostgradCourses course) {
        courses.remove(course);
    }

    @Override
    public PostgradCourses getPostgradCourse(String course) {
        return courses.get(course);
    }

    @Override
    public Collection<PostgradCourses> getPostgradCourses() {
        return courses.values();
    }

    @Override
    public void updatePostgradCourse(String course, PostgradCourses pg_course) {
        courses.put(course, pg_course);
    }

    @Override
    public void savePostgradCourse(PostgradCourses course) {
        courses.put(course.getCourse(), course);
    }
}
