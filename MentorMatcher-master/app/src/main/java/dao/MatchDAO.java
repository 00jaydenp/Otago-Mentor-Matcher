/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Match;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

/**
 *
 * @author Jayden
 */
public interface MatchDAO {

    /**
     * Get a list of all matches.
     *
     * @return list of matches
     */
    Collection<Match> getMatches();

    /**
     * Add new match
     *
     * @param match to add
     */
    void saveMatch(Match match);

    /**
     * Get specific match
     *
     * @param id of match top get
     * @return specified match
     */
    Match getMatch(int id);

    /**
     * Update existing match
     *
     * @param id of match to update
     * @param updatedMatch updated details of match
     */
    void updateMatch(int id, Match updatedMatch);

    /**
     * Remove a match
     *
     * @param match to remove
     */
    void deleteMatch(Match match);

    Collection<Match> getMatchByMentorID(@Bind("mentorID") int mentorID);

    Collection<Match> getMatchByMenteeID(@Bind("menteeID") int menteeID);
}
