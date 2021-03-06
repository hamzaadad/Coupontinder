package com.adlab.coupon.payless.votes.view;

import java.util.List;

import com.adlab.coupon.payless.UserProfile;

/**
 * Created by renarosantos on 05/02/17.
 */
public interface VotingActivityView {

    void showLoading();

    void hideLoading();

    void showProfiles(List<UserProfile> profiles);

    void showNegativeVote();

    void showPositiveVote();

    void showMatch(UserProfile profile);

    void showNewProfilePosition(int position, int total);

    void updatePercentage(int percentage);

    int cardsLeft();

    void showOutOfVotes();
}
