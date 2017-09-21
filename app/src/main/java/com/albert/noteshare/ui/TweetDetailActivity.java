package com.albert.noteshare.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.albert.noteshare.R;
import com.albert.noteshare.adapters.TweetPagerAdapter;
import com.albert.noteshare.models.Tweet;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TweetDetailActivity extends AppCompatActivity {
    @Bind(R.id.tweetViewPager) ViewPager mTweetViewPager;
    private TweetPagerAdapter adapterViewPager;
    ArrayList<Tweet> mTweets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        ButterKnife.bind(this);

        mTweets = Parcels.unwrap(getIntent().getParcelableExtra("tweets"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new TweetPagerAdapter(getSupportFragmentManager(), mTweets);
        mTweetViewPager.setAdapter(adapterViewPager);
        mTweetViewPager.setCurrentItem(startingPosition);
    }
}
