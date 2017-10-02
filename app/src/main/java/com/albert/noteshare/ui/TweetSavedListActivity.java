package com.albert.noteshare.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.albert.noteshare.Constants;
import com.albert.noteshare.R;
import com.albert.noteshare.adapters.FirebaseTweetListAdapter;
import com.albert.noteshare.adapters.FirebaseTweetViewHolder;
import com.albert.noteshare.models.Tweet;
import com.albert.noteshare.util.OnStartDragListener;
import com.albert.noteshare.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TweetSavedListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mTweetReference;
    private FirebaseTweetListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.tweetRecyclerView) RecyclerView mTweetRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_TWEET)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseTweetListAdapter(Tweet.class, R.layout.tweet_list_item_drag, FirebaseTweetViewHolder.class, query, this, this);

//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Tweet, FirebaseTweetViewHolder>(Tweet.class, R.layout.tweet_list_item_drag, FirebaseTweetViewHolder.class, mTweetReference) {
//            @Override
//            protected void populateViewHolder(FirebaseTweetViewHolder viewHolder, Tweet model, int position) {
//                viewHolder.bindTweet(model);
//            }
//        };
        mTweetRecyclerView.setHasFixedSize(true);
        mTweetRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mTweetRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mTweetRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder); // Method from Custom super class OnStartDragListener
    }

    /* On destroy method for the FirebaseAdapter */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
