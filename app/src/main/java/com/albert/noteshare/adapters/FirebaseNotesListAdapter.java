package com.albert.noteshare.adapters;

import android.content.Context;

import com.albert.noteshare.models.Note;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class FirebaseNotesListAdapter extends FirebaseRecyclerAdapter<Note, FirebaseNotesViewHolder> {
    private DatabaseReference mRef;
    private Context mContext;
    private ArrayList<Note> mNotes = new ArrayList<>();

    public FirebaseNotesListAdapter(Class<Note> modelClass, int modelLayout, Class<FirebaseNotesViewHolder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseNotesViewHolder viewHolder, Note model, int position) {
        viewHolder.bindNote(model);
    }
}
