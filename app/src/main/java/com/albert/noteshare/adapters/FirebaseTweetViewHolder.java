package com.albert.noteshare.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.albert.noteshare.Constants;
import com.albert.noteshare.R;
import com.albert.noteshare.models.Tweet;
import com.albert.noteshare.ui.TweetDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseTweetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    /*Constants for resizing the images from the api call*/
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseTweetViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindTweet(Tweet tweet) {
        ImageView userImageView = (ImageView) mView.findViewById(R.id.userImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.userName);
        TextView retweetsTextView = (TextView) mView.findViewById(R.id.retweetsTextView);
        TextView followersTextView = (TextView) mView.findViewById(R.id.followersTextView);

        Picasso.with(mContext)
                .load(tweet.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT) // This is to resize images and prevent out of memory error.
                .centerCrop()
                .into(userImageView);
        nameTextView.setText(tweet.getName());
        retweetsTextView.setText("Retweets: " + tweet.getRetweets()); // Required to concat due to save issues.0
        followersTextView.setText("Followers: " + tweet.getFollowers());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Tweet> tweets = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TWEET);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    tweets.add(snapshot.getValue(Tweet.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, TweetDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("tweets", Parcels.wrap(tweets));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
