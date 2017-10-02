package com.albert.noteshare;

/**
 * Created by albert on 9/15/17.
 */

public class Constants {
    public static final String TWITTER_CONSUMER_KEY = BuildConfig.TWITTER_CONSUMER_KEY;
    public static final String TWITTER_CONSUMER_SECRET = BuildConfig.TWITTER_CONSUMER_SECRET;
    public static final String TWITTER_ACCESS_TOKEN = BuildConfig.TWITTER_ACCESS_TOKEN;
    public static final String TWITTER_TOKEN_SECRET = BuildConfig.TWITTER_TOKEN_SECRET;
    public static final String BASE_URL = "https://api.twitter.com/1.1/search/tweets.json";
    public static final String TWITTER_TWEET_QUERY_PARAMETER = "q";
    public static final String PREFERENCES_TWEET_KEY = "tweet";
    public static final String FIREBASE_CHILD_WRITTEN_NOTE = "writtenNote";
    public static final String FIREBASE_CHILD_TWEET = "tweets";
    public static final String FIREBASE_QUERY_INDEX = "index";
}
