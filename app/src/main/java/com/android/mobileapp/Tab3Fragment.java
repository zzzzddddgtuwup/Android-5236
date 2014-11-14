package com.android.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Tab3Fragment extends Fragment {

    private EditText questionEditableField;

    public Tab3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=  inflater.inflate(R.layout.fragment_tab3, container, false);
        View askQuestion =(Button) rootView.findViewById(R.id.add_question);
        questionEditableField =(EditText) rootView.findViewById(R.id.question_text);
        final View.OnClickListener Click = new View.OnClickListener(){
            @Override
            public void onClick (View view){
                switch(view.getId()){
                    case R.id.add_question:
                        Intent askQuestion = new Intent(getActivity(), ForumActivity.class);
                        startActivity(askQuestion);
                        break;
                }
            }
        };

        askQuestion.setOnClickListener(Click);

        return rootView;
    }

    private void getQuestion(){
        String question = this.questionEditableField.getText().toString();
    }

}
