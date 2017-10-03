package com.albert.noteshare.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.albert.noteshare.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.addNoteButton) Button mAddNoteButton;
    @Bind(R.id.noteEditText) EditText mWriteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        mAddNoteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String tweet = mWriteEditText.getText().toString();
        Intent intent = new Intent(AddNoteActivity.this, TweetsActivity.class);
        intent.putExtra("tweet", tweet);
        startActivity(intent);
    }
}
