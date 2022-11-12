/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import dao.MenteeDAO;
import domain.Mentee;
import io.jooby.Jooby;
import io.jooby.StatusCode;

/**
 *
 * @author Jayden
 */
public class MenteeModule extends Jooby {

    public MenteeModule(MenteeDAO dao) {
        path("/api/register/mentee", () -> {
            post("", ctx -> {
                Mentee mentee = ctx.body().to(Mentee.class);
                if (dao.getMenteeByID(mentee.getMenteeID()) == null) {
                    dao.saveMentee(mentee);
                    return ctx.send(StatusCode.CREATED);
                } else {
                    return ctx
                            .setResponseCode(StatusCode.UNPROCESSABLE_ENTITY)
                            .render(new ErrorMessage("That mentee already exists in the system"));
                }
            });
        });

        //get by personID
        path("/api/person/mentee/{person_ID}", () -> {
            get("", ctx -> {
                int id = Integer.parseInt(ctx.path("person_ID").value());
                Mentee mentee = dao.getByPersonID(id);
                if (mentee == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return mentee;
                }
            });
        });

        path("/api/mentees", () -> {
            get("", ctx -> {
                return dao.getMentees();
            });
        });

        path("/api/mentees/{mentee_ID}", () -> {
            get("", ctx -> {
                int id = Integer.parseInt(ctx.path("mentee_ID").value());
                Mentee mentee = dao.getMenteeByID(id);
                if (mentee == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return mentee;
                }
            });

            put("", ctx -> {
                int id = Integer.parseInt(ctx.path("mentee_ID").value());
                if (dao.getMenteeByID(id) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    Mentee mentee = ctx.body().to(Mentee.class);
                    if (id != mentee.getMenteeID()) {
                        return ctx
                                .setResponseCode(StatusCode.CONFLICT)
                                .render(new ErrorMessage("You cannot edit the Mentee's ID using this method."));
                    } else {
                        dao.updateMentee(id, mentee);
                        return ctx.send(StatusCode.NO_CONTENT);
                    }
                }
            });

            delete("", ctx -> {
                int id = Integer.parseInt(ctx.path("mentee_ID").value());
                if (dao.getMenteeByID(id) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    dao.deleteMentee(dao.getMenteeByID(id));
                    return ctx.send(StatusCode.NO_CONTENT);
                }
            });

        });

    }
}
