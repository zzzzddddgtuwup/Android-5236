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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;


/**
 * A simple {@link Fragment} subclass.
 *
 */
// get questions as sorted
public class Tab2Fragment extends Fragment {
    private View view;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int forumId = getArguments().getInt(getString(R.string.map_to_forum_intent_extra));
        Log.e("tab2","this is forum " + forumId);
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new getQuestionTask().execute(forumId);
        }else{
            TextView tvTitle = (TextView)view.findViewById(R.id.tab2_title);
            tvTitle.setText("network not available");
        }
    }

    public Tab2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab2, container, false);
        return view;
    }

    //get sorted questions from server
    private class getQuestionTask extends AsyncTask<Integer, Void,Collection<Question>> {
        private int fid;

        @Override
        protected Collection<Question> doInBackground(Integer... forumId) {
            Collection<Question> questions = questionSvc.getOrInit(getString(R.string.serverUrl))
                    .getSortedQuestionList(forumId[0]);
            fid = forumId[0];
            return questions;
        }

        @Override
        protected void onPostExecute(Collection<Question> result) {
            final questionAdapter mQuestionAdapter;
            ListView listView = (ListView) view.findViewById(R.id.listview_popularQuestions_Forum);
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
                    Intent intent = new Intent(getActivity(), QAActivity.class);
                    intent.putExtra(getString(R.string.Q_CONTENT), question.getContent());
                    intent.putExtra(getString(R.string.Q_ID), question.getQid());
                    intent.putExtra(getString(R.string.Q_RATE), question.getRate());
                    intent.putExtra(getString(R.string.F_ID),fid);
                    intent.putExtra(getString(R.string.Q_USER),question.getUser().getUsername());
                    startActivity(intent);
                }
            });
        }
    }
}
