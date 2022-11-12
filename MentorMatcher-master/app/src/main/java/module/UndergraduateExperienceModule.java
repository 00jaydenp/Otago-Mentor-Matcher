/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import dao.UndergraduateExperienceDAO;
import domain.UndergraduateExperience;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import java.util.Collection;

/**
 *
 * @author David
 */
public class UndergraduateExperienceModule extends Jooby {

    public UndergraduateExperienceModule(UndergraduateExperienceDAO dao) {

        path("/api/undergraduate", () -> {
            get("", ctx -> {
                return dao.getUndergraduateExperiences();
            });

            post("", ctx -> {
                UndergraduateExperience undergraduate = ctx.body().to(UndergraduateExperience.class);
                dao.saveUndergraduateExperience(undergraduate);
                return ctx.send(StatusCode.CREATED);
            });
        });

        path("api/undergraduate/{person_ID}", () -> {
            get("", ctx -> {
                int personID = Integer.parseInt(ctx.path("person_ID").value());
                Collection<UndergraduateExperience> undergraduates = dao.getUndergraduateExperienceByID(personID);
                if (undergraduates == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return undergraduates;
                }
            });
        });

    }

}
