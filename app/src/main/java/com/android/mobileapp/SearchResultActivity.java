package com.android.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;


public class SearchResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent intent_received = getIntent();
        String searchQuery = intent_received.getStringExtra(getString(R.string.question_search_content));
        int forum_id = intent_received.getIntExtra(getString(R.string.map_to_forum_intent_extra),-1);
        new getSearchResultTask().execute(searchQuery,forum_id+"");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
            View rootView = inflater.inflate(R.layout.fragment_search_result, container, false);
            return rootView;
        }
    }

    private class getSearchResultTask extends AsyncTask<String,Void,Collection<Question>>
    {
        private int fid;

        @Override
        protected Collection<Question> doInBackground(String... params) {
            Collection<Question> result = QuestionSvc.getOrInit(getString(R.string.serverUrl))
                    .searchByQuestionInForum(params[0],Long.parseLong(params[1]));
            fid = Integer.parseInt(params[1]);
            return result;
        }

        @Override
        protected void onPostExecute(Collection<Question> result){
            final questionAdapter mQuestionAdapter;
            ListView listView = (ListView)findViewById(R.id.search_listView);
            mQuestionAdapter = new questionAdapter(
                    SearchResultActivity.this,
                    R.layout.list_item_question,
                    R.id.list_item_question_textview,
                    new ArrayList<Question>(result)
            );
            listView.setAdapter(mQuestionAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Question question = mQuestionAdapter.getItem(position);
                    Intent intent = new Intent(SearchResultActivity.this,QAActivity.class);
                    intent.putExtra(getString(R.string.Q_CONTENT),question.getContent());
                    intent.putExtra(getString(R.string.Q_ID),question.getQid());
                    intent.putExtra(getString(R.string.Q_RATE),question.getRate());
                    intent.putExtra(getString(R.string.F_ID),fid);
                    startActivity(intent);
                }
            });
        }
    }
}
