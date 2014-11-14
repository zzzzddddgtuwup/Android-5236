package com.android.mobileapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class mainFragment extends Fragment  {

    public mainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        View btnProfile = (Button) rootView.findViewById(R.id.main_button_myProfile);
        View btnMyQuestion = (Button) rootView.findViewById(R.id.main_button_myQuestion);
        View btnMyAnswer = (Button) rootView.findViewById(R.id.main_button_myAnswer);
        View btnMap = (Button) rootView.findViewById(R.id.main_button_select_forum);
        View btnForum = (Button) rootView.findViewById(R.id.main_button_enter_forum);

        final View.OnClickListener Click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.main_button_select_forum:
                        Intent select_forum_intent = new Intent(getActivity(), SettingActivity.class);
                        startActivity(select_forum_intent);
                       // new ForumDetailFragment();//u cann.show(getChildFragmentManager(), "Forum");
                        break;
                    case R.id.main_button_myQuestion:
                        Intent myQuestion_intent = new Intent(getActivity(), MyQuestionActivity.class);
                        startActivity(myQuestion_intent);
                        break;
                    case R.id.main_button_myAnswer:
                        Intent myAnswer_intent = new Intent(getActivity(), MyAnswerActivity.class);
                        startActivity(myAnswer_intent);
                        break;
                    case R.id.main_button_myProfile:
                        Intent profile_intent = new Intent(getActivity(), AccountActivity.class);
                        startActivity(profile_intent);
                        break;
                    case R.id.main_button_enter_forum:
                        //new ForumDetailFragment();
                        Intent forum_intent = new Intent(getActivity(),ForumActivity.class);
                        startActivity(forum_intent);
                        break;
                }
            }
        };
        btnProfile.setOnClickListener(Click);
        btnMyQuestion.setOnClickListener(Click);
        btnMyAnswer.setOnClickListener(Click);
        btnForum.setOnClickListener(Click);
        btnMap.setOnClickListener(Click);
        return rootView;
    }

}
