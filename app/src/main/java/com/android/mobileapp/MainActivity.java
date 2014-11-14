package com.android.mobileapp;

import android.app.FragmentManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
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
import android.widget.ListView;


public class MainActivity extends FragmentActivity {
    private final String TAG = ((Object) this).getClass().getSimpleName();
    public static FragmentManager fragmentManager;

    //Variables for the Navigation Drawer
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mOptionsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "+++ In onCreate() +++");
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new mainFragment())
                    .commit();
        }

        fragmentManager = getFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"+++ In onStart() +++");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"+++ In onRestart() +++");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"+++ In onResume() +++");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"+++ In onPause() +++");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"+++ In onStop() +++");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"+++ In onDestroy() +++");
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
            //Intent intent = new Intent(this, LoginActivity.class);
            //startActivity(intent);
            new LoginFragment().show(getSupportFragmentManager(),"login");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
