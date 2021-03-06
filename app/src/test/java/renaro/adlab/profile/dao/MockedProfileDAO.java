package com.adlab.coupon.payless.profile.dao;

import java.util.ArrayList;
import java.util.List;

import com.adlab.coupon.payless.UserProfile;

/**
 * Created by renarosantos1 on 30/05/17.
 */

public class MockedProfileDAO extends ProfileDAO {
    @Override
    public List<UserProfile> fetchProfiles() {
        return new ArrayList<>();
    }

    @Override
    public int fetchRemainingVotes() {
        return 3;
    }

    @Override
    public boolean voteProfile(final UserProfile profile, final boolean vote) {
        return false;
    }
}
