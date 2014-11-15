package com.android.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class QAActivity extends ActionBarActivity {
    private final String TAG = ((Object) this).getClass().getSimpleName();
    public final static String Q_ID = "com.android.mobileapp.q_id";
    public final static String Q_CONTENT = "com.android.mobileapp.q_content";
    public final static String Q_RATE = "com.android.mobile.q_rate";

    private long question_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String question_content = intent.getStringExtra(Q_CONTENT);
        question_id = intent.getLongExtra(Q_ID,-1);
        Log.d(TAG, "question id is  " + question_id);
        Log.d(TAG,"intent has Q_RATE? " + intent.hasExtra(Q_RATE));
        int question_rate = intent.getIntExtra(Q_RATE,-1);
        setContentView(R.layout.activity_qa);
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
        getMenuInflater().inflate(R.menu.qa, menu);
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

            View rootView = inflater.inflate(R.layout.fragment_qa, container, false);
            ImageButton upVote = (ImageButton) rootView.findViewById(R.id.upvote_button);
            final View.OnClickListener Click = new View.OnClickListener(){
                @Override
                public void onClick (View view){
                    switch(view.getId()){
                        case R.id.upvote_button:
                            //add the rate + 1 here
                            Toast.makeText(getActivity(),"You upvote the question",Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            };
            upVote.setOnClickListener(Click);
            TextView tvQuestion = (TextView)rootView.findViewById(R.id.qa_question);
            tvQuestion.setText("rate: "+question_rate + " " + question_content);
            return rootView;
        }

    }

    private class getAnswersTask extends AsyncTask<Long, Void, Collection<Answer>>{


        @Override
        protected Collection<Answer> doInBackground(Long... qid) {
            return AnswerSvc.getOrInit(getString(R.string.serverUrl))
                    .findByQuestionId(qid[0]);
        }

        @Override
        protected void onPostExecute(Collection<Answer> result){
            final answerAdapter mAnswerAdapter;
            ListView listView = (ListView)findViewById(R.id.listview_qa_answers);
            mAnswerAdapter = new answerAdapter(
                    QAActivity.this,
                    R.layout.list_item_answer,
                    R.id.list_item_answer_textview,
                    new ArrayList<Answer>(result));
            listView.setAdapter(mAnswerAdapter);
        }

    }
}
