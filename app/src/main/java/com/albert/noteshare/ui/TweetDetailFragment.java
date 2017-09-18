package com.albert.noteshare.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.albert.noteshare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TweetDetailFragment extends Fragment {


    public TweetDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tweet_detail, container, false);
    }

}
