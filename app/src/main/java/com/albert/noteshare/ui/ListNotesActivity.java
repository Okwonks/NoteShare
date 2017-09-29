package com.albert.noteshare.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.albert.noteshare.Constants;
import com.albert.noteshare.R;
import com.albert.noteshare.adapters.FirebaseNotesViewHolder;
import com.albert.noteshare.models.Note;
import com.albert.noteshare.models.Tweet;
import com.albert.noteshare.services.TwitterService;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ListNotesActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mNoteReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.addNoteButton) FloatingActionButton mFloatingButton;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        ButterKnife.bind(this);

        mNoteReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_WRITTEN_NOTE);
        setUpFirebaseAdapter();

        mFloatingButton.setOnClickListener(this);
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Note, FirebaseNotesViewHolder>(Note.class, R.layout  .view_notes_list_item, FirebaseNotesViewHolder.class, mNoteReference) {
            @Override
            protected void populateViewHolder(FirebaseNotesViewHolder viewHolder, Note model, int position) {
                viewHolder.bindNote(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onClick(View view) {
        if (view == mFloatingButton) {
            Intent intent = new Intent(ListNotesActivity.this, WriteNoteActivity.class);
            startActivity(intent);
        }

    }
}
