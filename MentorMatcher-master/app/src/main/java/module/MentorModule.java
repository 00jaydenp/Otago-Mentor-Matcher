/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import dao.MentorDAO;
import domain.Mentor;
import io.jooby.Jooby;
import io.jooby.StatusCode;

/**
 *
 * @author jessierongen
 */
public class MentorModule extends Jooby {

    public MentorModule(MentorDAO dao) {
        path("/api/register/mentor", () -> {
            post("", ctx -> {
                Mentor mentor = ctx.body().to(Mentor.class);
                if (dao.getMentorByID(mentor.getMentorID()) == null) {
                    dao.saveMentor(mentor);
                    return ctx.send(StatusCode.CREATED);
                } else {
                    return ctx
                            .setResponseCode(StatusCode.UNPROCESSABLE_ENTITY)
                            .render(new ErrorMessage("That mentor already exists in the system"));
                }
            });
        });

        //get by personID
        path("/api/person/mentor/{person_ID}", () -> {
            get("", ctx -> {
                int id = Integer.parseInt(ctx.path("person_ID").value());
                Mentor mentor = dao.getByPersonID(id);
                if (mentor == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return mentor;
                }
            });
        });

        path("/api/mentors", () -> {
            get("", ctx -> {
                return dao.getMentors();
            });
        });

        path("/api/mentors/{mentor_ID}", () -> {
            get("", ctx -> {
                int id = Integer.parseInt(ctx.path("mentor_ID").value());
                Mentor mentor = dao.getMentorByID(id);
                if (mentor == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return mentor;
                }
            });

            put("", ctx -> {
                int id = Integer.parseInt(ctx.path("mentor_ID").value());
                if (dao.getMentorByID(id) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    Mentor mentor = ctx.body().to(Mentor.class);
                    if (id != mentor.getMentorID()) {
                        return ctx
                                .setResponseCode(StatusCode.CONFLICT)
                                .render(new ErrorMessage("You cannot edit the Mentor's ID using this method."));
                    } else {
                        dao.updateMentor(id, mentor);
                        return ctx.send(StatusCode.NO_CONTENT);
                    }
                }
            });

            delete("", ctx -> {
                int id = Integer.parseInt(ctx.path("mentor_ID").value());
                if (dao.getMentorByID(id) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    dao.deleteMentor(id);
                    return ctx.send(StatusCode.NO_CONTENT);
                }
            });

        });

    }
}
