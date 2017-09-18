package com.albert.noteshare.services;

import com.albert.noteshare.Constants;
import com.albert.noteshare.models.Tweet;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by albert on 9/15/17.
 */

public class TwitterService {
    public static void findTweet(String tweet, Callback callback) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.TWITTER_CONSUMER_KEY, Constants.TWITTER_CONSUMER_SECRET);
        consumer.setTokenWithSecret(Constants.TWITTER_ACCESS_TOKEN, Constants.TWITTER_TOKEN_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.TWITTER_TWEET_QUERY_PARAMETER, tweet);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Tweet> processResults(Response response) {
        ArrayList<Tweet> tweets = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject twitterJSON = new JSONObject(jsonData);
                JSONArray statusesJSON = twitterJSON.getJSONArray("statuses");
                for (int i = 0; i < statusesJSON.length(); i++) {
                    JSONObject tweetJSON = statusesJSON.getJSONObject(i);
                    String text = tweetJSON.getString("text");
                    String retweeted = tweetJSON.getString("retweeted");
                    String name = tweetJSON.getString("name");
                    String followers = tweetJSON.getString("followers_count");
                    String imageUrl = tweetJSON.getString("profile_image_url");

                    Tweet tweet = new Tweet(text, retweeted, name, followers, imageUrl);
                    tweets.add(tweet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweets;
    }
}
