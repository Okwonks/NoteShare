package com.albert.noteshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddNoteActivity extends AppCompatActivity {
    @Bind(R.id.addNoteButton) Button mAddNoteButton;
    @Bind(R.id.noteEditText) EditText mWriteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        mAddNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = mWriteEditText.getText().toString();
                Intent intent = new Intent(AddNoteActivity.this, NotesActivity.class);
                intent.putExtra("note", note);
                startActivity(intent);
            }
        });
    }
}
