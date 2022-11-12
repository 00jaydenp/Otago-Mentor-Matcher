/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import dao.WorkExperienceDAO;
import domain.WorkExperience;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import java.util.Collection;

/**
 *
 * @author David
 */
public class WorkModule extends Jooby {

    public WorkModule(WorkExperienceDAO dao) {

        path("/api/work", () -> {
            get("", ctx -> {
                return dao.getWorkExperiences();
            });

            post("", ctx -> {
                WorkExperience work = ctx.body().to(WorkExperience.class);
                dao.saveWorkExperience(work);
                return ctx.send(StatusCode.CREATED);
            });
        });

        path("api/work/{mentor_ID}", () -> {
            get("", ctx -> {
                int mentorID = Integer.parseInt(ctx.path("mentor_ID").value());
                Collection<WorkExperience> works = dao.getMentorExperienceByID(mentorID);
                if (works == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return works;
                }
            });
        });

    }

}
