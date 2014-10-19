package com.android.mobileapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.os.Build;
import android.content.Intent;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    private final String TAG = ((Object) this).getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "+++ In onCreate() +++");
        setContentView(R.layout.activity_main);




        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment{

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            View btnProfile = (Button) rootView.findViewById(R.id.main_button_profile);
            View btnMyQuestion = (Button) rootView.findViewById(R.id.main_button_myQuestion);
            View btnMyAnswer = (Button) rootView.findViewById(R.id.main_button_myAnswer);
            View btnForum = (Button) rootView.findViewById(R.id.main_button_forum);

            final OnClickListener Click = new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("zdg","I ma here");
                    switch (view.getId()) {
                        case R.id.main_button_profile:
                            Intent profile_intent = new Intent(getActivity(), SettingActivity.class);
                            startActivity(profile_intent);
                            Log.e("zdg","button is pressed");
                            break;
                        case R.id.main_button_myQuestion:
                            Intent myQuestion_intent = new Intent(getActivity(), MyQuestionActivity.class);
                            startActivity(myQuestion_intent);
                            break;
                        case R.id.main_button_myAnswer:
                            Intent myAnswer_intent = new Intent(getActivity(), MyAnswerActivity.class);
                            startActivity(myAnswer_intent);
                            break;
                        case R.id.main_button_forum:
                            Intent forum_intent = new Intent(getActivity(), ForumActivity.class);
                            startActivity(forum_intent);
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


}
