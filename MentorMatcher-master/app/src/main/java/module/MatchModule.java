/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import dao.MatchDAO;

import io.jooby.StatusCode;
import io.jooby.Jooby;
import java.util.Collection;

/**
 *
 * @author Jayden
 */
public class MatchModule extends Jooby {

    public MatchModule(MatchDAO dao) {

        path("/api/match", () -> {
            get("", ctx -> {
                return dao.getMatches();
            });

            post("", ctx -> {
                domain.Match match = ctx.body().to(domain.Match.class);
                if (dao.getMatch(match.getMatchID()) == null) {
                    dao.saveMatch(match);
                    return ctx.send(StatusCode.CREATED);
                } else {
                    return ctx
                            .setResponseCode(StatusCode.UNPROCESSABLE_ENTITY)
                            .render(new ErrorMessage("That match already exists in the system"));
                }
            });
        });

        path("/api/match/{match_ID}", () -> {
            get("", ctx -> {
                int id = Integer.parseInt(ctx.path("match_ID").value());
                domain.Match match = dao.getMatch(id);
                if (match == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return match;
                }
            });

            put("", ctx -> {
                int id = Integer.parseInt(ctx.path("match_ID").value());
                if (dao.getMatch(id) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {

                    domain.Match match = ctx.body().to(domain.Match.class);
                    if (id != match.getMatchID()) {
                        return ctx
                                .setResponseCode(StatusCode.CONFLICT)
                                .render(new ErrorMessage("You cannot edit the Match ID using this method."));
                    } else {
                        dao.updateMatch(id, match);

                        return ctx.send(StatusCode.NO_CONTENT);
                    }
                }
            });

            delete("", ctx -> {
                int id = Integer.parseInt(ctx.path("match_ID").value());
                if (dao.getMatch(id) == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    dao.deleteMatch(dao.getMatch(id));
                    return ctx.send(StatusCode.NO_CONTENT);
                }
            });

        });

        path("/api/mentor/match/{mentor_ID}", () -> {
            get("", ctx -> {
                int id = Integer.parseInt(ctx.path("mentor_ID").value());
                Collection<domain.Match> matches = dao.getMatchByMentorID(id);
                if (matches == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return matches;
                }
            });
        });

        path("/api/mentee/match/{mentee_ID}", () -> {
            get("", ctx -> {
                int id = Integer.parseInt(ctx.path("mentee_ID").value());
                Collection<domain.Match> matches = dao.getMatchByMenteeID(id);
                if (matches == null) {
                    return ctx.send(StatusCode.NOT_FOUND);
                } else {
                    return matches;
                }
            });
        });

    }
}
