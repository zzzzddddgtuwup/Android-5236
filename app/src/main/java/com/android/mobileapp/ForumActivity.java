package com.android.mobileapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;


public class ForumActivity extends ActionBarActivity {
    private final String TAG = ((Object) this).getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "+++ In onCreate() +++");
        setContentView(R.layout.activity_forum);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentTabsFragmentSupport())
                    .commit();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.forum, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_login) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_forum, container, false);
//            return rootView;
//        }
//    }

    public class FragmentTabsFragmentSupport extends Fragment {
        private FragmentTabHost mTabHost;

        public FragmentTabsFragmentSupport() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            mTabHost = new FragmentTabHost(getActivity());
            mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.fragmentTabHost);

            mTabHost.addTab(mTabHost.newTabSpec("allQuestions").setIndicator("all"),
                    Tab1Fragment.class, null);
            mTabHost.addTab(mTabHost.newTabSpec("topFive").setIndicator("popular"),
                    Tab2Fragment.class, null);
            mTabHost.addTab(mTabHost.newTabSpec("askAndSearch").setIndicator("askAndSearch"),
                    Tab3Fragment.class, null);

            return mTabHost;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            mTabHost = null;
        }
    }


}
