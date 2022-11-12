/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import domain.Meeting;
import domain.Mentor;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author kieran
 */
public interface MeetingJdbiDAO extends MeetingDAO {

    @Override
    @SqlUpdate("DELETE FROM MEETING WHERE MEETING_ID = :meetingID")
    public void deleteMeeting(@BindBean Meeting meeting);

    @Override
    @SqlUpdate("UPDATE MEETING SET MEETING_TIME = :meetingTime, MEETING_LENGTH = :meetingLength, IN_PERSON = :inPerson, NOTES = :notes WHERE MEETING_ID = :meetingID")
    public void updateMeeting(@Bind("meetingID") int id, @BindBean Meeting updatedMeeting);

    @Override
    @SqlQuery("GET * FROM MEETING WHERE MEETING_ID = :meetingID")
    public Meeting getMeeting(@Bind("meetingID") int id);

    @Override
    @SqlUpdate("INSERT INTO MEETING(MATCH_ID, MEETING_TIME, MEETING_LENGTH, IN_PERSON, NOTES) VALUES (:match.matchID, :meetingTime, :meetingLength, :inPerson, :notes)")
    public void saveMeeting(@BindBean Meeting meeting);

    @Override
    @SqlQuery("SELECT * FROM MEETING ORDER BY MEETING_ID")
    public Collection<Meeting> getMeetings();

}
