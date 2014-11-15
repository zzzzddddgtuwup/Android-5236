package com.android.mobileapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
        new getQuestionTask().execute(forumId);
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

        @Override
        protected Collection<Question> doInBackground(Integer... forumId) {
            Collection<Question> questions = QuestionSvc.getOrInit(getString(R.string.serverUrl))
                    .findByForumId(forumId[0]);
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
                    intent.putExtra(QAActivity.Q_CONTENT,question.getContent());
                    intent.putExtra(QAActivity.Q_ID,question.getQid());
                    intent.putExtra(QAActivity.Q_RATE,question.getRate());
                    startActivity(intent);
                }
            });
        }
    }
}
