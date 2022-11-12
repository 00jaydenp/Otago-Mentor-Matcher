/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Feedback;
import domain.Match;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kieran
 */
public class FeedbackCollectionsDAO implements FeedbackDAO {

    private static final Map<Integer, Feedback> feedbacks = new HashMap<>();

    @Override
    public void deleteFeedback(Feedback feedback) {
        feedbacks.remove(feedback.getFeedbackID());
    }

    @Override
    public void updateFeedback(int id, Feedback updatedFeedback) {
        feedbacks.put(id, updatedFeedback);

    }

    @Override
    public Feedback getFeedback(int id) {
        return feedbacks.get(id);
    }

    @Override
    public void saveFeedback(Feedback feedback) {
        feedbacks.put(feedback.getFeedbackID(), feedback);
    }

    @Override
    public Collection<Feedback> getFeedbacks() {
        return feedbacks.values();
    }

}
