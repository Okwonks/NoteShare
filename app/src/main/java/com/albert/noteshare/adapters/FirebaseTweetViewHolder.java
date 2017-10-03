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
import com.albert.noteshare.util.ItemTouchHelperViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseTweetViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    /*Constants for resizing the images from the api call*/
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    public ImageView mUserImageView; // For the tweets being retrieved

    View mView;
    Context mContext;

    public FirebaseTweetViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindTweet(Tweet tweet) {
        mUserImageView = (ImageView) mView.findViewById(R.id.userImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.userName);
        TextView retweetsTextView = (TextView) mView.findViewById(R.id.retweetsTextView);
        TextView followersTextView = (TextView) mView.findViewById(R.id.followersTextView);

        Picasso.with(mContext)
                .load(tweet.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT) // This is to resize images and prevent out of memory error.
                .centerCrop()
                .into(mUserImageView);
        nameTextView.setText(tweet.getName());
        retweetsTextView.setText("Retweets: " + tweet.getRetweets()); // Required to concat due to save issues.
        followersTextView.setText("Followers: " + tweet.getFollowers());
    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }
}
