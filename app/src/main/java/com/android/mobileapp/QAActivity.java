package com.android.mobileapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class QAActivity extends ActionBarActivity implements View.OnClickListener {
    private final String TAG = ((Object) this).getClass().getSimpleName();

    private long question_id;
    private int forum_id;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        question_id = intent.getLongExtra(getString(R.string.Q_ID),-1);
        forum_id = intent.getIntExtra(getString(R.string.F_ID),-1);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        username = sharedPref.getString(getString(R.string.username),"zdg");

        setContentView(R.layout.activity_qa);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if (savedInstanceState == null) {
            PlaceholderFragment frag = new PlaceholderFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, frag)
                    .commit();
        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        ImageButton upVote = (ImageButton)findViewById(R.id.upvote_button);
        Button addAnswerBtn = (Button)findViewById(R.id.add_answer);
        upVote.setOnClickListener(this);
        addAnswerBtn.setOnClickListener(this);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new getAnswersTask().execute(question_id);
        }else{
            Toast.makeText(this, "The network is not available. Please open WIFI or Mobile network",
                    Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void onClick(View view) {
        EditText answerEditableField = (EditText)findViewById(R.id.answer_text);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            switch (view.getId()) {
                case R.id.upvote_button:
                    Toast.makeText(QAActivity.this, "You upvote the question", Toast.LENGTH_SHORT).show();
                    new questionRateTask().execute(question_id);
                    break;
                case R.id.add_answer:
                    Toast.makeText(QAActivity.this, "You answer the question", Toast.LENGTH_SHORT).show();
                    new addAnswerTask().execute(answerEditableField.getText().toString(),
                            username, "" + question_id);
                    break;
            }
            Intent n_intent = new Intent(QAActivity.this, ForumActivity.class);
            n_intent.putExtra(getString(R.string.map_to_forum_intent_extra), forum_id);
            startActivity(n_intent);
        }else{
            Toast.makeText(QAActivity.this, "The network is not available. Please open WIFI or Mobile network",
                    Toast.LENGTH_SHORT).show();
        }
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

            int question_rate = getArguments().getInt(getString(R.string.Q_RATE),-1);
            String question_content = getArguments().getString(getString(R.string.Q_CONTENT));
            String question_username = getArguments().getString(getString(R.string.Q_USER));

            View rootView = inflater.inflate(R.layout.fragment_qa, container, false);
            TextView tvQuestion = (TextView)rootView.findViewById(R.id.qa_question);
            tvQuestion.setText("rate: "+question_rate + " " + question_content);

            TextView tvQuestionUsername = (TextView)rootView.findViewById(R.id.question_username);
            tvQuestionUsername.setText(question_username);



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
            final answerRateAdapter mAnswerAdapter;
            ListView listView = (ListView)findViewById(R.id.listview_qa_answers);
            mAnswerAdapter = new answerRateAdapter(
                    QAActivity.this,
                    R.layout.list_item_answer_rate,
                    R.id.list_item_answer_textview_rate,
                    new ArrayList<Answer>(result));
            listView.setAdapter(mAnswerAdapter);
        }
    }

    private class addAnswerTask extends AsyncTask<String, Void,Void>
    {
        @Override
        protected Void doInBackground(String... params) {
            AnswerSvc.getOrInit(getString(R.string.serverUrl))
                    .addAnswer(params[0], params[1], Long.parseLong(params[2]));
            return null;
        }
    }

    private class answerRateAdapter extends ArrayAdapter<Answer> {

        public answerRateAdapter(Context context, int resource, int textViewResourceId, List<Answer> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            final Answer ans = getItem(position);
            if(convertView==null){
                convertView = LayoutInflater.from(this.getContext())
                        .inflate(R.layout.list_item_answer_rate, parent, false);
            }
            TextView tvContent = (TextView)convertView.findViewById(R.id.list_item_answer_textview_rate);
            ImageButton imageBt = (ImageButton)convertView.findViewById(R.id.rateButton);
            TextView tvAnswerUsername = (TextView)convertView.findViewById(R.id.answer_username);
            //add rate for answer
            imageBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConnectivityManager connMgr = (ConnectivityManager)
                            getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        if (view.getId() == R.id.rateButton) {
                            new answerRateTask().execute(ans.getAid());
                            Intent intent = new Intent(QAActivity.this, ForumActivity.class);
                            intent.putExtra(getString(R.string.map_to_forum_intent_extra), forum_id);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(QAActivity.this, "The network is not available. Please open WIFI or Mobile network",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
            tvContent.setText("rate: " + ans.getRate() + " " + ans.getContent());
            tvAnswerUsername.setText(ans.getUser().getUsername());
            return convertView;
        }
    }

    private class answerRateTask extends AsyncTask<Long,Void,Void>{

        @Override
        protected Void doInBackground(Long... answer_id) {
            AnswerSvc.getOrInit(getString(R.string.serverUrl)).rateAnswerById(answer_id[0]);
            return null;
        }
    }

    private class questionRateTask extends AsyncTask<Long, Void, Void>{

        @Override
        protected Void doInBackground(Long... question_id) {
            QuestionSvc.getOrInit(getString(R.string.serverUrl)).rateQuestionById(question_id[0]);
            return null;
        }
    }
}
