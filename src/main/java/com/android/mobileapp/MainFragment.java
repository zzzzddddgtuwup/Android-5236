package com.android.mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainFragment extends Fragment {

    private final String TAG = ((Object) this).getClass().getSimpleName();

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        View btnProfile = (Button) rootView.findViewById(R.id.main_button_profile);
        View btnMyQuestion = (Button) rootView.findViewById(R.id.main_button_myQuestion);
        View btnMyAnswer = (Button) rootView.findViewById(R.id.main_button_myAnswer);
        View btnForum = (Button) rootView.findViewById(R.id.main_button_forum);

        final View.OnClickListener Click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "Inside onclick listener");
                switch (view.getId()) {
                    case R.id.main_button_profile:
                        Intent profile_intent = new Intent(getActivity(), SettingActivity.class);
                        startActivity(profile_intent);
                        Log.e(TAG, "profile button is pressed");
                        break;
                    case R.id.main_button_myQuestion:
                        Intent myQuestion_intent = new Intent(getActivity(), MyQuestionActivity.class);
                        startActivity(myQuestion_intent);
                        Log.e(TAG, "Question button is pressed");
                        break;
                    case R.id.main_button_myAnswer:
                        Intent myAnswer_intent = new Intent(getActivity(), MyAnswerActivity.class);
                        startActivity(myAnswer_intent);
                        Log.e(TAG, "Answer button is pressed");
                        break;
                    case R.id.main_button_forum:
                        Intent forum_intent = new Intent(getActivity(), ForumActivity.class);
                        startActivity(forum_intent);
                        Log.e(TAG, "Forum button is pressed");
                        break;
                }
            }
        };
        btnProfile.setOnClickListener(Click);
        btnMyQuestion.setOnClickListener(Click);
        btnMyAnswer.setOnClickListener(Click);
        btnForum.setOnClickListener(Click);
        return rootView;
    }
}
