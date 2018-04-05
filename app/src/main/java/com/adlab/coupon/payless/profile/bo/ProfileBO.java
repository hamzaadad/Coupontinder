package com.adlab.coupon.payless.profile.bo;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import java.util.List;

import com.adlab.coupon.payless.UserProfile;
import com.adlab.coupon.payless.profile.dao.ProfileDAO;
import com.adlab.coupon.payless.votes.model.VoteResponse;

/**
 * Created by renarosantos on 21/02/17.
 */
public class ProfileBO {

    private final ProfileDAO mDao;

    public ProfileBO(@NonNull final ProfileDAO dao) {
        mDao = dao;
    }

    public List<UserProfile> fetchProfiles() {
        return mDao.fetchProfiles();
    }

    @WorkerThread
    public VoteResponse profileVoted(final UserProfile profile, final boolean vote) {
        final int remainingVotes = mDao.fetchRemainingVotes();
        if (remainingVotes == 0){
            return new VoteResponse(false, true);
        } else {
            final boolean isAMatch = mDao.voteProfile(profile, vote);
            return new VoteResponse(isAMatch, false);
        }

    }
}
