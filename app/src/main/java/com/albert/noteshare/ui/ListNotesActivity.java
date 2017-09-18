package com.albert.noteshare.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class ListNotesActivity extends AppCompatActivity {
    @Bind(R.id.allNotesListView) ListView mAllNotesListView;
    private String[] notes = new String[] {"Read a book on health", "Get more information on life", "Talk about java to the world", "What is android development?", "What is the meaning of life", "Clean out the kitchen", "Walk the dogs", "Go for a jog", "Make some pastor", "Meet up with the friends", "Finish any pending chores", "Remember to call the I.T guy", "What do I need to make Android apps", "Is James Bond truly 007", "Get busy with real estate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        ButterKnife.bind(this);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
        mAllNotesListView.setAdapter(adapter);
    }
}
