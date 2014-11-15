package com.android.mobileapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;


public class MyqaActivity extends ActionBarActivity {

    private long question_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String question_content = intent.getStringExtra(getString(R.string.Q_CONTENT));
        question_id = intent.getLongExtra(getString(R.string.Q_ID),-1);
        int question_rate = intent.getIntExtra(getString(R.string.Q_RATE),-1);

        setContentView(R.layout.activity_myqa);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment(question_content,question_rate))
                    .commit();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        new getAnswersTask().execute(question_id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.myqa, menu);
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
        private String question_content;
        private int question_rate;

        public PlaceholderFragment() {
        }

        public PlaceholderFragment(String question_content, int question_rate){
            this.question_content = question_content;
            this.question_rate = question_rate;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_myqa, container, false);

            TextView tvQuestion = (TextView)rootView.findViewById(R.id.my_qa_question);
            tvQuestion.setText("rate: "+question_rate + " " + question_content);

            return rootView;
        }
    }

    private class getAnswersTask extends AsyncTask<Long, Void, Collection<Answer>> {
        @Override
        protected Collection<Answer> doInBackground(Long... qid) {
            return AnswerSvc.getOrInit(getString(R.string.serverUrl))
                    .findByQuestionId(qid[0]);
        }

        @Override
        protected void onPostExecute(Collection<Answer> result){
            final answerAdapter mAnswerAdapter;
            ListView listView = (ListView)findViewById(R.id.my_listview_qa_answers);
            mAnswerAdapter = new answerAdapter(
                    MyqaActivity.this,
                    R.layout.list_item_answer,
                    R.id.list_item_answer_textview,
                    new ArrayList<Answer>(result));
            listView.setAdapter(mAnswerAdapter);
        }
    }
}
