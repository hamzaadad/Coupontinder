package renaro.tinderlikesample.votes.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.List;
import java.util.Locale;

import renaro.tinderlikesample.R;
import renaro.tinderlikesample.UserProfile;
import renaro.tinderlikesample.base.BaseActivity;
import renaro.tinderlikesample.profile.bo.ProfileBO;
import renaro.tinderlikesample.profile.dao.AppProfileDAO;
import renaro.tinderlikesample.task.AppTaskExecutor;
import renaro.tinderlikesample.votes.presenter.VotingPresenter;

public class VotingActivity extends BaseActivity<VotingPresenter>
        implements VotingActivityView, ProfileAdapter.ProfileListener {

    private View loading;
    private SwipeFlingAdapterView mSwipeList;
    private ProfileAdapter mAdapter;
    private MatchDialog mMatchDialog;
    private TextView paginationLabel;
    private TextView worksPercentage;
    private RelativeLayout dot;

    @NonNull
    @Override
    protected VotingPresenter createPresenter(@NonNull final Context context) {
        return new VotingPresenter(this, new AppTaskExecutor(this), new ProfileBO(new AppProfileDAO()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        loading = findViewById(R.id.loading);
        dot = (RelativeLayout) findViewById(R.id.dot);
        mSwipeList = (SwipeFlingAdapterView) findViewById(R.id.swipe_list);
        paginationLabel = (TextView) findViewById(R.id.pagination_label);
        worksPercentage = (TextView) findViewById(R.id.works_percentage);
        mSwipeList.setFlingListener(new SwipeListener());
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProfiles(final List<UserProfile> profiles) {
        UserProfile[] array = new UserProfile[profiles.size()];
        mAdapter = new ProfileAdapter(this, R.layout.profile_card, profiles.toArray(array));
        mAdapter.setListener(this);
        mSwipeList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        setPaginationIndex(mAdapter.getCurrentProfileIndex(), profiles.size());
        updatePercentage(mAdapter.getCurrentProfilePercentage());
    }

    @Override
    public void showNegativeVote() {
        mSwipeList.getTopCardListener().selectLeft();
    }

    @Override
    public void showPositiveVote() {
        mSwipeList.getTopCardListener().selectRight();
    }

    @Override
    public void showMatch(final UserProfile profile) {
        mMatchDialog = new MatchDialog(this);
        mMatchDialog.setProfile(profile);
        mMatchDialog.show();
    }

    @Override
    public void showNewProfilePosition(int position, int total) {
        setPaginationIndex(position, total);
    }

    @Override
    public void updatePercentage(int percentage) {
        if (percentage < 0)
            Toast.makeText(this, "Profile has invalid percentage...", Toast.LENGTH_SHORT).show();
        else {
            if (percentage < 50)
                dot.setBackgroundResource(R.drawable.red_circle);
            else if (percentage < 80)
                dot.setBackgroundResource(R.drawable.orange_circle);
            else
                dot.setBackgroundResource(R.drawable.green_circle);

            worksPercentage.setText(percentage + "% works");
        }
    }

    private void setPaginationIndex(int position, int total) {
        paginationLabel.setText(String.format(Locale.getDefault(), "%d of %d", position, total));
    }

    @Override
    public int cardsLeft() {
        return mSwipeList.getChildCount();
    }

    @Override
    public void showOutOfVotes() {
        Toast.makeText(this, "Out of Votes! Wait", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProfileRemoved(@NonNull final UserProfile profile, int newPosition, int total, int percentage) {
        mPresenter.onProfileRemoved(profile, newPosition, total, percentage);
    }

    @Override
    public void onEmptyList() {
        mPresenter.onEmptyList();
    }

    @Override
    public void onThumbsUp() {
        mPresenter.onPositiveButtonClicked();
    }

    @Override
    public void onThumbsDown() {
        mPresenter.onNegativeButtonClicked();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMatchDialog != null) {
            mMatchDialog.dismiss();
        }
    }

    private class SwipeListener implements SwipeFlingAdapterView.onFlingListener {
        @Override
        public void removeFirstObjectInAdapter() {
            mAdapter.removeTop();
        }

        @Override
        public void onLeftCardExit(final Object o) {
            mPresenter.onSlideProfileToLeft();
        }

        @Override
        public void onRightCardExit(final Object o) {
            mPresenter.onSlideProfileToRight();
        }

        @Override
        public void onAdapterAboutToEmpty(final int i) {
        }

        @Override
        public void onScroll(final float v) {
        }
    }
}
