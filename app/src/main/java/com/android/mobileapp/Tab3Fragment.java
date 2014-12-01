package com.android.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Tab3Fragment extends Fragment {

    public Tab3Fragment(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=  inflater.inflate(R.layout.fragment_tab3, container, false);

        final int forumId = getArguments().getInt(getString(R.string.map_to_forum_intent_extra));
        Log.e("tab3", "this is forum " + forumId);
        final EditText questionEditableField =(EditText) rootView.findViewById(R.id.question_text);
        View askQuestion =(Button) rootView.findViewById(R.id.add_question);
        View searchQuestion = (Button) rootView.findViewById(R.id.search_button);
        final EditText searchEditableField = (EditText) rootView.findViewById(R.id.search_text);

        //get user name from shared preference
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final String username = sharedPref.getString(getString(R.string.username),"zdg");


        final View.OnClickListener Click = new View.OnClickListener(){
            @Override
            public void onClick (View view){
                ConnectivityManager connMgr = (ConnectivityManager)
                        getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()){
                    switch(view.getId()){
                        case R.id.add_question:
                            Intent askQuestion = new Intent(getActivity(), ForumActivity.class);
                            new addQuestionTask().execute(questionEditableField.getText().toString()
                                    , username, "" + forumId);
                            askQuestion.putExtra(getString(R.string.map_to_forum_intent_extra), forumId);
                            startActivity(askQuestion);
                            break;
                        case R.id.search_button:
                            Log.d("search","search is entered");
                            Intent searchIntent = new Intent(getActivity(),SearchResultActivity.class);
                            searchIntent.putExtra(getString(R.string.question_search_content),searchEditableField.getText().toString());
                            searchIntent.putExtra(getString(R.string.map_to_forum_intent_extra),forumId);
                            startActivity(searchIntent);
                            break;
                    }
                }else{
                    Toast.makeText(getActivity(), "The network is not available. Please open WIFI or Mobile network",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
        askQuestion.setOnClickListener(Click);
        searchQuestion.setOnClickListener(Click);
        return rootView;
    }

    //add questions of one forum to server
    private class addQuestionTask extends AsyncTask<String, Void,Void>
    {

        @Override
        protected Void doInBackground(String... params) {
            questionSvc.getOrInit(getString(R.string.serverUrl))
                    .addQuestion(params[0],params[1],Long.parseLong(params[2]));
            return null;
        }
    }
}
