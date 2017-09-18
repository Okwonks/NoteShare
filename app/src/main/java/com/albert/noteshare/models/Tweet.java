package com.albert.noteshare.models;

/**
 * Created by albert on 9/18/17.
 */

public class Tweet {
    private String mText;
    private boolean mRetweeted;
    private String mName;
    private int mfollowers;

    public Tweet(String text, boolean retweeted, String name, int followers) {
        this.mText = text;
        this.mRetweeted = retweeted;
        this.mName = name;
        this.mfollowers = followers;
    }

    public String getText() {
        return mText;
    }

    public String getName() {
        return mName;
    }

    public int getFollowers() {
        return mfollowers;
    }

    public boolean getRetweeted() {
        return mRetweeted;
    }
}
