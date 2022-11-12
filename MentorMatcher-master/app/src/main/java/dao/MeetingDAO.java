/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Meeting;
import java.util.Collection;

/**
 *
 * @author kieran
 */
public interface MeetingDAO {

    /**
     * Get a list of all meetings.
     *
     * @return list of meetings
     */
    Collection<Meeting> getMeetings();

    /**
     * Add new match
     *
     * @param meeting to add
     */
    void saveMeeting(Meeting meeting);

    /**
     * Get specific meeting
     *
     * @param id of meeting top get
     * @return specified meeting
     */
    Meeting getMeeting(int id);

    /**
     * Update existing meeting
     *
     * @param id of meeting to update
     * @param updatedMeeting updated details of meeting
     */
    void updateMeeting(int id, Meeting updatedMeeting);

    /**
     * Remove a meeting
     *
     * @param meeting to remove
     */
    void deleteMeeting(Meeting meeting);

}
