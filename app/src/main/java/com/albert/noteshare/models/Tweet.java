package com.albert.noteshare.models;

import org.parceler.Parcel;

/**
 * Created by albert on 9/18/17.
 */
@Parcel
public class Tweet {
    private String text;
    private String retweeted;
    private String name;
    private int followers;
    private String imageUrl;
    private int retweets;

    public Tweet(String text, String retweeted, String name, int followers, String imageUrl, int retweets) {
        this.text = text;
        this.retweeted = retweeted;
        this.name = name;
        this.followers = followers;
        this.imageUrl = getLargeImageUrl(imageUrl);
        this.retweets = retweets;
    }

    public Tweet() {}

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public int getFollowers() {
        return followers;
    }

    public String getRetweeted() {
        return retweeted;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getRetweets() {
        return retweets;
    }

    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 11).concat(".jpg");
        return largeImageUrl;
    }
}
