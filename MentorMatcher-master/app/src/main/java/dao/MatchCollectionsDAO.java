/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import domain.Match;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jayden
 */
public class MatchCollectionsDAO implements MatchDAO {

    private static final Map<Integer, Match> matches = new HashMap<>();
    private static final Multimap<Integer, Match> matchMentorIDMap = ArrayListMultimap.create();
    private static final Multimap<Integer, Match> matchMenteeIDMap = ArrayListMultimap.create();

    /**
     * Delete a match
     *
     * @param match to delete
     */
    @Override
    public void deleteMatch(Match match) {
        matches.remove(match.getMatchID());
    }

    /**
     * Updates an existing match
     *
     * @param id of match to update
     * @param updatedMatch updated details of match
     */
    @Override
    public void updateMatch(int id, Match updatedMatch) {
        matches.put(id, updatedMatch);

    }

    /**
     * Gets a specific match.
     *
     * @param id of the match to return
     * @return Match who's id was specified.
     */
    @Override
    public Match getMatch(int id) {
        return matches.get(id);
    }

    /**
     * Adds a Match
     *
     * @param match to add
     */
    @Override
    public void saveMatch(Match match) {
        matches.put(match.getMatchID(), match);
        matchMentorIDMap.put(match.getMentorID(), match);
        matchMenteeIDMap.put(match.getMenteeID(), match);
    }

    /**
     * Gets a list of all matches.
     *
     * @return Collection of matches
     */
    @Override
    public Collection<Match> getMatches() {
        return matches.values();
    }

    /**
     * Gets a list of all matches for a specified mentor ID.
     *
     * @param mentorID the mentor ID to get matches for.
     * @return Collection of matches for a specified mentor.
     */
    @Override
    public Collection<Match> getMatchByMentorID(int mentorID) {
        return matchMentorIDMap.get(mentorID);
    }

    /**
     * Gets a list of all matches for a specified mentee ID.
     *
     * @param menteeID the mentee ID to get matches for.
     * @return Collection of matches for a specified mentee.
     */
    @Override
    public Collection<Match> getMatchByMenteeID(int menteeID) {
        return matchMenteeIDMap.get(menteeID);
    }

}
