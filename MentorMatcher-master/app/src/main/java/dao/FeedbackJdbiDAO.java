/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import domain.Feedback;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author kieran
 */
public interface FeedbackJdbiDAO extends FeedbackDAO {

    @Override
    @SqlUpdate("DELETE FROM FEEDBACK WHERE FEEDBACK_ID = :feedbackID")
    public void deleteFeedback(@BindBean Feedback feedback);

    @Override
    @SqlUpdate("UPDATE FEEDBACK SET FEEDBACK_DATE = :feedbackDate, FEEDBACK_RATING = :feedbackRating, FEEDBACK_COMMENT = :feedbackComment WHERE FEEDBACK_ID = :feedbackID")
    public void updateFeedback(@Bind("feedbackID") int id, @BindBean Feedback updatedFeedback);

    @Override
    @SqlQuery("GET * FROM FEEDBACK WHERE FEEDBACK_ID = :feedbackID")
    public Feedback getFeedback(@Bind("feedbackID") int id);

    @Override
    @SqlUpdate("INSERT INTO FEEDBACK(PERSON_ID, FEEDBACK_DATE, FEEDBACK_RATING, FEEDBACK_COMMENT) VALUES (:person.personID, :feedbackDate, :feedbackRating, :feedbackComment)")
    public void saveFeedback(@BindBean Feedback feedback);

    @Override
    @SqlQuery("SELECT * FROM FEEDBACK ORDER BY FEEDBACK_ID")
    public Collection<Feedback> getFeedbacks();

}
