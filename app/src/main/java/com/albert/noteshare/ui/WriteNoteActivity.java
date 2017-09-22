package com.albert.noteshare.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.albert.noteshare.Constants;
import com.albert.noteshare.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WriteNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mWrittenNoteReference;
    private ValueEventListener mWrittenNoteReferenceListener;

    @Bind(R.id.noteSaveButton) FloatingActionButton mNoteSaveButton;
    @Bind(R.id.multiEditTextView) EditText mMultiEditTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mWrittenNoteReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_WRITTEN_NOTE);
        mWrittenNoteReferenceListener = mWrittenNoteReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                    String note = noteSnapshot.getValue().toString();
                    Log.d("Notes updated", "Note: " + note);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mNoteSaveButton) {
            String note = mMultiEditTextView.getText().toString();
            saveNoteToFirebase(note);

            Intent intent = new Intent(WriteNoteActivity.this, ListNotesActivity.class);
            intent.putExtra("note", note);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWrittenNoteReference.removeEventListener(mWrittenNoteReferenceListener);
    }

    private void saveNoteToFirebase(String note) {
        mWrittenNoteReference.push().setValue(note);
    }
}
