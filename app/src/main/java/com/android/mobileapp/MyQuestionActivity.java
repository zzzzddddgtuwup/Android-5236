package com.android.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyQuestionActivity extends ActionBarActivity {
    private final String TAG = ((Object) this).getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String username = sharedPref.getString(getString(R.string.username),"zdg");
        Log.d(TAG,"username: "+ username);
        new getQuestionTask().execute(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_question, menu);
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

        private ArrayAdapter<String> mQuestionAdapter;


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my_question, container, false);
            return rootView;
        }
    }

    private class getQuestionTask extends AsyncTask<String, Void,Collection<Question>>
    {

        @Override
        protected Collection<Question> doInBackground(String... username) {
            Collection<Question> questions = QuestionSvc.getOrInit(getString(R.string.serverUrl))
                    .findByUserName(username[0]);
            return questions;
        }

        @Override
        protected void onPostExecute(Collection<Question> result){
            final questionAdapter mQuestionAdapter;
            ListView listView = (ListView)findViewById(R.id.listview_question);
            mQuestionAdapter = new questionAdapter(
                    MyQuestionActivity.this,
                    R.layout.list_item_question,
                    R.id.list_item_question_textview,
                    new ArrayList<Question>(result)
            );
            listView.setAdapter(mQuestionAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Question question = mQuestionAdapter.getItem(position);
                    Intent intent = new Intent(MyQuestionActivity.this,QAActivity.class);
                    intent.putExtra(QAActivity.Q_CONTENT,question.getContent());
                    intent.putExtra(QAActivity.Q_ID,question.getQid());
                    startActivity(intent);
                }
            });
        }
    }

    public class questionAdapter extends ArrayAdapter<Question>{

        public questionAdapter(Context context, int resource, int textViewResourceId, List<Question> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            Question ques = getItem(position);
            if(convertView == null){
                convertView =  LayoutInflater.from(this.getContext())
                        .inflate(R.layout.list_item_question, parent, false);
            }
            TextView tvContent = (TextView)convertView.findViewById(R.id.list_item_question_textview);
            tvContent.setText(ques.getContent());
            return convertView;
        }
    }
}
