package com.albert.noteshare.models;

/**
 * Created by albert on 9/18/17.
 */

public class Tweet {
    private String mText;
    private String mRetweeted;
    private String mName;
    private String mfollowers;
    private String mImageUrl;

    public Tweet(String text, String retweeted, String name, String followers, String imageUrl) {
        this.mText = text;
        this.mRetweeted = retweeted;
        this.mName = name;
        this.mfollowers = followers;
        this.mImageUrl = imageUrl;
    }

    public String getText() {
        return mText;
    }

    public String getName() {
        return mName;
    }

    public String getFollowers() {
        return mfollowers;
    }

    public String getRetweeted() {
        return mRetweeted;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
