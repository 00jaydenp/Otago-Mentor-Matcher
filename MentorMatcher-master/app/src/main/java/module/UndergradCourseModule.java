/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import dao.UndergradCourseDAO;
import domain.UndergradCourses;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import static java.nio.file.Paths.get;
import static javax.swing.UIManager.put;

/**
 *
 * @author Jayden
 */
public class UndergradCourseModule extends Jooby {

    public UndergradCourseModule(UndergradCourseDAO dao) {
        path("/api/undergradcourses", () -> {
            get("", ctx -> {
                return dao.getUndergradCourses();
            });

            post("", ctx -> {
                UndergradCourses ug_course = ctx.body().to(UndergradCourses.class);
                if (dao.getUndergradCourse(ug_course.getCourse()) == null) {
                    dao.saveUndergradCourse(ug_course);
                    return ctx.send(StatusCode.CREATED);
                } else {
                    return ctx
                            .setResponseCode(StatusCode.UNPROCESSABLE_ENTITY)
                            .render(new ErrorMessage("That Undergraduate Course  already exists in the system"));
                }
            });
        });

        path("/api/undergradcourses/{course}", () -> {
            get("", ctx -> {
                String course = ctx.path("course").value();
                UndergradCourses ug_course = dao.getUndergradCourse(course);
                if (ug_course == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return ug_course;
                }
            });

            put("", ctx -> {
                String course = ctx.path("course").value();
                if (dao.getUndergradCourse(course) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    UndergradCourses ug_course = ctx.body().to(UndergradCourses.class);
                    if (course.equals(ug_course.getCourse())) {
                        return ctx
                                .setResponseCode(StatusCode.CONFLICT)
                                .render(new ErrorMessage("You cannot edit the Courses Name using this method."));
                    } else {
                        dao.updateUndergradCourse(course, ug_course);
                        return ctx.send(StatusCode.NO_CONTENT);
                    }
                }
            });

            delete("", ctx -> {
                String course = ctx.path("course").value();
                if (dao.getUndergradCourse(course) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    UndergradCourses ug_course = dao.getUndergradCourse(course);
                    dao.deleteUndergradCourse(ug_course);
                    return ctx.send(StatusCode.NO_CONTENT);
                }
            });

        });

    }
}
