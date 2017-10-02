package com.albert.noteshare.ui;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.albert.noteshare.Constants;
import com.albert.noteshare.R;
import com.albert.noteshare.models.Tweet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TweetDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.userNameTextView) TextView mUserName;
    @Bind(R.id.tweetImageView) ImageView mUserImageView;
    @Bind(R.id.retweetTextView) TextView mRetweetTextView;
    @Bind(R.id.tweetTextView) TextView mTweetTextView;
    @Bind(R.id.saveTweetButton) FloatingActionButton mSaveTweetButton;

    private Tweet mTweet;

    public static TweetDetailFragment newInstance(Tweet tweet) {
        TweetDetailFragment tweetDetailFragment = new TweetDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("tweet", Parcels.wrap(tweet));
        tweetDetailFragment.setArguments(args);
        return tweetDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTweet = Parcels.unwrap(getArguments().getParcelable("tweet"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tweetView = inflater.inflate(R.layout.fragment_tweet_detail, container, false);
        ButterKnife.bind(this, tweetView);

        Picasso.with(tweetView.getContext()).load(mTweet.getImageUrl()).into(mUserImageView);

        mUserName.setText(mTweet.getName());
        mRetweetTextView.setText("Retweets: " + mTweet.getRetweets());
        mTweetTextView.setText(mTweet.getText());

        mSaveTweetButton.setOnClickListener(this);
        return tweetView;
    }

    @Override
    public void onClick(View view) {
        if (view == mSaveTweetButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference tweetRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_TWEET)
                    .child(uid);
            DatabaseReference pushRef = tweetRef.push();
            String pushId = pushRef.getKey();
            mTweet.setPushId(pushId);
            pushRef.setValue(mTweet);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
