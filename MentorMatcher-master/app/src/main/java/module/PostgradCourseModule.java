/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import dao.PostgradCourseDAO;
import domain.PostgradCourses;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import static java.nio.file.Paths.get;

/**
 *
 * @author Jayden
 */
public class PostgradCourseModule extends Jooby {

    public PostgradCourseModule(PostgradCourseDAO dao) {
        path("/api/postgradcourses", () -> {
            get("", ctx -> {
                return dao.getPostgradCourses();
            });

            post("", ctx -> {
                PostgradCourses pg_course = ctx.body().to(PostgradCourses.class);
                if (dao.getPostgradCourse(pg_course.getCourse()) == null) {
                    dao.savePostgradCourse(pg_course);
                    return ctx.send(StatusCode.CREATED);
                } else {
                    return ctx
                            .setResponseCode(StatusCode.UNPROCESSABLE_ENTITY)
                            .render(new ErrorMessage("That Postgraduate Course  already exists in the system"));
                }
            });
        });

        path("/api/postgradcourses/{course}", () -> {
            get("", ctx -> {
                String course = ctx.path("course").value();
                PostgradCourses pg_course = dao.getPostgradCourse(course);
                if (pg_course == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return pg_course;
                }
            });

            put("", ctx -> {
                String course = ctx.path("course").value();
                if (dao.getPostgradCourse(course) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    PostgradCourses pg_course = ctx.body().to(PostgradCourses.class);
                    if (course.equals(pg_course.getCourse())) {
                        return ctx
                                .setResponseCode(StatusCode.CONFLICT)
                                .render(new ErrorMessage("You cannot edit the Courses Name using this method."));
                    } else {
                        dao.updatePostgradCourse(course, pg_course);
                        return ctx.send(StatusCode.NO_CONTENT);
                    }
                }
            });

            delete("", ctx -> {
                String course = ctx.path("course").value();
                if (dao.getPostgradCourse(course) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    PostgradCourses pg_course = dao.getPostgradCourse(course);
                    dao.deletePostgradCourse(pg_course);
                    return ctx.send(StatusCode.NO_CONTENT);
                }
            });

        });
    }
}
