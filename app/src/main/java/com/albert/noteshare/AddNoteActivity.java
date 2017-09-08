package com.albert.noteshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {
    public static final String TAG = AddNoteActivity.class.getSimpleName();
    private EditText mWriteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }
}
