/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.UndergradCourses;
import java.util.Collection;

/**
 *
 * @author Jayden
 */
public interface UndergradCourseDAO {

    void deleteUndergradCourse(UndergradCourses course);

    void saveUndergradCourse(UndergradCourses course);

    Collection<UndergradCourses> getUndergradCourses();

    UndergradCourses getUndergradCourse(String course);

    void updateUndergradCourse(String course, UndergradCourses ug_course);
}
