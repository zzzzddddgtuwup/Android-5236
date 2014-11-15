package com.android.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import android.widget.SearchView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Tab3Fragment extends Fragment {

    private EditText questionEditableField;
    private SearchView searchEditableField;
    public Tab3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=  inflater.inflate(R.layout.fragment_tab3, container, false);

        final int forumId = getArguments().getInt(getString(R.string.map_to_forum_intent_extra));
        Log.e("tab3", "this is forum " + forumId);
        questionEditableField =(EditText) rootView.findViewById(R.id.question_text);
        searchEditableField =(SearchView) rootView.findViewById(R.id.searchView);
        View askQuestion =(Button) rootView.findViewById(R.id.add_question);
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final String username = sharedPref.getString(getString(R.string.username),"zdg");


        final View.OnClickListener Click = new View.OnClickListener(){
            @Override
            public void onClick (View view){
                switch(view.getId()){
                    case R.id.add_question:
                        Intent askQuestion = new Intent(getActivity(), ForumActivity.class);
                        new addQuestionTask().execute(questionEditableField.getText().toString()
                                ,username,""+forumId);
                        askQuestion.putExtra(getString(R.string.map_to_forum_intent_extra),forumId);
                        startActivity(askQuestion);
                        break;
                    case R.id.add_answer:
                        Toast.makeText(getActivity(), "You upvote the question", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        askQuestion.setOnClickListener(Click);
        return rootView;
    }

    private class addQuestionTask extends AsyncTask<String, Void,Void>
    {

        @Override
        protected Void doInBackground(String... params) {
            QuestionSvc.getOrInit(getString(R.string.serverUrl))
                    .addQuestion(params[0],params[1],Long.parseLong(params[2]));
            return null;
        }
    }

    private void searchQuestion(){
        String result= this.searchEditableField.getQuery().toString();
    }

}
