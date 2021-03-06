package com.albert.noteshare.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.albert.noteshare.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.makeNoteButton) Button mMakeNoteButton;
    @Bind(R.id.appWelcomeTextView) TextView mAppWelcomeTextView;
    @Bind(R.id.allNotesButton) Button mAllNotesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface sansationFont = Typeface.createFromAsset(getAssets(), "fonts/sansation.ttf");
        mAppWelcomeTextView.setTypeface(sansationFont); // Giving the app it's custom font style.

        mMakeNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        mAllNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListNotesActivity.class);
                startActivity(intent);
            }
        });
    }
}
