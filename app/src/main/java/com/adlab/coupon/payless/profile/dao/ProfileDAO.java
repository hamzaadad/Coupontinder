package renaro.adlab.profile.dao;

import java.util.List;

import renaro.adlab.UserProfile;

/**
 * Created by renarosantos on 21/02/17.
 */
public abstract class ProfileDAO {

    public abstract List<UserProfile> fetchProfiles();

    public abstract int fetchRemainingVotes();

    public abstract boolean voteProfile(final UserProfile profile, final boolean vote);
}
