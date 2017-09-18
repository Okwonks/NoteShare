package com.albert.noteshare.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.albert.noteshare.R;
import com.albert.noteshare.models.Tweet;
import com.albert.noteshare.services.TwitterService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NotesActivity extends AppCompatActivity {
    private static final String TAG = NotesActivity.class.getSimpleName();
    @Bind(R.id.notesListView) ListView mNotesListView;
    @Bind(R.id.noteTextView) TextView mNoteTextView;

    public ArrayList<Tweet> mTweets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
//        mNotesListView.setAdapter(adapter); // Change to custom ArrayAdapter later on

        mNotesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String note = ((TextView)view).getText().toString();
                Toast.makeText(NotesActivity.this, note, Toast.LENGTH_LONG).show();
            }
        });


        Intent intent = getIntent();
        String tweet = intent.getStringExtra("tweet");
        mNoteTextView.setText("Your note: " + tweet + " is being added...");

        getTweet(tweet);
    }

    private void getTweet(String tweet) {
        final TwitterService twitterService = new TwitterService();
        twitterService.findTweet(tweet, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mTweets = twitterService.processResults(response);

                NotesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] userName = new String[mTweets.size()];

                        for (int i = 0; i < userName.length; i++) {
                            userName[i] = mTweets.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(NotesActivity.this, android.R.layout.simple_list_item_1, userName);
                        mNotesListView.setAdapter(adapter);
                    }
                });
                for (Tweet tweet: mTweets) {
                    Log.d(TAG, "Text: " + tweet.getText());
                    Log.d(TAG, "Name: " + tweet.getName());
                    Log.d(TAG, "ImageUrl: " + tweet.getImageUrl());
                }
            }
        });
    }
}
