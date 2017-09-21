package com.albert.noteshare.models;

import org.parceler.Parcel;

/**
 * Created by albert on 9/18/17.
 */
@Parcel
public class Tweet {
    private String mText;
    private String mRetweeted;
    private String mName;
    private int mfollowers;
    private String mImageUrl;
    private int mRetweets;

    public Tweet(String text, String retweeted, String name, int followers, String imageUrl, int retweets) {
        this.mText = text;
        this.mRetweeted = retweeted;
        this.mName = name;
        this.mfollowers = followers;
        this.mImageUrl = imageUrl;
        this.mRetweets = retweets;
    }

    public Tweet() {}

    public String getText() {
        return mText;
    }

    public String getName() {
        return mName;
    }

    public int getFollowers() {
        return mfollowers;
    }

    public String getRetweeted() {
        return mRetweeted;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public int getRetweets() {
        return mRetweets;
    }

    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 11).concat(".jpg");
        return largeImageUrl;
    }
}
