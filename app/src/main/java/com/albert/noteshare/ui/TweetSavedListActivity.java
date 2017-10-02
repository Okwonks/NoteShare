package com.albert.noteshare.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.albert.noteshare.Constants;
import com.albert.noteshare.R;
import com.albert.noteshare.adapters.FirebaseTweetViewHolder;
import com.albert.noteshare.models.Tweet;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TweetSavedListActivity extends AppCompatActivity {
    private DatabaseReference mTweetReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.tweetRecyclerView) RecyclerView mTweetRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mTweetReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_TWEET)
                .child(uid);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Tweet, FirebaseTweetViewHolder>(Tweet.class, R.layout.tweet_list_item_drag, FirebaseTweetViewHolder.class, mTweetReference) {
            @Override
            protected void populateViewHolder(FirebaseTweetViewHolder viewHolder, Tweet model, int position) {
                viewHolder.bindTweet(model);
            }
        };
        mTweetRecyclerView.setHasFixedSize(true);
        mTweetRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mTweetRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
