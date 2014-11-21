package com.android.mobileapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;


public class ForumSearchActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_search);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        String search_content = getIntent().getStringExtra(getString(R.string.forum_search_content));
        new getForumSearchResult().execute(search_content);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.forum_search, menu);
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
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_forum_search, container, false);
            return rootView;
        }
    }

    private class getForumSearchResult extends AsyncTask<String,Void,Collection<Forum>>
    {

        @Override
        protected Collection<Forum> doInBackground(String... params) {
            return ForumSvc.getOrInit(getString(R.string.serverUrl))
                    .searchByName(params[0]);
        }

        @Override
        protected void onPostExecute(Collection<Forum> forums){
            TextView title = (TextView)findViewById(R.id.forum_search_title);
            if(forums == null||forums.size()==0){
                title.setText("No forum found!");
            }else{
                title.setText("Forum");
                ListView listView = (ListView)findViewById(R.id.forum_search_listView);
                final ArrayAdapter<Forum> forumArrayAdapter= new ArrayAdapter<Forum>(
                        ForumSearchActivity.this,
                        R.layout.list_item_forum,
                        R.id.list_item_forum_textview,
                        new ArrayList<Forum>(forums)
                );
                listView.setAdapter(forumArrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Forum f = forumArrayAdapter.getItem(i);
                        Intent forumIntent = new Intent(ForumSearchActivity.this,ForumActivity.class);
                        forumIntent.putExtra(getString(R.string.map_to_forum_intent_extra), f.getFid());
                        startActivity(forumIntent);
                    }
                });
            }
        }
    }
}
