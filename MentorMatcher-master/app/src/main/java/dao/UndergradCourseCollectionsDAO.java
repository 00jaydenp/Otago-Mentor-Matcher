/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.UndergradCourses;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jayden
 */
public class UndergradCourseCollectionsDAO implements UndergradCourseDAO {

    private static final Map<String, UndergradCourses> courses = new HashMap<>();

    @Override
    public void deleteUndergradCourse(UndergradCourses course) {
        courses.remove(course);
    }

    @Override
    public UndergradCourses getUndergradCourse(String course) {
        return courses.get(course);
    }

    @Override
    public Collection<UndergradCourses> getUndergradCourses() {
        return courses.values();
    }

    @Override
    public void updateUndergradCourse(String course, UndergradCourses ug_course) {
        courses.put(course, ug_course);
    }

    @Override
    public void saveUndergradCourse(UndergradCourses course) {
        courses.put(course.getCourse(), course);
    }
}
