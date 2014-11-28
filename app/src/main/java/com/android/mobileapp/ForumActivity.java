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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;


public class ForumActivity extends ActionBarActivity {
    private final String TAG = ((Object) this).getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "+++ In onCreate() +++");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        if (savedInstanceState == null) {
            FragmentTabsFragmentSupport tabhostFragment = new FragmentTabsFragmentSupport();
            tabhostFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, tabhostFragment)
                    .commit();
        }
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
}
