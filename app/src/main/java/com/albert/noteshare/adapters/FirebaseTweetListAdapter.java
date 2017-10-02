package com.albert.noteshare.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.albert.noteshare.models.Tweet;
import com.albert.noteshare.ui.TweetDetailActivity;
import com.albert.noteshare.util.ItemTouchHelperAdapter;
import com.albert.noteshare.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseTweetListAdapter extends FirebaseRecyclerAdapter<Tweet, FirebaseTweetViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Tweet> mTweets = new ArrayList<>();

    public FirebaseTweetListAdapter(Class<Tweet> modelClass, int modelLayout, Class<FirebaseTweetViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mTweets.add(dataSnapshot.getValue(Tweet.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mTweets, fromPosition, toPosition); // Changes position in the node as well
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mTweets.remove(position); // removes the item from it's position in the node
        getRef(position).removeValue();
    }

    @Override
    protected void populateViewHolder(final FirebaseTweetViewHolder viewHolder, Tweet model, int position) {
        viewHolder.bindTweet(model);
        viewHolder.mUserImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        /* On click listener to better the selection */
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TweetDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("tweets", Parcels.wrap(mTweets));
                mContext.startActivity(intent);
            }
        });
    }

    /* Method to trigger the the adapters cleanup()
    * This method re-assigns the index for each object */
    private void setIndexInFirebase() {
        for (Tweet tweet: mTweets) {
            int index = mTweets.indexOf(tweet);
            DatabaseReference ref = getRef(index);
            tweet.setIndex(Integer.toString(index));
            ref.setValue(tweet);
        }
    }

    /* Cleanup method */
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}
