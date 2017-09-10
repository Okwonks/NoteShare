package com.albert.noteshare;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mMakeNoteButton;
    private TextView mAppWelcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMakeNoteButton = (Button) findViewById(R.id.makeNoteButton);
        mMakeNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);

                mAppWelcomeTextView = (TextView) findViewById(R.id.appWelcomeTextView);
                Typeface sansationFont = Typeface.createFromAsset(getAssets(), "fonts/sansation.ttf");
                mAppWelcomeTextView.setTypeface(sansationFont);
            }
        });
    }
}
