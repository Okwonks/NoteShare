package com.albert.noteshare.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.albert.noteshare.Constants;
import com.albert.noteshare.R;
import com.albert.noteshare.models.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
                    String userNote = noteSnapshot.getValue().toString();
                    Log.d("Notes updated", "Note: " + userNote);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        ButterKnife.bind(this);

        mNoteSaveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mNoteSaveButton) {
            String userNote = mMultiEditTextView.getText().toString();
            Note note = new Note(userNote, "");
            saveNoteToFirebase(note);

            Intent intent = new Intent(WriteNoteActivity.this, ListNotesActivity.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "Note saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWrittenNoteReference.removeEventListener(mWrittenNoteReferenceListener);
    }

    private void saveNoteToFirebase(Note note) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference noteRef = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_WRITTEN_NOTE)
                .child(uid);
        DatabaseReference pushRef = noteRef.push();
        String pushId = pushRef.getKey();
        note.setPushId(pushId);
        pushRef.setValue(note);
    }
}
