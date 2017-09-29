package com.albert.noteshare.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.albert.noteshare.Constants;
import com.albert.noteshare.R;
import com.albert.noteshare.models.Note;
import com.albert.noteshare.ui.WriteNoteActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseNotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseNotesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindNote(Note note) {
        TextView noteTextView = mView.findViewById(R.id.writeListTextView);
        TextView titleTextView = mView.findViewById(R.id.titleTextView);
        titleTextView.setText(note.getTitle());
        noteTextView.setText(note.getNote());
        Log.v("Note: ", String.valueOf(note.getNote()));
        Log.v("Title: ", String.valueOf(note.getTitle()));
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Note> notes = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_WRITTEN_NOTE);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    notes.add(snapshot.getValue(Note.class));
                }
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, WriteNoteActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("notes", Parcels.wrap(notes));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
