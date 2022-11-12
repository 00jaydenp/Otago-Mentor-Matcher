/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Feedback;
import java.util.Collection;

/**
 *
 * @author kieran
 */
public interface FeedbackDAO {

    /**
     * Get a list of all meetings.
     *
     * @return list of meetings
     */
    Collection<Feedback> getFeedbacks();

    /**
     * Add new match
     *
     * @param feedback to add
     */
    void saveFeedback(Feedback feedback);

    /**
     * Get specific feedback
     *
     * @param id of feedback top get
     * @return specified feedback
     */
    Feedback getFeedback(int id);

    /**
     * Update existing feedback
     *
     * @param id of feedback to update
     * @param updatedFeedback updated details of feedback
     */
    void updateFeedback(int id, Feedback updatedFeedback);

    /**
     * Remove a feedback
     *
     * @param feedback to remove
     */
    void deleteFeedback(Feedback feedback);
}
