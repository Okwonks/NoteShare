package com.albert.noteshare.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.albert.noteshare.R;
import com.albert.noteshare.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by albert on 9/18/17.
 */

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
    private ArrayList<Tweet> mTweets = new ArrayList<>();
    private Context mContext;

    public NoteListAdapter(Context context, ArrayList<Tweet> tweets) {
        mContext = context;
        mTweets = tweets;
    }

    @Override
    public NoteListAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_item, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoteListAdapter.NoteViewHolder holder, int position) {
        holder.bindTweet(mTweets.get(position));
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.userImageView) ImageView mUserImageView;
        @Bind(R.id.userName) TextView mUserName;
        @Bind(R.id.followersTextView) TextView mFollowersTextView;
        @Bind(R.id.retweetsTextView) TextView mRetweetsTextView;

        private Context mContext;

        public NoteViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mContext = view.getContext();
        }

        public void bindTweet(Tweet tweet) {
            Picasso.with(mContext).load(tweet.getImageUrl()).into(mUserImageView);
            mUserName.setText(tweet.getName());
            mRetweetsTextView.setText("Retweets: " + tweet.getRetweets());
            mFollowersTextView.setText("Followers: " + tweet.getFollowers());
        }
    }
}
/*
    @Override
    public NoteListAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    }

    @Override
    public void onBindViewHolder(NoteListAdapter.NoteViewHolder holder, int position) {

    }

    @Override
    public int getItem() {

    }*/