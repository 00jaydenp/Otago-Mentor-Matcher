/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import dao.PostgraduateExperienceDAO;
import domain.PostgraduateExperience;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import java.util.Collection;

/**
 *
 * @author David
 */
public class PostgraduateExperienceModule extends Jooby {

    public PostgraduateExperienceModule(PostgraduateExperienceDAO dao) {

        path("/api/postgraduate", () -> {
            get("", ctx -> {
                return dao.getPostgraduateExperiences();
            });

            post("", ctx -> {
                PostgraduateExperience postgraduate = ctx.body().to(PostgraduateExperience.class);
                dao.savePostgraduateExperience(postgraduate);
                return ctx.send(StatusCode.CREATED);
            });
        });

        path("api/postgraduate/{person_ID}", () -> {
            get("", ctx -> {
                int personID = Integer.parseInt(ctx.path("person_ID").value());
                Collection<PostgraduateExperience> postgraduates = dao.getPostgraduateExperienceByID(personID);
                if (postgraduates == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return postgraduates;
                }
            });
        });

    }

}
