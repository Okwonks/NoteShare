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

public class NotesActivity extends AppCompatActivity {
    private TextView mNoteTextView;
    private ListView mNotesListView;
    private String[] notes = new String[] {"Clean out the kitchen", "Walk the dogs", "Go for a jog", "Make some pastor"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        mNotesListView = (ListView) findViewById(R.id.notesListView);
        mNoteTextView = (TextView) findViewById(R.id.noteTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
        mNotesListView.setAdapter(adapter);

        mNotesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String note = ((TextView)view).getText().toString();
                Toast.makeText(NotesActivity.this, note, Toast.LENGTH_LONG).show();
            }
        });


        Intent intent = getIntent();
        String newNote = intent.getStringExtra("note"); // Fix this with a TextView later on.
        mNoteTextView.setText("Your note: " + newNote + " is being added...");
    }
}
