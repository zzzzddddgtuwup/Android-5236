package com.android.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Tab1Fragment extends Fragment {
    private View view;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int forumId = getArguments().getInt(getString(R.string.map_to_forum_intent_extra),1);
        Log.d("tap1","forum is " + forumId);
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new getQuestionTask().execute(forumId);
        }else{
            TextView tvTitle = (TextView)view.findViewById(R.id.tab1_title);
            tvTitle.setText("network not available");
        }
    }

    public Tab1Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab1, container, false);
        return view;
    }

    private class getQuestionTask extends AsyncTask<Integer, Void,Collection<Question>>
    {
        private int fid;

        @Override
        protected Collection<Question> doInBackground(Integer... forumId) {
            Collection<Question> questions = QuestionSvc.getOrInit(getString(R.string.serverUrl))
                    .findByForumId(forumId[0]);
            fid = forumId[0];
            return questions;
        }

        @Override
        protected void onPostExecute(Collection<Question> result){
            final questionAdapter mQuestionAdapter;
            ListView listView = (ListView)view.findViewById(R.id.listview_AllQuestions_Forum);
            mQuestionAdapter = new questionAdapter(
                    getActivity(),
                    R.layout.list_item_question,
                    R.id.list_item_question_textview,
                    new ArrayList<Question>(result)
            );
            listView.setAdapter(mQuestionAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Question question = mQuestionAdapter.getItem(position);
                    Intent intent = new Intent(getActivity(),QAActivity.class);
                    intent.putExtra(getString(R.string.Q_CONTENT),question.getContent());
                    intent.putExtra(getString(R.string.Q_ID),question.getQid());
                    intent.putExtra(getString(R.string.Q_RATE),question.getRate());
                    intent.putExtra(getString(R.string.Q_USER),question.getUser().getUsername());
                    intent.putExtra(getString(R.string.F_ID),fid);
                    startActivity(intent);
                }
            });
        }
    }
}
