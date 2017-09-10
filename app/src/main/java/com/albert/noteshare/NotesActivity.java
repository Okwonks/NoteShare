package com.albert.noteshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotesActivity extends AppCompatActivity {
    @Bind(R.id.notesListView) ListView mNotesListView;
    @Bind(R.id.noteTextView) TextView mNoteTextView;
    private String[] notes = new String[] {"Clean out the kitchen", "Walk the dogs", "Go for a jog", "Make some pastor", "Meet up with the friends", "Finish any pending chores", "Remember to call the I.T guy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
        mNotesListView.setAdapter(adapter); // Change to custom ArrayAdapter later on

        mNotesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String note = ((TextView)view).getText().toString();
                Toast.makeText(NotesActivity.this, note, Toast.LENGTH_LONG).show();
            }
        });


        Intent intent = getIntent();
        String newNote = intent.getStringExtra("note");
        mNoteTextView.setText("Your note: " + newNote + " is being added...");
    }
}
