/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Match;
import domain.Meeting;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kieran
 */
public class MeetingCollectionsDAO implements MeetingDAO {

    private static final Map<Integer, Meeting> meetings = new HashMap<>();

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting.getMeetingID());
    }

    @Override
    public void updateMeeting(int id, Meeting updatedMeeting) {
        meetings.put(id, updatedMeeting);

    }

    @Override
    public Meeting getMeeting(int id) {
        return meetings.get(id);
    }

    @Override
    public void saveMeeting(Meeting meeting) {
        meetings.put(meeting.getMeetingID(), meeting);
    }

    @Override
    public Collection<Meeting> getMeetings() {
        return meetings.values();
    }

}
