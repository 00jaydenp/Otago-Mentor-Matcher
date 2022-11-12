/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Match;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author Jayden
 */
public interface MatchJdbiDAO extends MatchDAO {

    @Override
    @SqlUpdate("DELETE FROM MATCH WHERE MATCH_ID = :matchID")
    public void deleteMatch(@BindBean Match match);

    @Override
    @SqlUpdate("UPDATE MATCH SET APPROVED = :approved, ADMIN_APPROVED = :adminApproved, MATCH_DATE= :matchDate WHERE MATCH_ID = :matchID")
    public void updateMatch(@Bind("matchID") int id, @BindBean Match updatedMatch);

    @Override
    @SqlQuery("SELECT * FROM MATCH WHERE MATCH_ID = :matchID")
    @RegisterBeanMapper(Match.class)
    public Match getMatch(@Bind("matchID") int id);

    @Override
    @SqlUpdate("INSERT INTO MATCH(MATCH_ID, MENTEE_ID, MENTOR_ID, APPROVED, ADMIN_APPROVED, MATCH_DATE) VALUES (:matchID, :mentee.menteeID, :mentor.mentorID, 'false', 'false', :matchDate)")
    public void saveMatch(@BindBean Match match);

    @Override
    @SqlQuery("SELECT * FROM MATCH ORDER BY MATCH_ID")
    @RegisterBeanMapper(Match.class)
    public Collection<Match> getMatches();

    @Override
    @SqlQuery("SELECT * FROM MATCH WHERE MENTOR_ID = :mentorID")
    @RegisterBeanMapper(Match.class)
    public Collection<Match> getMatchByMentorID(@Bind("mentorID") int mentorID);

    @Override
    @SqlQuery("SELECT * FROM MATCH WHERE MENTEE_ID = :menteeID")
    @RegisterBeanMapper(Match.class)
    public Collection<Match> getMatchByMenteeID(@Bind("menteeID") int menteeID);

}
