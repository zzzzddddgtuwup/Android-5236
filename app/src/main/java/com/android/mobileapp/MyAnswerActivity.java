package com.android.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class MyAnswerActivity extends ActionBarActivity {
    private final String TAG = ((Object) this).getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_answer);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        new getAnswerTask().execute("zdg");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_answer, menu);
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

        private ArrayAdapter<String> mAnswerAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my_answer, container, false);
//
//            String[] data = {
//                    "Turn left and go right",
//                    "Nope,it's not free",
//                    "What kind of help?",
//                    "How much ?",
//                    "Nice"
//            };
//
//            List<String> answer = new ArrayList<String>(Arrays.asList(data));
//            mAnswerAdapter = new ArrayAdapter<String>(
//                    getActivity(),
//                    R.layout.list_item_answer,
//                    R.id.list_item_answer_textview,
//                    answer);
//            ListView listView = (ListView) rootView.findViewById(R.id.listview_answer);
//            listView.setAdapter(mAnswerAdapter);
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                    String question = mAnswerAdapter.getItem(position);
//                    Intent intent = new Intent()
//                            .putExtra(Intent.EXTRA_TEXT,question);
//                    startActivity(intent);
//                }
//            });
            return rootView;
        }
    }

    private class getAnswerTask extends AsyncTask<String, Void, Collection<Answer>>{

        @Override
        protected Collection<Answer> doInBackground(String... name) {
            //may throw exception here
            Collection<Answer> answers= answerSvc.getOrInit(getString(R.string.serverUrl))
                    .findByUserName(name[0]);
            return answers;
        }

        @Override
        protected void onPostExecute(Collection<Answer> result){
            final answerAdapter mAnswerAdapter;
            ListView listView = (ListView)findViewById(R.id.listview_answer);
            mAnswerAdapter = new answerAdapter(
                    MyAnswerActivity.this,
                    R.layout.list_item_answer,
                    R.id.list_item_answer_textview,
                    new ArrayList<Answer>(result));
            listView.setAdapter(mAnswerAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Answer s_answer = mAnswerAdapter.getItem(position);
                    Intent intent = new Intent()
                            .putExtra(Intent.EXTRA_TEXT,s_answer.getAid());
                    startActivity(intent);
                }
            });
        }
    }

    public class answerAdapter extends ArrayAdapter<Answer>{

        public answerAdapter(Context context, int resource, int textViewResourceId, List<Answer> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            Answer ans = getItem(position);
            if(convertView==null){
                convertView = LayoutInflater.from(this.getContext())
                        .inflate(R.layout.list_item_answer, parent, false);
            }
            TextView tvContent = (TextView)convertView.findViewById(R.id.list_item_answer_textview);
            tvContent.setText(ans.getContent());
            return convertView;
        }
    }
}
